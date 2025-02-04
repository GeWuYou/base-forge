package com.gewuyou.baseforge.authentication.service.impl

import com.gewuyou.baseforge.authentication.constant.AuthenticationConstant
import com.gewuyou.baseforge.authentication.entity.UserDetailsEntity
import com.gewuyou.baseforge.authorization.api.client.BaseForgeAuthorizationOpenfeignClient
import com.gewuyou.baseforge.authorization.model.AuthUser
import com.gewuyou.baseforge.dictionary.api.client.BaseForgeDataDictionaryOpenfeignClient
import com.gewuyou.baseforge.security.authentication.autoconfigure.service.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

/**
 *用户详细信息实现
 *
 * @since 2025-01-10 16:17:51
 * @author gewuyou
 */
@Service
class UserDetailsServiceImpl(
    private val dataDictionaryOpenfeignClient: BaseForgeDataDictionaryOpenfeignClient,
    private val authorizationOpenfeignClient: BaseForgeAuthorizationOpenfeignClient,
) :  AuthenticationUserDetailsService {
    /**
     * 根据用户唯一标识查询用户唯一标识
     * @param principal 用户唯一标识 通常为用户名 手机号 邮箱等
     * @apiNote 用于支持多种唯一标识登录时，根据登录时用户唯一标识查询用户确定的唯一标识
     * @return 用户唯一标识 此实现使用用户id作为用户确定的唯一标识
     */
    override fun getUserPrincipal(principal: Any): String? {
       return getAuthUser(principal).id.toString()
    }
    private fun getAuthUser(principal: Any): AuthUser {
        // 从数据字典中获取邮箱正则
        val emailRegular = dataDictionaryOpenfeignClient.getDictionaryItemByCode(
            AuthenticationConstant.PHONE_ITEM_NAME,
            AuthenticationConstant.AUTH_DICTIONARY_CATEGORY_NAME
        )?.value?.toRegex()
        // 从数据字典中获取手机号正则
        val phoneRegular = dataDictionaryOpenfeignClient.getDictionaryItemByCode(
            AuthenticationConstant.EMAIL_ITEM_NAME,
            AuthenticationConstant.AUTH_DICTIONARY_CATEGORY_NAME
        )?.value?.toRegex()
        // 转换为字符串
        val principalStr = principal.toString()
        // 判断是否匹配邮箱正则
        if (emailRegular!= null && principalStr.matches(emailRegular)) {
            // 调用根据邮箱获取用户authid方法
            authorizationOpenfeignClient.findByEmail(principalStr)?.let {
                return it
            }?: throw IllegalArgumentException("用户不存在")
        }else if (phoneRegular!= null && principalStr.matches(phoneRegular)) {
            // 调用根据手机号获取用户id方法
            authorizationOpenfeignClient.findByPhone(principalStr)?.let {
                return it
            }?: throw IllegalArgumentException("用户不存在")
        }else {
            // 调用根据用户名获取用户id方法
           authorizationOpenfeignClient.findByUsername(principalStr)?.let {
                return it
            }?: throw IllegalArgumentException("用户不存在")
        }
    }

    /**
     * 根据用户唯一标识查询用户详细信息
     * @param principal 用户唯一标识 通常为用户名 手机号 邮箱等
     * @return UserDetails 用户详细信息
     */
    override fun loadUserByPrincipal(principal: Any): UserDetails? {
        // 先获取用户认证信息
        val authUser = getAuthUser(principal)
        val userAuthId = authUser.id
        // 再获取用户角色信息
        val roles = authorizationOpenfeignClient.getRoleByUserAuthId(userAuthId)
        // 再装配用户详细信息
        return UserDetailsEntity(userAuthId,authUser.username,authUser.password,roles,authUser.isEnabled)
    }
}