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
 * <p>
 * 字典项历史表
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Getter
@Setter
@Entity
@TableName("bf_dictionary_item_history")
@Table(name = "bf_dictionary_item_history")
@Schema(name = "DictionaryItemHistory", description = "字典项历史表")
public class DictionaryItemHistory implements Serializable {

    /**
     * 属性名 主键
     */
    public static final String FIELD_ID = "id";
    /**
     * 属性名 字典项id
     */
    public static final String FIELD_DICTIONARY_ITEM_ID = "dictionaryItemId";
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
    /**
     * 属性名 操作类型（1 新增 2 修改 3 逻辑删除 4 物理删除）
     */
    public static final String FIELD_OPERATION_TYPE = "operationType";
    /**
     * 属性名 操作时间戳
     */
    public static final String FIELD_OPERATION_TIMESTAMP = "operationTimestamp";
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "字典项id")
    @Column(name = "dictionary_item_id")
    @TableField("dictionary_item_id")
    private Long dictionaryItemId;

    @Schema(description = "类别id")
    @Column(name = "category_id")
    @TableField("category_id")
    private Long categoryId;

    @Schema(description = "字典代码")
    @Column(name = "code")
    @TableField("code")
    private String code;

    @Schema(description = "字典项名")
    @Column(name = "name")
    @TableField("name")
    private String name;

    @Schema(description = "字典项值(可为正则表达式，枚举值...)")
    @Column(name = "value")
    @TableField("value")
    private String value;

    @Schema(description = "描述")
    @Column(name = "description")
    @TableField("description")
    private String description;

    @Schema(description = "是否启用")
    @Column(name = "is_enabled")
    @TableField("is_enabled")
    private Boolean isEnabled;

    @Schema(description = "是否删除")
    @Column(name = "is_deleted")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @Schema(description = "环境")
    @Column(name = "environment")
    @TableField("environment")
    private String environment;

    @Schema(description = "创建者")
    @Column(name = "created_by")
    @TableField("created_by")
    private Long createdBy;

    @Schema(description = "修改者")
    @Column(name = "updated_by")
    @TableField("updated_by")
    private Long updatedBy;

    @Schema(description = "生效日期")
    @Column(name = "effective_date")
    @TableField("effective_date")
    private LocalDateTime effectiveDate;

    @Schema(description = "过期日期")
    @Column(name = "expiration_date")
    @TableField("expiration_date")
    private LocalDateTime expirationDate;

    @Schema(description = "创建时间")
    @Column(name = "created_at")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间")
    @Column(name = "updated_at")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "操作类型（1 新增 2 修改 3 逻辑删除 4 物理删除）")
    @Column(name = "operation_type")
    @TableField("operation_type")
    private Integer operationType;

    @Schema(description = "操作时间戳")
    @Column(name = "operation_timestamp")
    @TableField("operation_timestamp")
    private LocalDateTime operationTimestamp;
}
