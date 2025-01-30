package com.gewuyou.baseforge.gateway.filter

import com.gewuyou.baseforge.autoconfigure.util.RequestIdUtil
import com.gewuyou.baseforge.core.constants.WebCommonConstant
import org.slf4j.MDC
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpMethod
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * 请求 ID 网关过滤器
 *
 * @since 2025-01-20 09:34:53
 * @author gewuyou
 */
class RequestIdGatewayFilter : GatewayFilter {
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val request = exchange.request
        // 检测请求是否需要跳过
        if (shouldSkipRequest(request)) {
            return chain.filter(exchange)
        }
        request.headers[WebCommonConstant.REQUEST_ID_HEADER]?.let {
            // 如果请求头中已有 requestId，直接设置到 RequestIdUtil 中
            it.firstOrNull()?.let(RequestIdUtil::setRequestId) ?: RequestIdUtil.generateRequestId().also {
                request.mutate()
                    // 将 requestId 添加到请求头
                    .header(WebCommonConstant.REQUEST_ID_HEADER, RequestIdUtil.getRequestId())
                    .build()
            }
        }
        // 获取当前的 requestId
        val currentRequestId = RequestIdUtil.getRequestId()
        // 将 requestId 设置到日志中
        MDC.put(WebCommonConstant.REQUEST_ID_MDC_KEY, currentRequestId)
        // 转发请求
        return chain.filter(exchange)
            .doFinally {
                // 清理 MDC 中的 requestId，避免内存泄漏
                MDC.remove(WebCommonConstant.REQUEST_ID_MDC_KEY)
                // 将 requestId 清除
                RequestIdUtil.removeRequestId()
            }
    }

    /**
     * 检查请求是否需要跳过
     * @param httpRequest 请求对象
     * @return true 跳过，false 不跳过
     */
    private fun shouldSkipRequest(httpRequest: ServerHttpRequest): Boolean {
        val method = httpRequest.method
        return when {
            // 跳过 OPTIONS 请求
            HttpMethod.OPTIONS == method -> true
            // 跳过 静态资源请求 (例如图片、CSS、JavaScript 文件)
            HttpMethod.GET == method && httpRequest.uri.path.matches(Regex(".*\\.(css|js|png|jpg|jpeg|gif|svg)")) -> true
            // 跳过 HEAD 请求
            HttpMethod.HEAD == method -> true
            // 跳过 TRACE 请求
            HttpMethod.TRACE == method -> true
            // 跳过健康检查请求
            httpRequest.uri.path.startsWith("/actuator/health") || httpRequest.uri.path == "/health" -> true
            else -> false
        }
    }
}