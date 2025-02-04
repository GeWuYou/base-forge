package com.gewuyou.baseforge.dictionary.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典项表
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Getter
@Setter
@Entity
@Table(name = "bf_dictionary_item")
@TableName("bf_dictionary_item")
@Schema(name = "DictionaryItem", description = "字典项表")
public class DictionaryItem implements Serializable {

    /**
     * 属性名 主键
     */
    public static final String FIELD_ID = "id";

    // 字段属性名常量
    /**
     * 属性名 类别id
     */
    public static final String FIELD_CATEGORY_ID = "categoryId";
    /**
     * 属性名 字典代码
     */
    public static final String FIELD_CODE = "code";
    /**
     * 属性名 字典项名
     */
    public static final String FIELD_NAME = "name";
    /**
     * 属性名 字典项值(可为正则表达式，枚举值...)
     */
    public static final String FIELD_VALUE = "value";
    /**
     * 属性名 描述
     */
    public static final String FIELD_DESCRIPTION = "description";
    /**
     * 属性名 是否启用
     */
    public static final String FIELD_IS_ENABLED = "isEnabled";
    /**
     * 属性名 是否删除
     */
    public static final String FIELD_IS_DELETED = "isDeleted";
    /**
     * 属性名 环境
     */
    public static final String FIELD_ENVIRONMENT = "environment";
    /**
     * 属性名 类型
     */
    public static final String FIELD_TYPE = "type";
    /**
     * 属性名 创建者
     */
    public static final String FIELD_CREATED_BY = "createdBy";
    /**
     * 属性名 修改者
     */
    public static final String FIELD_UPDATED_BY = "updatedBy";
    /**
     * 属性名 生效日期
     */
    public static final String FIELD_EFFECTIVE_DATE = "effectiveDate";
    /**
     * 属性名 过期日期
     */
    public static final String FIELD_EXPIRATION_DATE = "expirationDate";
    /**
     * 属性名 创建时间
     */
    public static final String FIELD_CREATED_AT = "createdAt";
    /**
     * 属性名 修改时间
     */
    public static final String FIELD_UPDATED_AT = "updatedAt";
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @Column(name = "id")
    private Long id;

    @Schema(description = "类别id")
    @TableField("category_id")
    @Column(name = "category_id")
    private Long categoryId;

    @Schema(description = "字典代码")
    @TableField("code")
    @Column(name = "code")
    private String code;

    @Schema(description = "字典项名")
    @TableField("name")
    @Column(name = "name")
    private String name;

    @Schema(description = "字典项值(可为正则表达式，枚举值...)")
    @TableField("value")
    @Column(name = "value")
    private String value;

    @Schema(description = "描述")
    @TableField("description")
    @Column(name = "description")
    private String description;

    @Schema(description = "是否启用")
    @TableField("is_enabled")
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Schema(description = "是否删除")
    @TableField("is_deleted")
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Schema(description = "环境")
    @TableField("environment")
    @Column(name = "environment")
    private String environment;

    @Schema(description = "类型 lambda/java/sql/...")
    @TableField("type")
    @Column(name = "type")
    private String type;

    @Schema(description = "创建者")
    @TableField("created_by")
    @Column(name = "created_by")
    private Long createdBy;

    @Schema(description = "修改者")
    @TableField("updated_by")
    @Column(name = "updated_by")
    private Long updatedBy;

    @Schema(description = "生效日期")
    @TableField("effective_date")
    @Column(name = "effective_date")
    private LocalDateTime effectiveDate;

    @Schema(description = "过期日期")
    @TableField("expiration_date")
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Schema(description = "创建时间")
    @TableField("created_at")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间")
    @TableField("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
