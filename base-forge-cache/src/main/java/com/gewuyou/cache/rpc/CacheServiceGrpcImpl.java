package com.gewuyou.cache.rpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gewuyou.cache.service.ICacheService;
import com.gewuyou.common.cache.CacheServiceGrpc;
import com.gewuyou.common.cache.CacheServiceOuterClass;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Grpc缓存服务实现
 *
 * @author gewuyou
 * @since 2024-10-03 16:53:39
 */
@GrpcService
public class CacheServiceGrpcImpl extends CacheServiceGrpc.CacheServiceImplBase {
    private final ICacheService cacheService;

    private final ObjectMapper objectMapper;

    @Autowired
    public CacheServiceGrpcImpl(ICacheService cacheService, ObjectMapper objectMapper) {
        this.cacheService = cacheService;
        this.objectMapper = objectMapper;
    }

    /**
     * <pre>
     * 设置缓存
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void set(CacheServiceOuterClass.SetRequest request, StreamObserver<Empty> responseObserver) {
        try {
            cacheService.set(request.getKey(), request.getValue());
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 设置缓存并设置过期时间(单位：秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void setWithTimeoutBySec(CacheServiceOuterClass.SetWithTimeoutRequest request, StreamObserver<Empty> responseObserver) {
        try {
            cacheService.set(request.getKey(), request.getValue(), request.getTimeout());
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 设置缓存并设置过期时间(单位：毫秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void setWithTimeoutByMs(CacheServiceOuterClass.SetWithTimeoutRequest request, StreamObserver<Empty> responseObserver) {
        try {
            cacheService.set(request.getKey(), request.getValue(), request.getTimeout(), TimeUnit.MILLISECONDS);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 获取缓存
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void get(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.StringResponse> responseObserver) {
        try {
            String value = (String) cacheService.get(request.getKey());
            responseObserver.onNext(CacheServiceOuterClass.StringResponse.newBuilder().setValue(value).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 删除缓存
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void delete(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            boolean success = cacheService.delete(request.getKey());
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(success).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 延迟删除缓存(单位：秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void delayedDeleteBySec(CacheServiceOuterClass.DelayDeleteRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean delete = cacheService.delayedDelete(request.getKey(), request.getDelay(), TimeUnit.SECONDS);
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(delete).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 延迟删除缓存(单位：毫秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void delayedDeleteByMs(CacheServiceOuterClass.DelayDeleteRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean delete = cacheService.delayedDelete(request.getKey(), request.getDelay(), TimeUnit.MILLISECONDS);
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(delete).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 设置缓存过期时间(单位：秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void setExpireBySec(CacheServiceOuterClass.SetExpireRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean expire = cacheService.expire(request.getKey(), request.getTime());
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(expire).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 设置缓存过期时间(单位：毫秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void setExpireByMs(CacheServiceOuterClass.SetExpireRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean result = cacheService.expire(request.getKey(), request.getTime(), TimeUnit.MILLISECONDS);
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 获取缓存剩余过期时间(单位：秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void getExpireBySec(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        try {
            Long expireTime = cacheService.getExpire(request.getKey());
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(expireTime).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 获取缓存剩余过期时间(单位：毫秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void getExpireByMs(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        try {
            Long expireTime = cacheService.getExpire(request.getKey(), TimeUnit.MILLISECONDS);
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(expireTime).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 判断对应键的缓存是否存在
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hasKey(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean hasKey = cacheService.hasKey(request.getKey());
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(hasKey).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 自增
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void incr(CacheServiceOuterClass.IncrRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        try {
            Long result = cacheService.incr(request.getKey(), request.getDelta());
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 自减
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void decr(CacheServiceOuterClass.IncrRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        try {
            Long result = cacheService.decr(request.getKey(), request.getDelta());
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 哈希设置
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hSet(CacheServiceOuterClass.HashSetRequest request, StreamObserver<Empty> responseObserver) {
        try {
            cacheService.hSet(request.getKey(), request.getHashKey(), request.getValue());
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 哈希设置并设置过期时间(单位：秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hSetWithTimeoutBySec(CacheServiceOuterClass.HashSetWithTimeoutRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean result = cacheService.hSet(request.getKey(), request.getHashKey(), request.getValue(), request.getTimeout());
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 哈希设置并设置过期时间(单位：毫秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hSetWithTimeoutByMs(CacheServiceOuterClass.HashSetWithTimeoutRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean result = cacheService.hSet(request.getKey(), request.getHashKey(), request.getValue(), request.getTimeout(), TimeUnit.MILLISECONDS);
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 哈希获取
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hGet(CacheServiceOuterClass.HashGetRequest request, StreamObserver<CacheServiceOuterClass.StringResponse> responseObserver) {
        try {
            String value = (String) cacheService.hGet(request.getKey(), request.getHashKey());
            responseObserver.onNext(CacheServiceOuterClass.StringResponse.newBuilder().setValue(value).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 哈希获取所有值
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hGetAll(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.StringResponse> responseObserver) {
        try {
            Map<String, Object> result = cacheService.hGetAll(request.getKey());
            String resultJsonStr = objectMapper.writeValueAsString(result);
            responseObserver.onNext(CacheServiceOuterClass.StringResponse.newBuilder().setValue(resultJsonStr).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 哈希删除
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hDel(CacheServiceOuterClass.HashDeleteRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        try {
            // 获取 hashKey
            String hashKeyValue = request.getHashKey();
            Long result;
            // 尝试解析为 List<String>
            try {
                List<String> hashKeys = new ObjectMapper().readValue(hashKeyValue, new TypeReference<>() {
                });
                // 处理多个 hashKey
                result = cacheService.hDel(request.getKey(), (Object[]) hashKeys.toArray(new String[0]));
            } catch (JsonProcessingException e) {
                // 如果解析失败，说明是单个 hashKey
                result = cacheService.hDel(request.getKey(), hashKeyValue);
            }
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 哈希自增
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hIncr(CacheServiceOuterClass.HashIncrRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        try {
            Long result = cacheService.hIncr(request.getKey(), request.getHashKey(), request.getDelta());
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 哈希自减
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void hDecr(CacheServiceOuterClass.HashIncrRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        try {
            Long result = cacheService.hIncr(request.getKey(), request.getHashKey(), request.getDelta());
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 有序集合自增
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void zIncr(CacheServiceOuterClass.ZSetRequest request, StreamObserver<CacheServiceOuterClass.DoubleResponse> responseObserver) {
        try {
            Double result = cacheService.zIncr(request.getKey(), request.getValue(), request.getScore());
            responseObserver.onNext(CacheServiceOuterClass.DoubleResponse.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 有序排序集合自减
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void zDecr(CacheServiceOuterClass.ZSetRequest request, StreamObserver<CacheServiceOuterClass.DoubleResponse> responseObserver) {
        try {
            Double result = cacheService.zDecr(request.getKey(), request.getValue(), request.getScore());
            responseObserver.onNext(CacheServiceOuterClass.DoubleResponse.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 有序集合范围取反查询
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void zReversedRangWithScores(CacheServiceOuterClass.ZReversedRangeRequest request, StreamObserver<CacheServiceOuterClass.StringResponse> responseObserver) {
        try {
            Map<Object, Double> result = cacheService.zReverseRangeWithScore(request.getKey(), request.getStart(), request.getEnd());
            String resultJsonStr = objectMapper.writeValueAsString(result);
            responseObserver.onNext(CacheServiceOuterClass.StringResponse.newBuilder().setValue(resultJsonStr).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 有序集合获取指定键的分数
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void zScore(CacheServiceOuterClass.ZScoreRequest request, StreamObserver<CacheServiceOuterClass.DoubleResponse> responseObserver) {
        try {
            Double result = cacheService.zScore(request.getKey(), request.getValue());
            responseObserver.onNext(CacheServiceOuterClass.DoubleResponse.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 有序集合获取所有值及分数
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void zAllScore(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.StringResponse> responseObserver) {
        try {
            Map<Object, Double> result = cacheService.zAllScore(request.getKey());
            String resultJsonStr = objectMapper.writeValueAsString(result);
            responseObserver.onNext(CacheServiceOuterClass.StringResponse.newBuilder().setValue(resultJsonStr).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 集合添加元素
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void sAdd(CacheServiceOuterClass.SetAddRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        // 获取集合元素
        try {
            String value = request.getValue();
            Long result;
            // 尝试解析为 List<String>
            try {
                List<String> values = new ObjectMapper().readValue(value, new TypeReference<>() {
                });
                // 处理多个值
                result = cacheService.sAdd(request.getKey(), (Object[]) values.toArray(new String[0]));
            } catch (JsonProcessingException e) {
                result = cacheService.sAdd(request.getKey(), value);
            }
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 判断集合中是否存在指定键值
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void sIsMember(CacheServiceOuterClass.SetIsMemberRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean result = cacheService.sIsMember(request.getKey(), request.getValue());
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 获取集合大小
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void sSize(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.Int64Response> responseObserver) {
        try {
            Long result = cacheService.sSize(request.getKey());
            responseObserver.onNext(CacheServiceOuterClass.Int64Response.newBuilder().setValue(result).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 集合取交集
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void sDiff(CacheServiceOuterClass.StringRequest request, StreamObserver<CacheServiceOuterClass.StringResponse> responseObserver) {
        try {
            String keyJsonStr = request.getKey();
            // 尝试解析为 List<String>
            Set<Object> resultSet;
            try {
                List<String> keys = objectMapper.readValue(keyJsonStr, new TypeReference<>() {
                });
                // 处理多个 key
                resultSet = cacheService.sDiff(keys.toArray(new String[0]));
            } catch (JsonProcessingException e) {
                // 处理单个 key
                resultSet = cacheService.sDiff(request.getKey());
            }
            String resultJsonStr = objectMapper.writeValueAsString(resultSet);
            responseObserver.onNext(CacheServiceOuterClass.StringResponse.newBuilder().setValue(resultJsonStr).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 尝试设置集合元素，如果元素不存在则设置，并设置过期时间(单位：秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void sIfAbsentBySec(CacheServiceOuterClass.SetIfAbsentRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean success = cacheService.setIfAbsent(request.getKey(), request.getValue(), Duration.ofSeconds(request.getTimeout()));
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(success).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * <pre>
     * 尝试设置集合元素，如果元素不存在则设置，并设置过期时间(单位：毫秒)
     * </pre>
     *
     * @param request          请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void sIfAbsentByMs(CacheServiceOuterClass.SetIfAbsentRequest request, StreamObserver<CacheServiceOuterClass.BoolResponse> responseObserver) {
        try {
            Boolean success = cacheService.setIfAbsent(request.getKey(), request.getValue(), Duration.ofMillis(request.getTimeout()));
            responseObserver.onNext(CacheServiceOuterClass.BoolResponse.newBuilder().setSuccess(success).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
