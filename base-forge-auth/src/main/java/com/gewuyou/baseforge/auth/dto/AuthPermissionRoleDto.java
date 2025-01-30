package com.gewuyou.baseforge.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author gewuyou DTO for {@link com.gewuyou.baseforge.auth.model.AuthPermission}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthPermissionRoleDto implements Serializable {
    /**
     * 权限id
     */
    private Long id;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求路径
     */
    private String url;
    /**
     * 角色列表
     */
    private List<String> roles;
}