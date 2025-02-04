package com.gewuyou.baseforge.authorization.controller

import com.gewuyou.baseforge.authorization.model.AuthRole
import com.gewuyou.baseforge.authorization.service.AuthUserRoleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p>
 * 用户角色关系表 前端控制器
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@RestController
@RequestMapping("/auth/userRole")
class AuthUserRoleController(
    private val authUserRoleService: AuthUserRoleService
){
    @GetMapping("/list/{userAuthId}")
    fun getRoleByUserAuthId(@PathVariable userAuthId: Long): List<AuthRole>{
        return authUserRoleService.getRoleByUserAuthId(userAuthId)
    }
}
