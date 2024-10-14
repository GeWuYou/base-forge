package com.gewuyou.log.rpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gewuyou.common.log.LogServiceGrpc;
import com.gewuyou.common.log.LogServiceOuterClass;
import com.gewuyou.common.model.ExceptionLog;
import com.gewuyou.common.model.OperationLog;
import com.gewuyou.log.service.IExceptionLogService;
import com.gewuyou.log.service.IOperationLogService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 日志服务 gRPC 实现
 *
 * @author gewuyou
 * @since 2024-10-05 19:07:26
 */
@GrpcService
public class LogServiceGrpcImpl extends LogServiceGrpc.LogServiceImplBase {

    private final IExceptionLogService exceptionLogService;
    private final IOperationLogService operationLogService;
    private final ObjectMapper objectMapper;

    @Autowired
    public LogServiceGrpcImpl(
            IExceptionLogService exceptionLogService,
            IOperationLogService operationLogService,
            ObjectMapper objectMapper
    ) {
        super();
        this.exceptionLogService = exceptionLogService;
        this.operationLogService = operationLogService;
        this.objectMapper = objectMapper;
    }

    /**
     * <pre>
     * 保存异常日志
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void saveExceptionLog(LogServiceOuterClass.ExceptionLogEntry request, StreamObserver<LogServiceOuterClass.ExceptionLogEntry> responseObserver) {
        try {
            String exceptionLogJsonStr = request.getExceptionLogJsonStr();
            ExceptionLog exceptionLog = objectMapper.readValue(exceptionLogJsonStr, ExceptionLog.class);
            exceptionLogService.save(exceptionLog);
            responseObserver.onNext(
                    LogServiceOuterClass.ExceptionLogEntry
                            .newBuilder()
                            .setExceptionLogJsonStr(objectMapper.writeValueAsString(exceptionLog))
                            .build());
            responseObserver.onCompleted();
        } catch (JsonProcessingException e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 保存操作日志
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void saveOperationLog(LogServiceOuterClass.OperationLogEntry request, StreamObserver<LogServiceOuterClass.OperationLogEntry> responseObserver) {
        try {
            String operationLogJsonStr = request.getOperationLogJsonStr();
            OperationLog operationLog = objectMapper.readValue(operationLogJsonStr, OperationLog.class);
            operationLogService.save(operationLog);
            responseObserver.onNext(LogServiceOuterClass.OperationLogEntry
                    .newBuilder()
                    .setOperationLogJsonStr(objectMapper.writeValueAsString(operationLog))
                    .build());
            responseObserver.onCompleted();
        } catch (JsonProcessingException e) {
            responseObserver.onError(e);
        }
    }
}
