package com.gewuyou.shared.provider;

/**
 * 应用程序配置提供程序
 *
 * @author gewuyou
 * @since 2024-10-11 14:41:45
 */
public interface ApplicationConfigProvider {
    /**
     * 获取配置
     *
     * @param namespace    命名空间
     * @param key          键
     * @param defaultValue 默认值
     * @return 配置值
     */
    String get(String namespace, String key, String defaultValue);



    /**
     * 移除某个配置
     *
     * @param namespace 命名空间
     * @param key       键
     */
    void remove(String namespace, String key);

    /**
     * 清除某个命名空间下的所有配置
     *
     * @param namespace 命名空间
     */
    void clear(String namespace);

    /**
     * 清除所有配置
     *
     */
    void clear();

    /**
     * 获取优先级，数值越小优先级越高
     */
    int getPriority();
}
