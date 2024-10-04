package com.gewuyou.cache.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gewuyou.cache.client.ICacheClientGrpc;
import com.gewuyou.common.cache.CacheServiceGrpc;
import com.gewuyou.common.cache.CacheServiceOuterClass;
import com.gewuyou.common.grpc.ClientGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * gRPC 缓存客户端
 *
 * @author gewuyou
 * @since 2024-10-03 22:41:12
 */
@Component
@Slf4j
public class CacheClientGrpcImpl implements ClientGrpc, ICacheClientGrpc {
    /**
     * grpc 缓存服务地址
     */
    @Value("${grpc.cache.host}")
    private String host;
    /**
     * grpc 缓存服务端口
     */
    @Value("${grpc.cache.port}")
    private int port;
    /**
     * grpc 缓存客户端管道
     */
    private ManagedChannel managedChannel;
    /**
     * grpc 缓存客户端阻塞式 stub
     */
    private CacheServiceGrpc.CacheServiceBlockingStub cacheServiceBlockingStub;

    private final ObjectMapper objectMapper;

    @Autowired
    public CacheClientGrpcImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    @Override
    public void init() {
        managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        cacheServiceBlockingStub = CacheServiceGrpc.newBlockingStub(managedChannel);
    }

    @PreDestroy
    @Override
    public void shutdown() {
        managedChannel.shutdown();
    }

    /**
     * 设置缓存值。
     *
     * @param key   缓存的键
     * @param value 要设置的值
     */
    @Override
    public void set(String key, String value) {
        //noinspection ResultOfMethodCallIgnored
        cacheServiceBlockingStub
                .set(CacheServiceOuterClass
                        .SetRequest
                        .newBuilder()
                        .setKey(key)
                        .setValue(value)
                        .build());
    }

    /**
     * 设置缓存值并指定过期时间（秒）。
     *
     * @param key        缓存的键
     * @param value      要设置的值
     * @param expireTime 过期时间（单位：秒）
     */
    @Override
    public void setWithExpireTimeBySec(String key, String value, long expireTime) {
        //noinspection ResultOfMethodCallIgnored
        cacheServiceBlockingStub
                .setWithTimeoutBySec(CacheServiceOuterClass
                        .SetWithTimeoutRequest
                        .newBuilder()
                        .setKey(key)
                        .setValue(value)
                        .setTimeout(expireTime)
                        .build());
    }

    /**
     * 设置缓存值并指定过期时间（毫秒）。
     *
     * @param key        缓存的键
     * @param value      要设置的值
     * @param expireTime 过期时间（单位：毫秒）
     */
    @Override
    public void setWithExpireTimeByMills(String key, String value, long expireTime) {
        //noinspection ResultOfMethodCallIgnored
        cacheServiceBlockingStub
                .setWithTimeoutByMs(CacheServiceOuterClass
                        .SetWithTimeoutRequest
                        .newBuilder()
                        .setKey(key)
                        .setValue(value)
                        .setTimeout(expireTime)
                        .build());
    }

    /**
     * 获取缓存值。
     *
     * @param key 缓存的键
     * @return 缓存的值，如果不存在，返回 null
     */
    @Override
    public String get(String key) {
        return cacheServiceBlockingStub.get(CacheServiceOuterClass.StringRequest.newBuilder().setKey(key).build()).getValue();
    }

    /**
     * 删除指定的缓存项。
     *
     * @param key 缓存的键
     * @return 如果删除成功，返回 true；否则返回 false
     */
    @Override
    public boolean delete(String key) {
        return cacheServiceBlockingStub.delete(CacheServiceOuterClass.StringRequest.newBuilder().setKey(key).build()).getSuccess();
    }

    /**
     * 延迟删除指定的缓存项（按秒）。
     *
     * @param key       缓存的键
     * @param delayTime 延迟时间（单位：秒）
     * @return 如果删除成功，返回 true；否则返回 false
     */
    @Override
    public boolean delayedDeleteBySec(String key, long delayTime) {
        return cacheServiceBlockingStub.delayedDeleteBySec(CacheServiceOuterClass.DelayDeleteRequest.newBuilder().setDelay(delayTime).build()).getSuccess();
    }

    /**
     * 延迟删除指定的缓存项（按毫秒）。
     *
     * @param key       缓存的键
     * @param delayTime 延迟时间（单位：毫秒）
     * @return 如果删除成功，返回 true；否则返回 false
     */
    @Override
    public boolean delayedDeleteByMills(String key, long delayTime) {
        return cacheServiceBlockingStub.delayedDeleteByMs(CacheServiceOuterClass.DelayDeleteRequest.newBuilder().setDelay(delayTime).build()).getSuccess();
    }

    /**
     * 设置缓存项的过期时间（秒）。
     *
     * @param key        缓存的键
     * @param expireTime 过期时间（单位：秒）
     * @return 如果设置成功，返回 true；否则返回 false
     */
    @Override
    public boolean setExpireTimeBySec(String key, long expireTime) {
        return cacheServiceBlockingStub.setExpireBySec(CacheServiceOuterClass.SetExpireRequest.newBuilder().setKey(key).setTime(expireTime).build()).getSuccess();
    }

    /**
     * 设置缓存项的过期时间（毫秒）。
     *
     * @param key        缓存的键
     * @param expireTime 过期时间（单位：毫秒）
     * @return 如果设置成功，返回 true；否则返回 false
     */
    @Override
    public boolean setExpireTimeByMills(String key, long expireTime) {
        return cacheServiceBlockingStub.setExpireByMs(CacheServiceOuterClass.SetExpireRequest.newBuilder().setKey(key).setTime(expireTime).build()).getSuccess();
    }

    /**
     * 获取缓存项的剩余过期时间（秒）。
     *
     * @param key 缓存的键
     * @return 剩余过期时间（单位：秒），如果不存在，返回 -1
     */
    @Override
    public long getExpireTimeBySec(String key) {
        return cacheServiceBlockingStub.getExpireBySec(CacheServiceOuterClass.StringRequest.newBuilder().setKey(key).build()).getValue();
    }

    /**
     * 获取缓存项的剩余过期时间（毫秒）。
     *
     * @param key 缓存的键
     * @return 剩余过期时间（单位：毫秒），如果不存在，返回 -1
     */
    @Override
    public long getExpireTimeByMills(String key) {
        return cacheServiceBlockingStub.getExpireByMs(CacheServiceOuterClass.StringRequest.newBuilder().setKey(key).build()).getValue();
    }

    /**
     * 检查指定的键是否存在。
     *
     * @param key 缓存的键
     * @return 如果存在，返回 true；否则返回 false
     */
    @Override
    public boolean hasKey(String key) {
        return cacheServiceBlockingStub.hasKey(CacheServiceOuterClass.StringRequest.newBuilder().setKey(key).build()).getSuccess();
    }

    /**
     * 对指定的键进行自增操作。
     *
     * @param key   缓存的键
     * @param delta 增量值
     * @return 增加后的值
     */
    @Override
    public long incr(String key, int delta) {
        return cacheServiceBlockingStub.incr(CacheServiceOuterClass.IncrRequest.newBuilder().setKey(key).setDelta(delta).build()).getValue();
    }

    /**
     * 对指定的键进行自减操作。
     *
     * @param key   缓存的键
     * @param delta 减量值
     * @return 减少后的值
     */
    @Override
    public long decr(String key, int delta) {
        return cacheServiceBlockingStub.decr(CacheServiceOuterClass.IncrRequest.newBuilder().setKey(key).setDelta(delta).build()).getValue();
    }

    /**
     * 设置哈希表中指定字段的值。
     *
     * @param key     哈希表的键
     * @param hashKey 哈希字段的键
     * @param value   要设置的值
     */
    @Override
    public void hSet(String key, String hashKey, String value) {
        //noinspection ResultOfMethodCallIgnored
        cacheServiceBlockingStub.hSet(CacheServiceOuterClass.HashSetRequest.newBuilder().setKey(key).setHashKey(hashKey).setValue(value).build());
    }

    /**
     * 设置哈希表中指定字段的值，并指定过期时间（秒）。
     *
     * @param key        哈希表的键
     * @param hashKey    哈希字段的键
     * @param value      要设置的值
     * @param expireTime 过期时间（单位：秒）
     */
    @Override
    public void hSetWithExpireTimeBySec(String key, String hashKey, String value, long expireTime) {
        //noinspection ResultOfMethodCallIgnored
        cacheServiceBlockingStub.hSetWithTimeoutBySec(CacheServiceOuterClass.HashSetWithTimeoutRequest.newBuilder().setKey(key).setHashKey(hashKey).setValue(value).setTimeout(expireTime).build());
    }

    /**
     * 设置哈希表中指定字段的值，并指定过期时间（毫秒）。
     *
     * @param key        哈希表的键
     * @param hashKey    哈希字段的键
     * @param value      要设置的值
     * @param expireTime 过期时间（单位：毫秒）
     */
    @Override
    public void hSetWithExpireTimeByMills(String key, String hashKey, String value, long expireTime) {
        //noinspection ResultOfMethodCallIgnored
        cacheServiceBlockingStub.hSetWithTimeoutByMs(CacheServiceOuterClass.HashSetWithTimeoutRequest.newBuilder().setKey(key).setHashKey(hashKey).setValue(value).setTimeout(expireTime).build());
    }

    /**
     * 获取哈希表中指定字段的值。
     *
     * @param key     哈希表的键
     * @param hashKey 哈希字段的键
     * @return 哈希字段的值，如果不存在，返回 null
     */
    @Override
    public String hGet(String key, String hashKey) {
        return cacheServiceBlockingStub.hGet(CacheServiceOuterClass.HashGetRequest.newBuilder().setKey(key).setHashKey(hashKey).build()).getValue();
    }

    /**
     * 获取哈希表中所有字段的值。
     *
     * @param key 哈希表的键
     * @return 包含所有字段和值的映射
     */
    @Override
    public Map<String, Object> hGetAll(String key) {
        String resultStr = cacheServiceBlockingStub.hGetAll(CacheServiceOuterClass.StringRequest.newBuilder().setKey(key).build()).getValue();
        Map<String, Object> result;
        try {
            result = objectMapper.readValue(resultStr, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("hGetAll error", e);
            result = new HashMap<>();
        }
        return result;
    }

    /**
     * 删除哈希表中一个或多个字段。
     *
     * @param hashKey 一个或多个哈希字段的键
     * @return 被删除字段的数量
     */
    @Override
    public long hDel(String key, String... hashKey) {
        if (hashKey.length <= 1) {
            return cacheServiceBlockingStub.hDel(
                            CacheServiceOuterClass.HashDeleteRequest
                                    .newBuilder()
                                    .setKey(key)
                                    .setHashKey(hashKey[0])
                                    .build())
                    .getValue();
        } else {
            List<String> hashKeys = List.of(hashKey);
            try {
                return cacheServiceBlockingStub.hDel(
                                CacheServiceOuterClass.HashDeleteRequest
                                        .newBuilder()
                                        .setKey(key)
                                        .setHashKey(
                                                objectMapper
                                                        .writeValueAsString(hashKeys))
                                        .build())
                        .getValue();
            } catch (JsonProcessingException e) {
                log.error("hDel error", e);
                return 0;
            }
        }
    }

    /**
     * 对哈希表中指定字段进行自增操作。
     *
     * @param key     哈希表的键
     * @param hashKey 哈希字段的键
     * @param delta   增量值
     * @return 增加后的值
     */
    @Override
    public long hIncr(String key, String hashKey, int delta) {
        return cacheServiceBlockingStub.hIncr(CacheServiceOuterClass.HashIncrRequest.newBuilder().setKey(key).setHashKey(hashKey).setDelta(delta).build()).getValue();
    }

    /**
     * 对哈希表中指定字段进行自减操作。
     *
     * @param key     哈希表的键
     * @param hashKey 哈希字段的键
     * @param delta   减量值
     * @return 减少后的值
     */
    @Override
    public long hDecr(String key, String hashKey, int delta) {
        return cacheServiceBlockingStub.hDecr(CacheServiceOuterClass.HashIncrRequest.newBuilder().setKey(key).setHashKey(hashKey).setDelta(delta).build()).getValue();
    }

    /**
     * 对有序集合中的指定元素进行分数自增操作。
     *
     * @param key   有序集合的键
     * @param value 要增加分数的元素
     * @param score 增幅
     * @return 增加后的分数
     */
    @Override
    public double zIncr(String key, String value, double score) {
        return cacheServiceBlockingStub.zIncr(CacheServiceOuterClass.ZSetRequest.newBuilder().setKey(key).setValue(value).setScore(score).build()).getValue();
    }

    /**
     * 对有序集合中的指定元素进行分数自减操作。
     *
     * @param key   有序集合的键
     * @param value 要减少分数的元素
     * @param score 减幅
     * @return 减少后的分数
     */
    @Override
    public double zDecr(String key, String value, double score) {
        return cacheServiceBlockingStub.zDecr(CacheServiceOuterClass.ZSetRequest.newBuilder().setKey(key).setValue(value).setScore(score).build()).getValue();
    }

    /**
     * 以分数的降序获取有序集合中指定范围的元素及其分数。
     *
     * @param key   有序集合的键
     * @param start 起始位置（从 0 开始）
     * @param end   结束位置（从 0 开始）
     * @return 包含元素及其分数的映射
     */
    @Override
    public Map<Object, Double> zReversedRangWithScores(String key, long start, long end) {
        String resultStr = cacheServiceBlockingStub.zReversedRangWithScores(CacheServiceOuterClass.ZReversedRangeRequest.newBuilder().setKey(key).setStart(start).setEnd(end).build()).getValue();
        Map<Object, Double> result;
        try {
            result = objectMapper.readValue(resultStr, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("zReversedRangWithScores error", e);
            result = new HashMap<>();
        }
        return result;
    }

    /**
     * 获取有序集合中指定元素的分数。
     *
     * @param key   有序集合的键
     * @param value 要查询分数的元素
     * @return 元素的分数，如果不存在，返回 -1
     */
    @Override
    public double zScore(String key, String value) {
        return cacheServiceBlockingStub.zScore(CacheServiceOuterClass.ZScoreRequest.newBuilder().setKey(key).setValue(value).build()).getValue();
    }

    /**
     * 获取有序集合中所有元素及其分数。
     *
     * @param key 有序集合的键
     * @return 包含所有元素及其分数的映射
     */
    @Override
    public Map<Object, Double> zAllScore(String key) {
        String resultStr = cacheServiceBlockingStub.zAllScore(CacheServiceOuterClass.StringRequest.newBuilder().setKey(key).build()).getValue();
        Map<Object, Double> result;
        try {
            result = objectMapper.readValue(resultStr, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("zAllScore error", e);
            result = new HashMap<>();
        }
        return result;
    }

    /**
     * 向集合中添加一个或多个元素。
     *
     * @param key    集合的键
     * @param values 要添加的元素
     * @return 被添加元素的数量，不包括已经存在的元素
     */
    @Override
    public long sAdd(String key, Object... values) {
        if (values.length <= 1) {
            return cacheServiceBlockingStub.sAdd(
                            CacheServiceOuterClass.SetAddRequest
                                    .newBuilder()
                                    .setKey(key)
                                    .setValue(String.valueOf(values[0]))
                                    .build())
                    .getValue();
        } else {
            List<Object> valuesList = List.of(values);
            try {
                return cacheServiceBlockingStub.sAdd(
                                CacheServiceOuterClass.SetAddRequest
                                        .newBuilder()
                                        .setKey(key)
                                        .setValue(objectMapper.writeValueAsString(valuesList))
                                        .build())
                        .getValue();
            } catch (JsonProcessingException e) {
                log.error("sAdd error", e);
                return 0;
            }
        }
    }

    /**
     * 检查指定的元素是否为集合的成员。
     *
     * @param key   集合的键
     * @param value 要检查的元素
     * @return 如果元素是集合的成员，返回 true；否则返回 false
     */
    @Override
    public boolean sisMember(String key, String value) {
        return cacheServiceBlockingStub.sIsMember(CacheServiceOuterClass.SetIsMemberRequest.newBuilder().setKey(key).setValue(value).build()).getSuccess();
    }

    /**
     * 获取集合中元素的数量。
     *
     * @param key 集合的键
     * @return 集合中元素的数量
     */
    @Override
    public long sSize(String key) {
        return cacheServiceBlockingStub.sSize(CacheServiceOuterClass.StringRequest.newBuilder().setKey(key).build()).getValue();
    }

    /**
     * 获取多个集合的差集。
     *
     * @param keys 要计算差集的多个集合的键
     * @return 包含差集元素的集合
     */
    @Override
    public Set<Object> sDiff(String... keys) {
        if (keys.length > 1) {
            List<String> keysList = List.of(keys);
            try {
                return objectMapper.readValue(cacheServiceBlockingStub
                        .sDiff(
                                CacheServiceOuterClass.StringRequest
                                        .newBuilder()
                                        .setKey(objectMapper.writeValueAsString(keysList))
                                        .build()
                        ).getValue(), new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                log.error("sDiff error", e);
                return Set.of();
            }
        }
        return Set.of();
    }

    /**
     * 如果指定的键不存在，则添加元素并设置过期时间（秒）。
     *
     * @param key        缓存的键
     * @param value      要添加的元素
     * @param expireTime 过期时间（单位：秒）
     * @return 如果添加成功，返回 true；否则返回 false
     */
    @Override
    public boolean sIfAbsentBySec(String key, String value, long expireTime) {
        return cacheServiceBlockingStub.sIfAbsentBySec(CacheServiceOuterClass.SetIfAbsentRequest.newBuilder().setKey(key).setValue(value).setTimeout(expireTime).build()).getSuccess();
    }

    /**
     * 如果指定的键不存在，则添加元素并设置过期时间（毫秒）。
     *
     * @param key        缓存的键
     * @param value      要添加的元素
     * @param expireTime 过期时间（单位：毫秒）
     * @return 如果添加成功，返回 true；否则返回 false
     */
    @Override
    public boolean sIfAbsentByMills(String key, String value, long expireTime) {
        return cacheServiceBlockingStub.sIfAbsentByMs(CacheServiceOuterClass.SetIfAbsentRequest.newBuilder().setKey(key).setValue(value).setTimeout(expireTime).build()).getSuccess();
    }
}
