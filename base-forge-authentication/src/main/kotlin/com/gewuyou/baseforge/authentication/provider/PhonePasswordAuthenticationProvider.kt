package com.gewuyou.baseforge.authentication.provider

import com.gewuyou.baseforge.authentication.entity.token.PhonePasswordAuthenticationToken
import com.gewuyou.baseforge.core.extension.log
import com.gewuyou.baseforge.security.authentication.autoconfigure.provider.AbstractPrincipalPasswordAuthenticationProvider
import com.gewuyou.baseforge.security.authentication.autoconfigure.service.UserCacheService
import com.gewuyou.baseforge.security.authentication.autoconfigure.service.UserDetailsService
import com.gewuyou.baseforge.security.authentication.entities.entity.UserDetails
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 *电话密码认证提供者
 *
 * @since 2025-02-17 23:22:58
 * @author gewuyou
 */
@Service
class PhonePasswordAuthenticationProvider(
    userCacheService: UserCacheService,
    @Qualifier("phoneUserDetailsService")
    userDetailsService: UserDetailsService,
    passwordEncoder: PasswordEncoder
) : AbstractPrincipalPasswordAuthenticationProvider(userCacheService, userDetailsService, passwordEncoder) {
    /**
     * Returns `true` if this <Code>AuthenticationProvider</Code> supports the
     * indicated <Code>Authentication</Code> object.
     *
     *
     * Returning `true` does not guarantee an
     * `AuthenticationProvider` will be able to authenticate the presented
     * `Authentication` object. It simply indicates it can support closer
     * evaluation of it. An `AuthenticationProvider` can still return
     * `null` from the [.authenticate] method to indicate
     * another `AuthenticationProvider` should be tried.
     *
     *
     *
     * Selection of an `AuthenticationProvider` capable of performing
     * authentication is conducted at runtime the `ProviderManager`.
     *
     * @param authentication
     * @return `true` if the implementation can more closely evaluate the
     * `Authentication` class presented
     */
    override fun supports(authentication: Class<*>): Boolean {
        return PhonePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

    /**
     * 创建成功的认证
     *
     * @param user           用户详情
     * @param authentication 认证
     * @return 成功的认证
     */
    override fun createSuccessAuthentication(authentication: Authentication, user: UserDetails): Authentication {
        val result =
            PhonePasswordAuthenticationToken.authenticated(user, authentication.credentials, user.getAuthorities())
        log.debug("身份验证成功 用户: {}", result.name)
        return result
    }
}