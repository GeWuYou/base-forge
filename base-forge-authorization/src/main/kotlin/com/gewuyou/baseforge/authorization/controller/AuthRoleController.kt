package com.gewuyou.baseforge.authorization.controller

import com.gewuyou.baseforge.entities.web.annotation.ApiVersion
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@RestController
@ApiVersion
@RequestMapping("/auth/z/role")
class AuthRoleController
