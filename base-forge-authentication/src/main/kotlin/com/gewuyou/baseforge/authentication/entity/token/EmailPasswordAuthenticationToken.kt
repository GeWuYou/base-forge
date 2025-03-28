package com.gewuyou.baseforge.authentication.entity.token

import com.gewuyou.baseforge.security.authentication.entities.token.PrincipalCredentialsAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 *电子邮件密码身份验证令牌
 *
 * @since 2025-02-16 17:15:04
 * @author gewuyou
 */
class EmailPasswordAuthenticationToken(
    principal: Any,
    credentials: Any?,
    authorities: Collection<GrantedAuthority>? = null
) : PrincipalCredentialsAuthenticationToken(principal, credentials, authorities) {
    companion object {
        /**
         * 创建未认证的令牌
         */
        @JvmStatic
        fun unauthenticated(principal: Any, credentials: Any): EmailPasswordAuthenticationToken {
            return EmailPasswordAuthenticationToken(principal, credentials)
        }

        /**
         * 创建已认证的令牌
         */
        @JvmStatic
        fun authenticated(
            principal: Any,
            credentials: Any?,
            authorities: Collection<GrantedAuthority>
        ): EmailPasswordAuthenticationToken {
            return EmailPasswordAuthenticationToken(principal, credentials, authorities)
        }
    }
}