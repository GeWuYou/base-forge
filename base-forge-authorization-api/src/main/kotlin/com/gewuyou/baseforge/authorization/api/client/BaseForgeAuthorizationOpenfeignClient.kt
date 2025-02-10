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
        private const val BASE_URL = "/api"
        private const val V1 = "/v1"
        private const val SERVICE_URL = "/auth/z"
        private const val USER_URL = "/user"
        private const val PERMISSION_URL ="/permission"
        private const val USER_ROLE_URL ="/userRole"

        private const val USER_URL_V1 = BASE_URL.plus(V1).plus(SERVICE_URL).plus(USER_URL)
        private const val PERMISSION_URL_V1 = BASE_URL.plus(V1).plus(SERVICE_URL).plus(PERMISSION_URL)
        private const val USER_ROLE_URL_V1 = BASE_URL.plus(V1).plus(SERVICE_URL).plus(USER_ROLE_URL)
    }
    @GetMapping("$USER_URL_V1/email/{email}")
    fun findByEmail(@PathVariable email: String): AuthUser?

    @GetMapping("$USER_URL_V1/phone/{phone}")
    fun findByPhone(@PathVariable phone: String): AuthUser?

    @GetMapping("$USER_URL_V1/username/{username}")
    fun findByUsername(@PathVariable username: String): AuthUser?

    /**
     * 根据key(前缀+url+method)获取权限列表
     */
    @GetMapping("$PERMISSION_URL_V1/{key}")
    fun getPermissionsByKey(@PathVariable key: String):Set<String>

    /**
     * 获取权限列表
     * @return 权限列表
     */
    @GetMapping("$PERMISSION_URL_V1/list")
    fun getAuthPermissionRoleDtoList(): List<AuthPermissionRoleDto>

    @GetMapping("$USER_ROLE_URL_V1/list/{userAuthId}")
    fun getRoleByUserAuthId(@PathVariable userAuthId: Long): List<AuthRole>
}