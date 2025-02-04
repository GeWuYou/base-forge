package com.gewuyou.baseforge.authorization.service

import com.baomidou.mybatisplus.extension.service.IService
import com.gewuyou.baseforge.authorization.model.AuthUser

/**
 * <p>
 * 用户认证表 服务类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
interface AuthUserService : IService<AuthUser>{
    fun findByEmail(email: String): AuthUser?
    fun findByPhone(phone: String): AuthUser?
    fun findByUsername(username: String): AuthUser?
}
