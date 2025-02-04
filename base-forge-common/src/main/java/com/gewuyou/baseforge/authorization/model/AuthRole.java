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
 * 角色表
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Getter
@Setter
@Entity
@Table(name = "bf_auth_role")
@TableName("bf_auth_role")
@Schema(name = "AuthRole", description = "角色表")
public class AuthRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;



    @Schema(description = "角色名")
    @Column(name = "role_name")
    @TableField("role_name")
    private String roleName;

    @Schema(description = "描述")
    @Column(name = "description")
    @TableField("description")
    private String description;

    @Schema(description = "创建时间")
    @Column(name = "created_at")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "是否启用")
    @Column(name = "is_active")
    @TableField("is_active")
    private Boolean isActive;

    @Schema(description = "是否删除")
    @Column(name = "is_deleted")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @Schema(description = "修改时间")
    @Column(name = "update_at")
    @TableField("update_at")
    private LocalDateTime updateAt;
}
