package com.gewuyou.baseforge.authentication.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.gewuyou.baseforge.authentication.constant.AuthenticationConstant
import com.gewuyou.baseforge.authentication.entity.UserDetailsEntity
import com.gewuyou.baseforge.redis.service.CacheService
import org.springframework.security.core.userdetails.UserCache
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

/**
 *用户缓存 实现
 *
 * @since 2025-01-25 09:31:31
 * @author gewuyou
 */
@Service
class UserCacheImpl(
    private val cacheService: CacheService,
    private val objectMapper: ObjectMapper
) : UserCache {
    /**
     * The expiration time for the cache entries, in seconds.
     */
    private val expiration: Long = 60 * 60
    /**
     * Obtains a [UserDetails] from the cache.
     * @param username the User.getUsername used to place the user in the cache
     * @return the populated `UserDetails` or `null` if the user
     * could not be found or if the cache entry has expired
     */
    override fun getUserFromCache(username: String?): UserDetails? {
        return username?.let {
            objectMapper.readValue(
                cacheService.get(AuthenticationConstant.USER_CACHE_PREFIX + username).toString(),
                UserDetailsEntity::class.java
            )
        }
    }

    /**
     * Places a [UserDetails] in the cache. The `username` is the key
     * used to subsequently retrieve the `UserDetails`.
     * @param user the fully populated `UserDetails` to place in the cache
     */
    override fun putUserInCache(user: UserDetails?) {
        user?.let {
            cacheService.set(AuthenticationConstant.USER_CACHE_PREFIX + user.username, objectMapper.writeValueAsString(user), expiration)
        }
    }

    /**
     * Removes the specified user from the cache. The `username` is the key
     * used to remove the user. If the user is not found, the method should simply return     * (not thrown an exception).
     *
     *
     * Some cache implementations may not support eviction from the cache, in which case
     * they should provide appropriate behaviour to alter the user in either its
     * documentation, via an exception, or through a log message.
     * @param username to be evicted from the cache
     */
    override fun removeUserFromCache(username: String?) {
        username?.let {
            cacheService.delete(AuthenticationConstant.USER_CACHE_PREFIX + username)
        }
    }
}