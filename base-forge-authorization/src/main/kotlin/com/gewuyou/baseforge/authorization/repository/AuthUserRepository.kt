package com.gewuyou.baseforge.authorization.repository

import com.gewuyou.baseforge.authorization.model.AuthUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 *安全用户存储库
 *
 * @since 2025-01-28 16:40:15
 * @author gewuyou
 */
interface AuthUserRepository: JpaRepository<AuthUser, Long>,JpaSpecificationExecutor<AuthUser> {
    fun findByEmail(email: String): AuthUser?
    fun findByPhone(phone: String): AuthUser?
    fun findByUsername(username: String): AuthUser?
}