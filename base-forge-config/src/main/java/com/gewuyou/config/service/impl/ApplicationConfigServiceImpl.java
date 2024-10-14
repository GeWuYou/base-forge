package com.gewuyou.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gewuyou.common.model.ApplicationConfig;
import com.gewuyou.config.mapper.ApplicationConfigMapper;
import com.gewuyou.config.service.IApplicationConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 程序配置表，用于存储不同模块的配置信息 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-11
 */
@Service
public class ApplicationConfigServiceImpl extends ServiceImpl<ApplicationConfigMapper, ApplicationConfig> implements IApplicationConfigService {

    /**
     * 根据命名空间和key获取配置信息
     *
     * @param namespace 命名空间
     * @param key       key
     * @return 配置信息
     */
    @Override
    public ApplicationConfig getApplicationConfigByNamespaceAndKey(String namespace, String key) {
        return baseMapper.selectOne(
                new LambdaQueryWrapper<ApplicationConfig>()
                        .eq(ApplicationConfig::getNamespace, namespace)
                        .eq(ApplicationConfig::getKey, key)
        );
    }

    /**
     * 根据命名空间和key删除配置信息
     *
     * @param namespace 命名空间
     * @param key       key
     * @return 删除的行数
     */
    @Override
    public long deleteByNamespaceAndKey(String namespace, String key) {
        return baseMapper.delete(new LambdaQueryWrapper<ApplicationConfig>()
                .eq(ApplicationConfig::getNamespace, namespace)
                .eq(ApplicationConfig::getKey, key)
        );
    }

    /**
     * 删除指定命名空间下的所有配置信息
     *
     * @param namespace 命名空间
     * @return 删除的行数
     */
    @Override
    public long deleteByNamespace(String namespace) {
        return baseMapper.delete(
                new LambdaQueryWrapper<ApplicationConfig>()
                        .eq(ApplicationConfig::getNamespace, namespace)
        );
    }
}
