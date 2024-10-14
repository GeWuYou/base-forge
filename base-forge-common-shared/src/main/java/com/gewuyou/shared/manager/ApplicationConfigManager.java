package com.gewuyou.shared.manager;

import com.gewuyou.shared.provider.ApplicationConfigProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 应用程序配置管理器
 *
 * @author gewuyou
 * @since 2024-10-11 15:12:19
 */
@Service
public class ApplicationConfigManager {
    private final List<ApplicationConfigProvider> applicationConfigProviders;

    @Autowired
    public ApplicationConfigManager(List<ApplicationConfigProvider> applicationConfigProviders) {
        applicationConfigProviders.sort(Comparator.comparing(ApplicationConfigProvider::getPriority));
        this.applicationConfigProviders = applicationConfigProviders;
    }

    /**
     * 获取配置
     *
     * @param namespace    命名空间
     * @param key          键
     * @param defaultValue 默认值
     * @return 配置值
     */
    public String get(String namespace, String key, String defaultValue) {
        for (ApplicationConfigProvider provider : applicationConfigProviders) {
            var value = provider.get(namespace, key, null);
            if (Objects.nonNull(value)) {
                return value;
            }
        }
        return defaultValue;
    }

    /**
     * 移除某个配置(从缓存中移除)
     *
     * @param namespace 命名空间
     * @param key       键
     */
    public void remove(String namespace, String key) {
        applicationConfigProviders.getFirst().remove(namespace, key);
    }

    /**
     * 移除某个配置
     *
     * @param namespace 命名空间
     * @param key       键
     */
    public void removeAll(String namespace, String key) {
        applicationConfigProviders.forEach(provider -> provider.remove(namespace, key));
    }

    /**
     * 清除某个命名空间下的所有配置(从缓存中移除)
     *
     * @param namespace 命名空间
     */
    public void clear(String namespace) {
        applicationConfigProviders.getFirst().clear(namespace);
    }

    /**
     * 清除某个命名空间下的所有配置
     *
     * @param namespace 命名空间
     */
    public void clearAll(String namespace) {
        applicationConfigProviders.forEach(provider -> provider.clear(namespace));
    }

    /**
     * 清除所有配置(从缓存中移除)
     */
    public void clear() {
        applicationConfigProviders.getFirst().clear();
    }

    /**
     * 清除所有配置
     */
    public void clearAll() {
        applicationConfigProviders.forEach(ApplicationConfigProvider::clear);
    }
}
