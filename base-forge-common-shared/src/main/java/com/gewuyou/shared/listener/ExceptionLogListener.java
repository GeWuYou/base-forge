package com.gewuyou.shared.listener;


import com.gewuyou.common.model.ExceptionLog;
import com.gewuyou.log.client.ILogClientGrpc;
import com.gewuyou.shared.event.ExceptionLogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异常日志监听器
 *
 * @author gewuyou
 * @since 2024-05-04 上午12:25:38
 */
@Component
public class ExceptionLogListener implements ApplicationListener<ExceptionLogEvent> {
    private final ILogClientGrpc logClient;

    @Autowired
    public ExceptionLogListener(ILogClientGrpc logClient) {
        this.logClient = logClient;
    }

    /**
     * Handle an application event.
     *
     * @param exceptionLogEvent the event to respond to
     */
    @Async
    @Override
    public void onApplicationEvent(@NonNull ExceptionLogEvent exceptionLogEvent) {
        logClient.saveExceptionLog((ExceptionLog) exceptionLogEvent.getSource());
    }
}
