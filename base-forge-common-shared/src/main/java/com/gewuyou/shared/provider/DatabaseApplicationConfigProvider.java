package com.gewuyou.shared.provider;

import com.gewuyou.common.enums.ResponseInformation;
import com.gewuyou.common.exception.GlobalSharedException;
import com.gewuyou.config.client.IConfigClientGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 数据库应用程序配置提供程序
 *
 * @author gewuyou
 * @since 2024-10-12 14:11:23
 */
@Service("databaseApplicationConfigProvider")
public class DatabaseApplicationConfigProvider implements ApplicationConfigProvider {

   private final IConfigClientGrpc configClient;

   @Autowired
    public DatabaseApplicationConfigProvider(IConfigClientGrpc configClient) {
        this.configClient = configClient;
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
        return Optional.ofNullable(configClient.get(namespace, key)).orElse(defaultValue);
    }

    /**
     * 移除某个配置
     *
     * @param namespace 命名空间
     * @param key       键
     */
    @Override
    public void remove(String namespace, String key) {
        configClient.delete(namespace, key);
    }

    /**
     * 清除某个命名空间下的所有配置
     *
     * @param namespace 命名空间
     */
    @Override
    public void clear(String namespace) {
        configClient.delete(namespace);
    }

    /**
     * 清除所有配置
     */
    @Override
    public void clear() {
        // ignore 其它模块操作不应该清除所有配置
        throw new GlobalSharedException(ResponseInformation.NOT_SUPPORTED_OPERATION);
    }

    /**
     * 获取优先级，数值越小优先级越高
     */
    @Override
    public int getPriority() {
        return 2;
    }
}
