package com.gewuyou.baseforge.auth.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.gewuyou.baseforge.auth.constant.AuthConstant
import com.gewuyou.baseforge.auth.mapper.AuthPermissionMapper
import com.gewuyou.baseforge.auth.model.AuthPermission
import com.gewuyou.baseforge.auth.service.AuthPermissionService
import com.gewuyou.baseforge.core.exception.InternalException
import com.gewuyou.baseforge.redis.service.CacheService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Service
open class AuthPermissionServiceImpl(
    private val cacheService: CacheService,
    private val objectMapper: ObjectMapper
) : ServiceImpl<AuthPermissionMapper, AuthPermission>(), AuthPermissionService {
    /**
     * 加载权限列表到缓存中
     */
    @PostConstruct
    private fun loadPermissionsToCache() = baseMapper.getAuthPermissionRoleDtoList()
        .stream()
        .forEach {
            val key = AuthConstant.PERMISSION_CACHE_PREFIX + "$it.url:$it.requestMethod"
            val value = objectMapper.writeValueAsString(it.roles.toSet())
            cacheService.set(key, value)
        }

    /**
     * 根据key(前缀+url+method)获取权限列表
     */
    override fun getPermissionsByKey(key: String): Set<String> {
        // 尝试从缓存中获取权限数据
        return getPermissionsFromCache(key) ?: run {
            // 缓存中没有，加载数据并重新尝试从缓存获取
            loadPermissionsToCache()
            getPermissionsFromCache(key) ?: throw InternalException("缓存中没有该权限数据")
        }
    }

    /**
     * 尝试从缓存中获取权限数据并反序列化
     */
    private fun getPermissionsFromCache(key: String): Set<String>? {
        val cacheData = cacheService.get(key) ?: return null
        val map: Map<String, Set<String>> = objectMapper.readValue(cacheData as String, object : TypeReference<Map<String, Set<String>>>() {})
        return map[key]
    }
}
