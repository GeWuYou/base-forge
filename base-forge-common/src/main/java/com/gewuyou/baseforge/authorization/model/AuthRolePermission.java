package com.gewuyou.baseforge.authorization.model;

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
 * 角色权限关系表
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Getter
@Setter
@Entity
@Table(name = "bf_auth_role_permission")
@TableName("bf_auth_role_permission")
@Schema(name = "AuthRolePermission", description = "角色权限关系表")
public class AuthRolePermission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色表主键")
    @Column(name = "role_id")
    @TableField("role_id")
    private Long roleId;

    @Schema(description = "权限表主键")
    @Column(name = "permission_id")
    @TableField("permission_id")
    private Long permissionId;

    @Schema(description = "创建时间")
    @Column(name = "created_at")
    @TableField("created_at")
    private LocalDateTime createdAt;
}
