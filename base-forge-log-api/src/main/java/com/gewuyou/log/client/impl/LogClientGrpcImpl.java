package com.gewuyou.log.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gewuyou.common.enums.ResponseInformation;
import com.gewuyou.common.exception.LogException;
import com.gewuyou.common.grpc.ClientGrpc;
import com.gewuyou.common.log.LogServiceGrpc;
import com.gewuyou.common.log.LogServiceOuterClass;
import com.gewuyou.common.model.ExceptionLog;
import com.gewuyou.common.model.OperationLog;
import com.gewuyou.log.client.ILogClientGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 日志gRPC客户端实现
 *
 * @author gewuyou
 * @since 2024-10-05 19:25:34
 */
@Component
public class LogClientGrpcImpl implements ClientGrpc, ILogClientGrpc {
    private final ObjectMapper objectMapper;
    /**
     * grpc 日志服务地址
     */
    @Value("${grpc.log.host}")
    protected String host;
    /**
     * grpc 日志服务端口
     */
    @Value("${grpc.log.port}")
    protected int port;
    /**
     * grpc 客户端管道
     */
    private ManagedChannel managedChannel;
    private LogServiceGrpc.LogServiceBlockingStub blockingStub;
    @Autowired
    public LogClientGrpcImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    /**
     * 保存异常日志
     *
     * @param exceptionLog 异常日志
     * @return 保存成功的异常日志对象
     */
    @Override
    public ExceptionLog saveExceptionLog(ExceptionLog exceptionLog) {
        try {
            String json = objectMapper.writeValueAsString(exceptionLog);
            String jsonStr = blockingStub.saveExceptionLog(LogServiceOuterClass.ExceptionLogEntry.newBuilder().setExceptionLogJsonStr(json).build()).getExceptionLogJsonStr();
            return objectMapper.readValue(jsonStr, ExceptionLog.class);
        } catch (JsonProcessingException e) {
            throw new LogException(ResponseInformation.JSON_SERIALIZE_OR_DESERIALIZE_ERROR);
        }
    }

    /**
     * 保存操作日志
     *
     * @param operationLog 操作日志
     * @return 保存成功的操作日志对象
     */
    @Override
    public OperationLog saveOperationLog(OperationLog operationLog) {
        try {
            String json = objectMapper.writeValueAsString(operationLog);
            String jsonStr = blockingStub.saveOperationLog(LogServiceOuterClass.OperationLogEntry.newBuilder().setOperationLogJsonStr(json).build()).getOperationLogJsonStr();
            return objectMapper.readValue(jsonStr, OperationLog.class);
        }catch (JsonProcessingException e) {
            throw new LogException(ResponseInformation.JSON_SERIALIZE_OR_DESERIALIZE_ERROR);
        }
    }
    /**
     * 初始化
     */
    @Override
    @PostConstruct
    public void init() {
        managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = LogServiceGrpc.newBlockingStub(managedChannel);
    }

    /**
     * 关闭管道
     */
    @Override
    @PreDestroy
    public void shutdown() {
        managedChannel.shutdown();
    }
}
