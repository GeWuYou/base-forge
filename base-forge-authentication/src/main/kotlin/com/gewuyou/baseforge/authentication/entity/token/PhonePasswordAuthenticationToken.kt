package com.gewuyou.baseforge.authentication.entity.token

import com.gewuyou.baseforge.security.authentication.entities.token.PrincipalCredentialsAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 *电话密码认证 Token
 *
 * @since 2025-02-16 15:49:26
 * @author gewuyou
 */
class PhonePasswordAuthenticationToken(
    principal: Any,
    credentials: Any?,
    authorities: Collection<GrantedAuthority>? = null
) :
    PrincipalCredentialsAuthenticationToken(principal, credentials, authorities) {
    companion object {
        /**
         * 创建未认证的令牌
         */
        @JvmStatic
        fun unauthenticated(principal: Any, credentials: Any): PhonePasswordAuthenticationToken {
            return PhonePasswordAuthenticationToken(principal, credentials)
        }

        /**
         * 创建已认证的令牌
         */
        @JvmStatic
        fun authenticated(
            principal: Any,
            credentials: Any?,
            authorities: Collection<GrantedAuthority>
        ): PhonePasswordAuthenticationToken {
            return PhonePasswordAuthenticationToken(principal, credentials, authorities)
        }
    }
}
