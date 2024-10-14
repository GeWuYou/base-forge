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
 * 角色资源中间表
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-09
 */
@Schema(name = "RoleResource对象", description = "角色资源中间表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_role_resource")
public class RoleResource implements Serializable {

    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @TableField("role_id")
    private String roleId;

    /**
     * 资源id
     */
    @Schema(description = "资源id")
    @TableField("resource_id")
    private String resourceId;
}
