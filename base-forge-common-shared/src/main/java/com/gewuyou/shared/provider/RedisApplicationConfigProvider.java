package com.gewuyou.shared.provider;

import com.gewuyou.cache.client.ICacheClientGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Redis 应用程序配置提供程序
 *
 * @author gewuyou
 * @since 2024-10-11 15:26:58
 */
@Service("redisApplicationConfigProvider")
public class RedisApplicationConfigProvider implements ApplicationConfigProvider {
    private final ICacheClientGrpc cacheClient;
    private final String CACHE_NAMESPACE = "application-config";

    @Autowired
    public RedisApplicationConfigProvider(ICacheClientGrpc cacheClient) {
        this.cacheClient = cacheClient;
    }

    /**
     * 获取配置
     *
     * @param namespace    命名空间
     * @param key          键
     * @param defaultValue 默认值
     * @return 配置值
     */
    @Override
    public String get(String namespace, String key, String defaultValue) {
        return Optional.ofNullable(cacheClient.get(CACHE_NAMESPACE + ":" + namespace + ":" + key)).orElse(defaultValue);
    }

    /**
     * 移除某个配置
     *
     * @param namespace 命名空间
     * @param key       键
     */
    @Override
    public void remove(String namespace, String key) {
        cacheClient.deleteCache(CACHE_NAMESPACE, namespace, key);
    }

    /**
     * 清除某个命名空间下的所有配置
     *
     * @param namespace 命名空间
     */
    @Override
    public void clear(String namespace) {
        cacheClient.clearNamespace(CACHE_NAMESPACE, namespace);
    }

    /**
     * 清除所有配置
     */
    @Override
    public void clear() {
        cacheClient.clearTopNamespace(CACHE_NAMESPACE);
    }


    /**
     * 获取优先级，数值越小优先级越高
     *
     * @return 优先级
     */
    @Override
    public int getPriority() {
        return 1;
    }
}
