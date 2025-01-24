package com.gewuyou.baseforge.dictionary.api.config

import com.gewuyou.baseforge.dictionary.api.client.BaseForgeDataDictionaryOpenfeignClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 *Base Forge 数据字典 API 自动配置
 *
 * @since 2025-01-24 10:36:47
 * @author gewuyou
 */
@Configuration
@EnableFeignClients(basePackageClasses = [BaseForgeDataDictionaryOpenfeignClient::class])
@ConditionalOnClass(EnableFeignClients::class)
@ComponentScan(basePackageClasses = [BaseForgeDataDictionaryOpenfeignClient::class])
class BaseForgeDataDictionaryApiAutoConfiguration