package com.gewuyou.baseforge.autoconfigure.web.config;

import com.gewuyou.baseforge.autoconfigure.web.interceptor.AccessLimitInterceptor;
import com.gewuyou.baseforge.autoconfigure.web.interceptor.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置
 *
 * @author gewuyou
 * @since 2024-11-13 11:01:23
 */
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final AccessLimitInterceptor accessLimitInterceptor;
    private final PaginationInterceptor paginationInterceptor;

    public WebMvcConfiguration(
            AccessLimitInterceptor accessLimitInterceptor,
            PaginationInterceptor paginationInterceptor
    ) {
        this.accessLimitInterceptor = accessLimitInterceptor;
        this.paginationInterceptor = paginationInterceptor;
    }

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations and resource handler requests.
     * Interceptors can be registered to apply to all requests or be limited
     * to a subset of URL patterns.
     *
     * @param registry the InterceptorRegistry to add interceptors to
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        log.info("开始注册拦截器");
        log.info("添加访问限制拦截器");
        registry.addInterceptor(accessLimitInterceptor);
        log.info("添加分页拦截器");
        registry.addInterceptor(paginationInterceptor);
        log.info("注册拦截器完成");
    }
}
