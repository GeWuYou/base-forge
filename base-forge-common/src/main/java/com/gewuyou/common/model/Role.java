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
 * 角色表
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-09
 */
@Schema(name = "Role对象", description = "角色表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_role")
public class Role implements Serializable {

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
     * 角色名称
     */
    @Schema(description = "角色名称")
    @TableField("role_name")
    private String roleName;

    /**
     * 是否禁用 0否 ：1是
     */
    @Schema(description = "是否禁用 0否 ：1是")
    @TableField("is_disabled")
    private Boolean isDisabled;
}
