package com.gewuyou.baseforge.authorization.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.authorization.mapper.AuthUserMapper
import com.gewuyou.baseforge.authorization.model.AuthUser
import com.gewuyou.baseforge.authorization.repository.AuthUserRepository
import com.gewuyou.baseforge.authorization.service.AuthUserService
import org.springframework.stereotype.Service

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Service
class AuthUserServiceImpl(
    private val authUserRepository: AuthUserRepository
) : ServiceImpl<AuthUserMapper, AuthUser>(), AuthUserService{
    override fun findByEmail(email: String): AuthUser? {
        return authUserRepository.findByEmail(email)
    }

    override fun findByPhone(phone: String): AuthUser? {
        return authUserRepository.findByPhone(phone)
    }

    override fun findByUsername(username: String): AuthUser? {
        return authUserRepository.findByUsername(username)
    }

}
