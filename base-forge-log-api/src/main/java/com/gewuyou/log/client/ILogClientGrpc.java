package com.gewuyou.log.client;

import com.gewuyou.common.model.ExceptionLog;
import com.gewuyou.common.model.OperationLog;

/**
 * 日志Grpc客户端接口
 *
 * @author gewuyou
 * @since 2024-10-05 19:04:31
 */
public interface ILogClientGrpc {
    /**
     * 保存异常日志
     * @param exceptionLog 异常日志
     * @return 保存成功的异常日志对象
     */
    ExceptionLog saveExceptionLog(ExceptionLog exceptionLog);

    /**
     * 保存操作日志
     * @param operationLog 操作日志
     * @return 保存成功的操作日志对象
     */
    OperationLog saveOperationLog(OperationLog operationLog);
}
