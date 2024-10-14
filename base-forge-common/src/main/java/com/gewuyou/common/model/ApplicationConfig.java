package com.gewuyou.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 系统配置表，用于存储不同模块的配置信息
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-11
 */
@Schema(description = "系统配置表，用于存储不同模块的配置信息")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_application_config")
public class ApplicationConfig extends BaseModel implements Serializable {

    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 命名空间，用于区分不同的配置集
     */
    @Schema(description = "命名空间，用于区分不同的配置集")
    @TableField("namespace")
    private String namespace;

    /**
     * 配置键
     */
    @Schema(description = "配置键")
    @TableField("key")
    private String key;

    /**
     * 配置值
     */
    @Schema(description = "配置值")
    @TableField("value")
    private String value;

    /**
     * 配置说明
     */
    @Schema(description = "配置说明")
    @TableField("description")
    private String description;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    @TableField("modified_by")
    private String modifiedBy;

    /**
     * 配置状态（是否启用） 0-禁用 1-启用
     */
    @Schema(description = "配置状态（是否启用） 0-禁用 1-启用")
    @TableField("status")
    private Boolean status;
}
