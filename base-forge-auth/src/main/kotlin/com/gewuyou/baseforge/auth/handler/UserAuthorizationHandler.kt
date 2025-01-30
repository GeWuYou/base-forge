package com.gewuyou.baseforge.auth.handler

import com.gewuyou.baseforge.auth.constant.AuthConstant
import com.gewuyou.baseforge.auth.service.AuthPermissionService
import com.gewuyou.baseforge.security.authorization.autoconfigure.handler.AuthorizationHandler
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.stereotype.Component
import java.util.function.Supplier

/**
 *用户授权处理程序
 *
 * @since 2025-01-11 15:22:02
 * @author gewuyou
 */
@Component
class UserAuthorizationHandler(private val authPermissionService: AuthPermissionService) : AuthorizationHandler {
    /**
     * 处理授权
     * @param authentication 认证信息
     * @param context 请求授权上下文
     * @return 授权决策
     */
    override fun handle(
        authentication: Supplier<Authentication>,
        context: RequestAuthorizationContext
    ): AuthorizationDecision {
        // 获取请求路径和请求方法
        val request = context.request
        val method = request.method
        val url = request.requestURI
        // 创建key
        val key = AuthConstant.PERMISSION_CACHE_PREFIX + "$url:$method"
        // 获取当前用户的权限列表
        val userPermissions = authentication
            .get()
            .authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .toList()
        // 获取权限列表
        val permissions = authPermissionService.getPermissionsByKey(key)
        // 判断用户是否有权限
        userPermissions.forEach {
            // 如果用户有权限，则返回true
            if (permissions.contains(it)) {
                return AuthorizationDecision(true)
            }
        }
        // 如果用户没有权限，则返回false
        return AuthorizationDecision(false)
    }
}