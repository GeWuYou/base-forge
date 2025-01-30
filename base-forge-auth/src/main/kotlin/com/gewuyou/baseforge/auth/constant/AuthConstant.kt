package com.gewuyou.baseforge.auth.constant

/**
 *auth 常量
 *
 * @since 2025-01-25 09:37:13
 * @author gewuyou
 */
data object AuthConstant {
    /**
     * 用户缓存前缀
     */
    const val USER_CACHE_PREFIX = "auth:user:"

    /**
     * 权限缓存前缀
     */
    const val PERMISSION_CACHE_PREFIX = "auth:permission:"

    /**
     * 安全认证字典分类名称
     */
    const val AUTH_DICTIONARY_CATEGORY_NAME = "安全认证"

    /**
     * 邮箱字典项名
     */
    const val EMAIL_ITEM_NAME = "email"

    /**
     * 手机字典项名
     */
    const val PHONE_ITEM_NAME = "phone"
}