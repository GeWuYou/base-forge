package com.gewuyou.baseforge.authorization.controller

import com.gewuyou.baseforge.authorization.dto.AuthPermissionRoleDto
import com.gewuyou.baseforge.authorization.service.AuthPermissionService
import com.gewuyou.baseforge.entities.web.annotation.ApiVersion
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@RestController
@ApiVersion
@RequestMapping("/auth/z/permission")
class AuthPermissionController(
    private val authPermissionService: AuthPermissionService
){
    /**
     * 根据key(前缀+url+method)获取权限列表
     */
    @GetMapping("/{key}")
    fun getPermissionsByKey(@PathVariable key: String):Set<String>{
            return authPermissionService.getPermissionsByKey(key)
    }

    /**
     * 获取权限列表
     * @return 权限列表
     */
    @GetMapping("/list")
    fun getAuthPermissionRoleDtoList(): List<AuthPermissionRoleDto>{
        return authPermissionService.getAuthPermissionRoleDtoList()
    }
}
