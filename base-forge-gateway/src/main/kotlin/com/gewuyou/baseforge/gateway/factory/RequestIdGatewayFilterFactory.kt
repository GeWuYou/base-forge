package com.gewuyou.baseforge.gateway.factory

import com.gewuyou.baseforge.gateway.filter.RequestIdGatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

/**
 *请求 ID 过滤器工厂
 *
 * @since 2025-01-20 21:54:00
 * @author gewuyou
 */
@Component
class RequestIdGatewayFilterFactory:AbstractGatewayFilterFactory<RequestIdGatewayFilterFactory.Config>(Config::class.java) {

    class Config

    override fun apply(config: Config): GatewayFilter {
        return RequestIdGatewayFilter()
    }
}