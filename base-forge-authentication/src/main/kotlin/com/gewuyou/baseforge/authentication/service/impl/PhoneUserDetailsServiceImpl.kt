package com.gewuyou.baseforge.authentication.service.impl

import com.gewuyou.baseforge.authentication.entity.UserDetailsEntity
import com.gewuyou.baseforge.authorization.api.client.BaseForgeAuthorizationOpenfeignClient
import com.gewuyou.baseforge.security.authentication.autoconfigure.service.UserDetailsService
import com.gewuyou.baseforge.security.authentication.entities.entity.UserDetails
import org.springframework.stereotype.Service

/**
 *电话用户详细信息服务实现
 *
 * @since 2025-02-17 23:44:07
 * @author gewuyou
 */
@Service("phoneUserDetailsService")
class PhoneUserDetailsServiceImpl(
    private val authorizationOpenfeignClient: BaseForgeAuthorizationOpenfeignClient
) : UserDetailsService {
    /**
     * 根据用户身份标识获取用户
     * @param principal 用户身份标识 通常为用户名 手机号 邮箱等
     * @return 用户详情 不能为空
     */
    override fun loadUserByPrincipal(principal: Any): UserDetails {
        // 先获取用户认证信息
        val authUser = authorizationOpenfeignClient.findByPhone(principal as String)
            ?: throw IllegalArgumentException("用户不存在")
        val userAuthId = authUser.id
        // 再获取用户角色信息
        val roles = authorizationOpenfeignClient.getRoleByUserAuthId(userAuthId)
        // 再装配用户详细信息
        return UserDetailsEntity(userAuthId, authUser.username, authUser.password, principal, roles, authUser.isEnabled)
    }
}