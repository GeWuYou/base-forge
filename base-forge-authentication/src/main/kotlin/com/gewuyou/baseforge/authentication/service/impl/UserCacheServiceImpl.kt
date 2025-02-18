package com.gewuyou.baseforge.authentication.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.gewuyou.baseforge.authentication.constant.AuthenticationConstant
import com.gewuyou.baseforge.authentication.entity.UserDetailsEntity
import com.gewuyou.baseforge.redis.service.CacheService
import com.gewuyou.baseforge.security.authentication.autoconfigure.service.UserCacheService
import com.gewuyou.baseforge.security.authentication.entities.entity.UserDetails


import org.springframework.stereotype.Service

/**
 *用户缓存 实现
 *
 * @since 2025-01-25 09:31:31
 * @author gewuyou
 */
@Service
class UserCacheServiceImpl(
    private val cacheService: CacheService,
    private val objectMapper: ObjectMapper
) : UserCacheService {
    /**
     * The expiration time for the cache entries, in seconds.
     */
    private val expiration: Long = 5 * 60

    /**
     * 从缓存中获取用户信息
     * @param principal 用户标识
     * @return 用户信息 返回null表示缓存中没有该用户信息或信息已过期
     */
    override fun getUserFromCache(principal: String): UserDetails? {
        return principal.let {
            objectMapper.readValue(
                cacheService.get(AuthenticationConstant.USER_CACHE_PREFIX + it).toString(),
                UserDetailsEntity::class.java
            )
        }
    }

    /**
     * 将用户信息缓存到缓存中 注意，请将过期时间设置得尽可能短，以防止缓存与数据库出现数据不一致
     * @param userDetails 用户信息
     */
    override fun putUserToCache(userDetails: UserDetails) {
        userDetails.let {
            cacheService.set(
                AuthenticationConstant.USER_CACHE_PREFIX + userDetails.getPrincipal(),
                objectMapper.writeValueAsString(userDetails),
                expiration
            )
        }
    }

    /**
     * 从缓存中移除用户信息
     * @param principal 用户标识
     */
    override fun removeUserFromCache(principal: String) {
        principal.let {
            cacheService.delete(AuthenticationConstant.USER_CACHE_PREFIX + it)
        }
    }
}