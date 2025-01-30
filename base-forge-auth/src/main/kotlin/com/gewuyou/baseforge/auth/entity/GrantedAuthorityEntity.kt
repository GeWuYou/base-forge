package com.gewuyou.baseforge.auth.entity

import org.springframework.security.core.GrantedAuthority

/**
 *已授予的权限实体
 *
 * @since 2025-01-27 13:38:26
 * @author gewuyou
 */
data class GrantedAuthorityEntity(
    val roleName: String,
): GrantedAuthority {
    /**
     * 获取权限字符串
     * @return 权限字符串
     */
    override fun getAuthority(): String {
        return "$roleName:"
    }
}