package com.gewuyou.shared.listener;


import com.gewuyou.common.model.OperationLog;
import com.gewuyou.log.client.ILogClientGrpc;
import com.gewuyou.shared.event.OperationLogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 操作日志监听器
 *
 * @author gewuyou
 * @since 2024-05-03 下午11:40:07
 */
@Component
public class OperationLogListener implements ApplicationListener<OperationLogEvent> {
    private final ILogClientGrpc logClient;

    @Autowired
    public OperationLogListener(ILogClientGrpc logClient) {
        this.logClient = logClient;
    }

    /**
     * Handle an application event.
     *
     * @param operationLogEvent the event to respond to
     */
    @Async
    @Override
    public void onApplicationEvent(@NonNull OperationLogEvent operationLogEvent) {
        logClient.saveOperationLog((OperationLog) operationLogEvent.getSource());
    }
}
