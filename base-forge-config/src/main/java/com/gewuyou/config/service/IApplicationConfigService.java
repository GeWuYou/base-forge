package com.gewuyou.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gewuyou.common.model.ApplicationConfig;

/**
 * <p>
 * 程序配置表，用于存储不同模块的配置信息 服务类
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-11
 */
public interface IApplicationConfigService extends IService<ApplicationConfig> {
    /**
     * 根据命名空间和key获取配置信息
     * @param namespace 命名空间
     * @param key key
     * @return 配置信息
     */
    ApplicationConfig getApplicationConfigByNamespaceAndKey(String namespace, String key);

    /**
     * 根据命名空间和key删除配置信息
     * @param namespace 命名空间
     * @param key key
     * @return  删除的行数
     */
    long deleteByNamespaceAndKey(String namespace, String key);

    /**
     * 删除指定命名空间下的所有配置信息
     * @param namespace 命名空间
     * @return  删除的行数
     */
    long deleteByNamespace(String namespace);
}
