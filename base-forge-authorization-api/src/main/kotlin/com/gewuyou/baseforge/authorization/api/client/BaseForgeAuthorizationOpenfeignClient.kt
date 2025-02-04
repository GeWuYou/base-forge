package com.gewuyou.baseforge.authorization.api.client

import com.gewuyou.baseforge.authorization.dto.AuthPermissionRoleDto
import com.gewuyou.baseforge.authorization.model.AuthRole
import com.gewuyou.baseforge.authorization.model.AuthUser
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 *授权服务 OpenFeign 客户端
 *
 * @since 2025-02-02 12:23:43
 * @author gewuyou
 */
@FeignClient(name = "base-forge-authorization")
interface BaseForgeAuthorizationOpenfeignClient {
    companion object{
        private const val BASE_URL = "/auth/z"
        const val USER_URL = BASE_URL.plus("/user")
        const val PERMISSION_URL = BASE_URL.plus("/permission")
        const val USER_ROLE_URL = BASE_URL.plus("/userRole")
    }
    @GetMapping("$USER_URL/email/{email}")
    fun findByEmail(@PathVariable email: String): AuthUser?

    @GetMapping("$USER_URL/phone/{phone}")
    fun findByPhone(@PathVariable phone: String): AuthUser?

    @GetMapping("$USER_URL/username/{username}")
    fun findByUsername(@PathVariable username: String): AuthUser?

    /**
     * 根据key(前缀+url+method)获取权限列表
     */
    @GetMapping("$PERMISSION_URL/{key}")
    fun getPermissionsByKey(@PathVariable key: String):Set<String>

    /**
     * 获取权限列表
     * @return 权限列表
     */
    @GetMapping("$PERMISSION_URL/list")
    fun getAuthPermissionRoleDtoList(): List<AuthPermissionRoleDto>

    @GetMapping("$USER_ROLE_URL/list/{userAuthId}")
    fun getRoleByUserAuthId(@PathVariable userAuthId: Long): List<AuthRole>
}