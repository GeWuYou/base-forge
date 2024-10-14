package com.gewuyou.config.client;

/**
 * 配置客户端 grpc接口
 *
 * @author gewuyou
 * @since 2024-10-13 20:24:06
 */
public interface IConfigClientGrpc {
    /**
     * 获取配置
     *
     * @param namespace 命名空间
     * @param key 配置key
     * @return 配置值
     */
    String get(String namespace, String key);

    /**
     * 删除配置
     * @param namespace 命名空间
     * @param key 配置key
     */
    void delete(String namespace, String key);

    /**
     * 删除命名空间下所有配置
     * @param namespace 命名空间
     */
    void delete(String namespace);

}
