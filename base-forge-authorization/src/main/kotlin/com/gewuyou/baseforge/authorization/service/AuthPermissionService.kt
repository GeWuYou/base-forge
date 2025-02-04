package com.gewuyou.baseforge.authorization.service

import com.baomidou.mybatisplus.extension.service.IService
import com.gewuyou.baseforge.authorization.dto.AuthPermissionRoleDto
import com.gewuyou.baseforge.authorization.model.AuthPermission

/**
 *
 * 权限表 服务类
 *
 *
 * @author gewuyou
 * @since 2025-01-15
 */
interface AuthPermissionService : IService<AuthPermission> {
    /**
     * 根据key(前缀+url+method)获取权限列表
     */
    fun getPermissionsByKey(key: String):Set<String>

    fun getAuthPermissionRoleDtoList(): List<AuthPermissionRoleDto>
}
