package com.gewuyou.config.rpc;

import com.gewuyou.common.config.ConfigServiceGrpc;
import com.gewuyou.common.config.ConfigServiceOuterClass;
import com.gewuyou.common.model.ApplicationConfig;
import com.gewuyou.config.service.IApplicationConfigService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 配置服务 gRPC 实现
 *
 * @author gewuyou
 * @since 2024-10-12 15:51:20
 */
@GrpcService
public class ConfigServiceGrpcImpl extends ConfigServiceGrpc.ConfigServiceImplBase {
    private final IApplicationConfigService applicationConfigService;

    @Autowired
    public ConfigServiceGrpcImpl(IApplicationConfigService applicationConfigService) {
        this.applicationConfigService = applicationConfigService;
    }


    /**
     * <pre>
     * 获取配置
     * </pre>
     *
     * @param request 请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void get(ConfigServiceOuterClass.GetRequest request, StreamObserver<ConfigServiceOuterClass.GetResponse> responseObserver) {
        try {
            String namespace = request.getNamespace();
            String key = request.getKey();
            ApplicationConfig config = applicationConfigService.getApplicationConfigByNamespaceAndKey(namespace, key);
            ConfigServiceOuterClass.GetResponse response = ConfigServiceOuterClass.GetResponse.newBuilder()
                   .setValue(config.getValue())
                   .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 根据命名空间和key删除某个配置
     * </pre>
     *
     * @param request 请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void deleteByNamespaceAndKey(ConfigServiceOuterClass.DeleteByNamespaceAndKeyRequest request, StreamObserver<ConfigServiceOuterClass.LongResponse> responseObserver) {
        try {
            String namespace = request.getNamespace();
            String key = request.getKey();
            responseObserver.onNext(ConfigServiceOuterClass.LongResponse
                    .newBuilder()
                            .setValue(applicationConfigService.deleteByNamespaceAndKey(namespace, key))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 删除某个命名空间下的所有配置
     * </pre>
     *
     * @param request 请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void deleteByNamespace(ConfigServiceOuterClass.DeleteByNamespaceRequest request, StreamObserver<ConfigServiceOuterClass.LongResponse> responseObserver) {
        try {
            String namespace = request.getNamespace();
            responseObserver.onNext(ConfigServiceOuterClass.LongResponse
                    .newBuilder()
                            .setValue(applicationConfigService.deleteByNamespace(namespace))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
