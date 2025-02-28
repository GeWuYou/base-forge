package com.gewuyou.baseforge.security.authentication.autoconfigure.service

import org.springframework.security.core.userdetails.UserDetails


/**
 * 认证用户详细信息服务接口
 *
 * @author gewuyou
 * @since 2024-11-05 19:34:50
 */
interface AuthenticationUserDetailsService {
    /**
     * 根据用户唯一标识查询用户详细信息
     * @param principal 用户唯一标识 通常为用户名 手机号 邮箱等
     * @return UserDetails 用户详细信息
     */
    fun loadUserByPrincipal(principal: Any): UserDetails?

    /**
     * 根据用户唯一标识查询用户唯一标识
     * @param principal 用户唯一标识 通常为用户名 手机号 邮箱等
     * @apiNote 用于支持多种唯一标识登录时，根据登录时用户唯一标识查询用户确定的唯一标识
     * @return 用户唯一标识
     */
    fun getUserPrincipal(principal: Any): String?
}
