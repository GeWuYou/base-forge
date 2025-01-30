package com.gewuyou.baseforge.auth.service.impl

import com.gewuyou.baseforge.jwt.autoconfigure.service.JwtService
import com.gewuyou.baseforge.jwt.entities.entity.JwtPayloadData
import com.gewuyou.baseforge.security.authentication.autoconfigure.service.JwtAuthenticationService
import com.gewuyou.baseforge.security.authorization.autoconfigure.service.JwtAuthorizationService
import org.springframework.stereotype.Service

/**
 *JWT 服务实现
 *
 * @since 2025-01-11 13:23:02
 * @author gewuyou
 */
@Service
class JwtServiceImpl(
    private val jwtService: JwtService
) : JwtAuthenticationService, JwtAuthorizationService {
    /**
     * 生成访问token
     *
     * @param principal 用户唯一标识
     * @param deviceId  设备唯一标识
     * @param otherClaims 其他声明
     * @return java.lang.String token
     * @apiNote
     * @since 2023/7/2 19:45
     */
    override fun generateToken(principal: String, deviceId: String, otherClaims: Map<String, Any>?): String {
        return jwtService.generateToken(principal, deviceId, otherClaims)
    }

    /**
     * 将访问token加入黑名单
     * @param principal 用户唯一标识
     * @param deviceId  设备唯一标识
     */
    override fun addAccessTokenToBlacklist(principal: String, deviceId: String, token: String) {
        jwtService.addAccessTokenToBlacklist(principal, deviceId, token)
    }

    /**
     * 生成刷新token
     *
     * @param principal 用户唯一标识
     * @param deviceId  设备唯一标识
     * @return java.lang.String 刷新令牌
     * @since 2023/7/2 19:45
     */
    override fun generateRefreshToken(principal: String, deviceId: String): String {
        return jwtService.generateRefreshToken(principal, deviceId)
    }

    /**
     * 移除刷新token
     * @param principal 用户唯一标识
     * @param deviceId  设备唯一标识
     */
    override fun removeRefreshToken(principal: String, deviceId: String, token: String) {
        return jwtService.removeRefreshToken(principal, deviceId, token)
    }

    /**
     * 验证token`
     * @param token 访问token
     * @return JwtPayloadData 有效载荷数据 如果token无效则返回null
     */
    override fun validateToken(token: String): JwtPayloadData? {
        return jwtService.validateToken(token)
    }
}