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
 * 权限表
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Getter
@Setter
@Entity
@Table(name = "bf_auth_permission")
@TableName("bf_auth_permission")
@Schema(name = "AuthPermission", description = "权限表")
public class AuthPermission implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Schema(description = "主键")
    @Column(name = "id")
    @TableId(value = "id", type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 父权限id
     */
    @Schema(description = "父权限id")
    @Column(name = "parent_id")
    @TableField("parent_id")
    private Long parentId;

    @Schema(description = "请求方法：GET, POST, PUT, DELETE等")
    @Column(name = "request_method")
    @TableField("request_method")
    private String requestMethod;

    @Schema(description = "请求路径")
    @Column(name = "url")
    @TableField("url")
    private String url;


    @Schema(description = "权限名")
    @Column(name = "permission_name")
    @TableField("permission_name")
    private String permissionName;

    @Schema(description = "描述")
    @Column(name = "description")
    @TableField("description")
    private String description;

    @Schema(description = "创建数据")
    @Column(name = "created_at")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "修改时间")
    @Column(name = "updated_at")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "是否启用")
    @Column(name = "is_active")
    @TableField("is_active")
    private Boolean isActive;

    @Schema(description = "是否删除")
    @Column(name = "is_deleted")
    @TableField("is_deleted")
    private Boolean isDeleted;
}
