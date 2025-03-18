package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entitySerialVersionUID>
import java.io.Serial;
</#if>
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
    <#if isBuilderModel>
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
    </#if>
    <#if chainModel>
import lombok.experimental.Accessors;
    </#if>
</#if>
import jakarta.persistence.*;

/**
*
* ${table.comment!}实体类
*
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Getter
@Setter
    <#if chainModel>
@Accessors(chain = true)
    </#if>
    <#if isBuilderModel>
@Builder
@AllArgsConstructor
@NoArgsConstructor
    </#if>
</#if>
<#if springdoc>
@Schema(name = "${entity}", description = "${table.comment}")
<#elseif swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
@Entity
<#if table.convert>
@TableName("<#if schema??>${schema}.</#if>${table.name}")
</#if>
@Table(name = "${table.name}"<#if schema??>, schema = "${schema}"</#if>)
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#elseif entitySerialVersionUID>
public class ${entity} implements Serializable {
<#else>
public class ${entity} {
</#if>
<#if entitySerialVersionUID>
    @Serial
    private static final long serialVersionUID = 1L;
</#if>
    //region 属性
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    /**
    * ${field.comment}
    */
        <#if springdoc>
    @Schema(description = "${field.comment}")
        <#elseif swagger>
    @ApiModelProperty("${field.comment}")
        </#if>
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
    @Id
<#if field.keyIdentityFlag>
    @TableId(value = "${field.annotationColumnName}", type = IdType.AUTO)
<#elseif idType??>
    @TableId(value = "${field.annotationColumnName}", type = IdType.${idType})
<#elseif field.convert>
    @TableId("${field.annotationColumnName}")
</#if>
    <#if field.keyIdentityFlag>
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "${table.name}_id_gen")
    @SequenceGenerator(name = "${table.name}_id_gen", sequenceName = "${table.name}_id_seq", allocationSize = 1)
    </#if>
    <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
        <#if field.convert>
    @TableField(value = "${field.annotationColumnName}", fill = FieldFill.${field.fill})
        <#else>
    @TableField(fill = FieldFill.${field.fill})
        </#if>
    <#elseif field.convert>
    @TableField("${field.annotationColumnName}")
    </#if>
<#-- 乐观锁注解 -->
    <#if field.versionField>
    @Version
    </#if>
<#-- 逻辑删除注解 -->
    <#if field.logicDeleteField>
    @TableLogic
    </#if>
    @Column(name = "${field.annotationColumnName}")
    private ${field.propertyType} ${field.propertyName};
</#list>
    //endregion
    //region 属性名常量
<#------------  循环遍历生成属性名常量  ---------->
    public static final String TABLE_NAME = "${table.name}";
<#list table.fields as field>
    public static final String ${field.customMap["constantName"]} = "${field.propertyName}";
</#list>
    //endregion
<#------------  END 字段循环遍历  ---------->
<#if !entityLombokModel>
    //region getter and setter
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>

    public ${field.propertyType} ${getprefix}${field.capitalName}() {
    return ${field.propertyName};
    }

    <#if chainModel>
        public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    <#else>
        public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    </#if>
    this.${field.propertyName} = ${field.propertyName};
    <#if chainModel>
        return this;
    </#if>
    }
    </#list>
    //endregion
</#if>
<#if !entityLombokModel>

    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}