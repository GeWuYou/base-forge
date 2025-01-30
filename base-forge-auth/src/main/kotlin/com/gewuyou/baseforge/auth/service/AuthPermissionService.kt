package com.gewuyou.baseforge.auth.service;

import com.baomidou.mybatisplus.extension.service.IService
import com.gewuyou.baseforge.auth.model.AuthPermission

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
interface AuthPermissionService : IService<AuthPermission> {
    /**
     * 根据key(前缀+url+method)获取权限列表
     */
    fun getPermissionsByKey(key: String):Set<String>
}
