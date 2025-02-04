package com.gewuyou.baseforge.authentication.entity

import com.gewuyou.baseforge.authorization.model.AuthRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 *用户详细信息实体
 *
 * @since 2025-01-10 16:58:43
 * @author gewuyou
 */
data class UserDetailsEntity(
    val userAuthId: Long,
    private val username: String,
    private val password: String,
    val roles: List<AuthRole>,
    val enabled: Boolean,
) : UserDetails {
    /**
     * Returns the authorities granted to the user. Cannot return `null`.
     * @return the authorities, sorted by natural key (never `null`)
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.roles.stream().map{GrantedAuthorityEntity(it.roleName)}.toList()
    }

    /**
     * Returns the password used to authenticate the user.
     * @return the password
     */
    override fun getPassword(): String {
        return this.password
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * `null`.
     * @return the username (never `null`)
     */
    override fun getUsername(): String {
        return this.username
    }
}