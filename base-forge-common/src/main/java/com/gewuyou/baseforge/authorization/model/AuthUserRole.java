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
 * 用户角色关系表
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Getter
@Setter
@Entity
@Table(name = "bf_auth_user_role")
@TableName("bf_auth_user_role")
@Schema(name = "AuthUserRole", description = "用户角色关系表")
public class AuthUserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "主键")
    @Column(name = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户认证表主键")
    @Column(name = "user_auth_id")
    @TableField("user_auth_id")
    private Long userAuthId;

    @Schema(description = "角色表主键")
    @Column(name = "role_id")
    @TableField("role_id")
    private Long roleId;

    @Schema(description = "创建时间")
    @Column(name = "created_at")
    @TableField("created_at")
    private LocalDateTime createdAt;
}
