package com.gewuyou.shared.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gewuyou.common.annotation.OperationLogging;
import com.gewuyou.common.enums.ResponseInformation;
import com.gewuyou.common.model.ExceptionLog;
import com.gewuyou.shared.event.ExceptionLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 异常日志切面
 *
 * @author gewuyou
 * @since 2024-05-04 上午12:08:56
 */
@Aspect
@Component
@Slf4j
public class ExceptionLogAspect {
    private final ObjectMapper objectMapper;

    private final ApplicationContext applicationContext;

    @Autowired
    public ExceptionLogAspect(ObjectMapper objectMapper, ApplicationContext applicationContext) {
        this.objectMapper = objectMapper;
        this.applicationContext = applicationContext;
    }

    @Pointcut("execution(* com.gewuyou.*.*.controller..*.*(..))")
    public void exceptionLogPointcut() {
    }

    @AfterThrowing(value = "exceptionLogPointcut()", throwing = "e")
    private void buildAndSaveLog(JoinPoint joinPoint, Exception e) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 检查方法是否有 @OperationLogging 注解
        if (method.isAnnotationPresent(OperationLogging.class)) {
            // 如果有，直接返回，不记录异常日志
            return;
        }
        try {
            ExceptionLog exceptionLog = ExceptionLog.toExceptionLog(joinPoint,e,objectMapper);
            applicationContext.publishEvent(new ExceptionLogEvent(exceptionLog));
        } catch (Exception ex) {
            log.error("{}", ResponseInformation.LOG_BUILD_FAILED, ex);
        }
    }
}
