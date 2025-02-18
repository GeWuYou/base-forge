package com.gewuyou.baseforge.authentication.entity

import com.gewuyou.baseforge.authorization.model.AuthRole
import com.gewuyou.baseforge.security.authentication.entities.entity.UserDetails
import org.springframework.security.core.GrantedAuthority


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
    /**
     * 用户身份标识
     */
    private val principal: Any,
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

    override fun getCredentials(): Any {
        return this.password
    }

    override fun getPrincipal(): Any {
       return this.principal
    }

    override fun getUserOnlyIdentity(): Any {
        return this.userAuthId
    }


}