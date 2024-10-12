package com.gewuyou.shared.event;


import com.gewuyou.common.model.ExceptionLog;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 异常日志事件
 *
 * @author gewuyou
 * @since 2024-05-04 上午12:23:35
 */
@Getter
public class ExceptionLogEvent extends ApplicationEvent {
    private final ExceptionLog exceptionLog;

    public ExceptionLogEvent(ExceptionLog exceptionLog) {
        super(exceptionLog);
        this.exceptionLog = exceptionLog;
    }
}
