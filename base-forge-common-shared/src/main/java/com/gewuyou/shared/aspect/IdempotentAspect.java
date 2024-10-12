package com.gewuyou.shared.aspect;


import com.gewuyou.cache.client.ICacheClientGrpc;
import com.gewuyou.common.annotation.Idempotent;
import com.gewuyou.common.enums.ResponseInformation;
import com.gewuyou.common.exception.GlobalSharedException;
import com.gewuyou.common.utils.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 幂等切面
 *
 * @author gewuyou
 * @since 2024-07-20 上午12:00:39
 */
@Aspect
@Component
@Slf4j
public class IdempotentAspect {
    private final ICacheClientGrpc cacheClientGrpc;
    private final HttpServletRequest request;

    @Autowired
    public IdempotentAspect(ICacheClientGrpc cacheClientGrpc, HttpServletRequest request) {
        this.cacheClientGrpc = cacheClientGrpc;
        this.request = request;
    }

    @Around("@annotation(idempotent)")
    public Object around(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        String key = getIdempotentKey(joinPoint, request);

        // 尝试在 Redis 中设置唯一键，设置成功表示首次请求，否则是重复请求
        Boolean isFirstRequest =  cacheClientGrpc.sIfAbsentBySec(key,Boolean.TRUE.toString(),idempotent.delay());

        if (Boolean.FALSE.equals(isFirstRequest)) {
            // 如果键已存在，表示重复请求
            throw new GlobalSharedException(ResponseInformation.REPEAT_REQUEST);
        }

        try {
            return joinPoint.proceed();
        } finally {
            cacheClientGrpc.delayedDeleteBySec(key,idempotent.delay());
        }
    }

    private String getIdempotentKey(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
        // 根据请求参数生成唯一键，可以根据实际情况调整
        // 使用 ip地址、uri方法名和参数的哈希值作为键
        var ip = IpUtil.getIpAddress(request);
        log.info("ip: {}", ip);
        var methodName = joinPoint.getSignature().getName();
        var params = Arrays.toString(joinPoint.getArgs());
        return "idempotent:" + ip + ":" + methodName + ":" + params.hashCode();
    }
}
