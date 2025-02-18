package com.gewuyou.common

import java.security.SecureRandom
import java.util.*

/**
 *JWT 密钥生成器
 *
 * @since 2025-02-12 23:46:19
 * @author gewuyou
 */
class JwtSecretKeyGenerator {
    companion object {
        private fun generateSecretKey(length: Int=32): String {
            val key = ByteArray(length)
            SecureRandom().nextBytes(key)
            return Base64.getEncoder().encodeToString(key)
        }
        @JvmStatic
        fun main(args: Array<String>) {
            val secretKey = generateSecretKey()
            println(secretKey)
        }
    }

}