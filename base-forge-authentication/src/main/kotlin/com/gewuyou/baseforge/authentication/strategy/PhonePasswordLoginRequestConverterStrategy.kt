package com.gewuyou.baseforge.authentication.strategy

import com.gewuyou.baseforge.authentication.entity.token.PhonePasswordAuthenticationToken
import com.gewuyou.baseforge.security.authentication.autoconfigure.strategy.LoginRequestConverterStrategy
import com.gewuyou.baseforge.security.authentication.entities.entity.request.DefaultLoginRequest
import com.gewuyou.baseforge.security.authentication.entities.entity.request.LoginRequest
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

/**
 *手机密码登录请求转换器策略
 *
 * @since 2025-02-17 23:32:13
 * @author gewuyou
 */
@Component
class PhonePasswordLoginRequestConverterStrategy: LoginRequestConverterStrategy {
    /**
     * 转换登录请求为认证对象
     * @param loginRequest 登录请求
     * @return 认证对象
     */
    override fun convert(loginRequest: LoginRequest): Authentication {
        if(loginRequest is DefaultLoginRequest){
            return PhonePasswordAuthenticationToken.unauthenticated(loginRequest.principal, loginRequest.credentials)
        }
        throw IllegalArgumentException("Unsupported login request type: ${loginRequest.javaClass.name}")
    }

    /**
     * 获取支持的登录类型
     * @return 请求的登录类型
     */
    override fun getSupportedLoginType(): String {
        return "phone"
    }
}