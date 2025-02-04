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
 * 字典类别
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Getter
@Setter
@Entity
@Table(name = "bf_dictionary_category")
@TableName("bf_dictionary_category")
@Schema(name = "DictionaryCategory", description = "字典类别")
public class DictionaryCategory implements Serializable {

    /**
     * 属性名 主键
     */
    public static final String FIELD_ID = "id";
    /**
     * 属性名 类别名称
     */
    public static final String FIELD_NAME = "name";
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
     * 属性名 创建时间
     */
    public static final String FIELD_CREATED_AT = "createdAt";
    /**
     * 属性名 修改时间
     */
    public static final String FIELD_UPDATED_AT = "updatedAt";
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "类别名称")
    @TableField("name")
    @Column(name = "name")
    private String name;

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

    @Schema(description = "创建者")
    @TableField("created_by")
    @Column(name = "created_by")
    private Long createdBy;

    @Schema(description = "修改者")
    @TableField("updated_by")
    @Column(name = "updated_by")
    private Long updatedBy;

    @Schema(description = "创建时间")
    @TableField("created_at")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间")
    @TableField("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
