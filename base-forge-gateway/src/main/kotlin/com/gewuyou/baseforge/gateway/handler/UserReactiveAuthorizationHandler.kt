package com.gewuyou.baseforge.gateway.handler

import com.gewuyou.baseforge.authorization.api.client.BaseForgeAuthorizationOpenfeignClient
import com.gewuyou.baseforge.gateway.constant.GatewayConstant
import com.gewuyou.baseforge.security.authorization.autoconfigure.handler.ReactiveAuthorizationHandler
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/**
 *用户反应式授权处理程序
 *
 * @since 2025-02-03 19:22:41
 * @author gewuyou
 */
@Component
class UserReactiveAuthorizationHandler(
    private val authorizationOpenfeignClient: BaseForgeAuthorizationOpenfeignClient
) : ReactiveAuthorizationHandler {
    /**
     * 处理授权
     * @param authentication 认证信息
     * @param context 授权上下文
     * @return 授权决策
     */
    override fun handle(
        authentication: Mono<Authentication>,
        context: AuthorizationContext
    ): Mono<AuthorizationDecision> {
        // 获取请求路径和请求方法
        val request = context.exchange.request
        val url = request.uri.path
        val method = request.method.name()
        // 创建key
        val key = GatewayConstant.PERMISSION_CACHE_PREFIX + "$url:$method"
        // 获取当前用户的权限列表
        return authentication.flatMap {
            val userPermissions = it.authorities.stream().map(GrantedAuthority::getAuthority)
                .toList()
            // 获取权限列表
            val permissions = authorizationOpenfeignClient.getPermissionsByKey(key)
            // 判断用户是否有权限
            if (userPermissions.any(permissions::contains)) {
                Mono.just(AuthorizationDecision(true))
            } else {
                // 如果用户没有权限，则返回false
                Mono.just(AuthorizationDecision(false))
            }
        }
    }
}