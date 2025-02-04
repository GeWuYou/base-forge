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
 * 用户认证表
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Getter
@Setter
@Entity
@Table(name = "bf_auth_user")
@TableName("bf_auth_user")
@Schema(name = "AuthUser", description = "用户认证表")
public class AuthUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "用户认证表主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    @Column(name = "username")
    @TableField("username")
    private String username;

    @Schema(description = "用户电话号码")
    @Column(name = "phone")
    @TableField("phone")
    private String phone;

    @Schema(description = "邮箱")
    @Column(name = "email")
    @TableField("email")
    private String email;

    @Schema(description = "加密存储的密码")
    @Column(name = "password")
    @TableField("password")
    private String password;

    @Schema(description = "登录提供商类型(如 local, google, github 等)")
    @Column(name = "provider")
    @TableField("provider")
    private String provider;

    @Schema(description = "第三方提供商的用户 ID")
    @Column(name = "provider_id")
    @TableField("provider_id")
    private String providerId;

    @Schema(description = "是否启用")
    @Column(name = "is_enabled")
    @TableField("is_enabled")
    private Boolean isEnabled;

    @Schema(description = "是否锁定")
    @Column(name = "is_locked")
    @TableField("is_locked")
    private Boolean isLocked;

    @Schema(description = "创建时间")
    @Column(name = "created_at")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间")
    @Column(name = "updated_at")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "是否删除")
    @Column(name = "is_deleted")
    @TableField("is_deleted")
    private Boolean isDeleted;
}
