package com.gewuyou.config.client.impl;

import com.gewuyou.common.config.ConfigServiceGrpc;
import com.gewuyou.common.config.ConfigServiceOuterClass;
import com.gewuyou.common.grpc.ClientGrpc;
import com.gewuyou.config.client.IConfigClientGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置客户端 grpc 实现
 *
 * @author gewuyou
 * @since 2024-10-13 20:24:58
 */
@Slf4j
@Component
public class ConfigClientGrpcImpl implements ClientGrpc, IConfigClientGrpc {
    @Value("${grpc.config.host}")
    protected String host;
    @Value("${grpc.config.port}")
    protected int port;
    private ManagedChannel managedChannel;

    private ConfigServiceGrpc.ConfigServiceBlockingStub blockingStub;

    private static void printInfo(ConfigServiceOuterClass.LongResponse longResponse) {
        log.info("delete config result: {}", longResponse.getValue());
    }

    /**
     * 初始化
     */
    @PostConstruct
    @Override
    public void init() {
        managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = ConfigServiceGrpc.newBlockingStub(managedChannel);
    }

    /**
     * 关闭管道
     */
    @PreDestroy
    @Override
    public void shutdown() {
        managedChannel.shutdown();
    }

    /**
     * 获取配置
     *
     * @param namespace 命名空间
     * @param key       配置key
     * @return 配置值
     */
    @Override
    public String get(String namespace, String key) {
        return blockingStub.get(ConfigServiceOuterClass.GetRequest.newBuilder().setNamespace(namespace).setKey(key).build()).getValue();
    }

    /**
     * 删除配置
     *
     * @param namespace 命名空间
     * @param key       配置key
     */
    @Override
    public void delete(String namespace, String key) {
        ConfigServiceOuterClass.LongResponse longResponse = blockingStub.deleteByNamespaceAndKey(ConfigServiceOuterClass.DeleteByNamespaceAndKeyRequest.newBuilder().setNamespace(namespace).setKey(key).build());
        printInfo(longResponse);
    }

    /**
     * 删除命名空间下所有配置
     *
     * @param namespace 命名空间
     */
    @Override
    public void delete(String namespace) {
        ConfigServiceOuterClass.LongResponse longResponse = blockingStub.deleteByNamespace(ConfigServiceOuterClass.DeleteByNamespaceRequest.newBuilder().setNamespace(namespace).build());
        printInfo(longResponse);
    }
}
