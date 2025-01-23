package com.gewuyou.baseforge.autoconfigure.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gewuyou.baseforge.autoconfigure.i18n.config.I18nAutoConfiguration;
import com.gewuyou.baseforge.autoconfigure.web.aspect.IdempotentAspect;
import com.gewuyou.baseforge.autoconfigure.web.aspect.MethodRecordingAspect;
import com.gewuyou.baseforge.autoconfigure.web.aspect.ReadWriteLockAspect;
import com.gewuyou.baseforge.autoconfigure.web.config.entity.PageProperties;
import com.gewuyou.baseforge.autoconfigure.web.config.entity.ReadWriteLockProperties;
import com.gewuyou.baseforge.autoconfigure.web.filter.RequestFilter;
import com.gewuyou.baseforge.autoconfigure.web.handler.GlobalExceptionHandler;
import com.gewuyou.baseforge.autoconfigure.web.interceptor.AccessLimitInterceptor;
import com.gewuyou.baseforge.autoconfigure.web.interceptor.PaginationInterceptor;
import com.gewuyou.baseforge.redis.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Web 自动 配置类
 *
 * @author gewuyou
 * @since 2025-01-19 19:29:39
 */
@Configuration
@EnableConfigurationProperties({PageProperties.class, ReadWriteLockProperties.class})
@Slf4j
public class WebAutoConfiguration {
    /**
     * 创建 AccessLimitInterceptor
     *
     * @param cacheService 缓存服务
     * @param objectMapper 对象映射器
     * @return AccessLimitInterceptor
     */
    @Bean
    public AccessLimitInterceptor createAccessLimitInterceptor(
            CacheService cacheService,
            ObjectMapper objectMapper,
            @Qualifier(I18nAutoConfiguration.MESSAGE_SOURCE_BEAN_NAME)
            MessageSource i18nMessageSource
    ) {
        log.info("创建 AccessLimitInterceptor");
        return new AccessLimitInterceptor(cacheService, objectMapper, i18nMessageSource);
    }

    /**
     * 创建 IdempotentAspect
     *
     * @param cacheService 缓存服务
     * @return IdempotentAspect
     */
    @Bean
    public IdempotentAspect idempotentAspect(CacheService cacheService) {
        log.info("创建 IdempotentAspect");
        return new IdempotentAspect(cacheService);
    }

    /**
     * 创建 MethodRecordingAspect
     * @return MethodRecordingAspect
     */
    @Bean
    public MethodRecordingAspect createMethodRecordingAspect() {
        log.info("创建 MethodRecordingAspect");
        return new MethodRecordingAspect();
    }

    /**
     * 创建 ReadWriteLockAspect
     *
     * @param redissonClient Redisson 客户端
     * @return ReadWriteLockAspect
     */
    @Bean
    @ConditionalOnProperty(name = "base-forge.web.read-write-lock.enabled", havingValue = "true")
    public ReadWriteLockAspect readWriteLockAspect(RedissonClient redissonClient) {
        log.info("创建 ReadWriteLockAspect");
        return new ReadWriteLockAspect(redissonClient);
    }

    /**
     * 创建 PaginationInterceptor
     *
     * @param pageProperties 分页配置
     * @return PaginationInterceptor
     */
    @Bean
    @ConditionalOnProperty(name = "base-forge.web.page.enabled", havingValue = "true")
    public PaginationInterceptor createPaginationInterceptor(PageProperties pageProperties) {
        log.info("创建 PaginationInterceptor");
        return new PaginationInterceptor(pageProperties);
    }

    /**
     * 创建 GlobalExceptionHandler
     *
     * @return GlobalExceptionHandler
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler(@Qualifier(I18nAutoConfiguration.MESSAGE_SOURCE_BEAN_NAME) MessageSource i18nMessageSource) {
        log.info("创建 GlobalExceptionHandler");
        return new GlobalExceptionHandler(i18nMessageSource);
    }

    @Bean
    public FilterRegistrationBean<RequestFilter> createFilterRegistrationBean() {
        log.info("创建 RequestFilter");
        FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestFilter());
        // 默认拦截所有请求
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
