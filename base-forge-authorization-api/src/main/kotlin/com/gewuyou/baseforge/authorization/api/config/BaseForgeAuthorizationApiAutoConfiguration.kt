package com.gewuyou.baseforge.authorization.api.config

import com.gewuyou.baseforge.authorization.api.client.BaseForgeAuthorizationOpenfeignClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 *Base Forge 授权服务 API 自动配置
 *
 * @since 2025-01-24 10:36:47
 * @author gewuyou
 */
@Configuration
@EnableFeignClients(basePackageClasses = [BaseForgeAuthorizationOpenfeignClient::class])
@ConditionalOnClass(EnableFeignClients::class)
@ComponentScan(basePackageClasses = [BaseForgeAuthorizationOpenfeignClient::class])
class BaseForgeAuthorizationApiAutoConfiguration