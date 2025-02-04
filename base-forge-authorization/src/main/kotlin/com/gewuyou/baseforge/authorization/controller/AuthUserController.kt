package com.gewuyou.baseforge.authorization.controller

import com.gewuyou.baseforge.authorization.model.AuthUser
import com.gewuyou.baseforge.authorization.service.AuthUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * 用户认证表 前端控制器
 *
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@RestController
@RequestMapping("/auth/z/user")
class AuthUserController(
    private val authUserService: AuthUserService
) {
    @GetMapping("/email/{email}")
    fun findByEmail(@PathVariable email: String): AuthUser? {
        return authUserService.findByEmail(email)
    }

    @GetMapping("/phone/{phone}")
    fun findByPhone(@PathVariable phone: String): AuthUser? {
        return authUserService.findByPhone(phone)
    }

    @GetMapping("/username/{username}")
    fun findByUsername(@PathVariable username: String): AuthUser? {
        return authUserService.findByUsername(username)
    }
}
