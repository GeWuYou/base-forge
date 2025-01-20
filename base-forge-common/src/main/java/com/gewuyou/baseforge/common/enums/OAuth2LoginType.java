package com.gewuyou.baseforge.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OAuth 2 登录类型
 *
 * @author gewuyou
 * @since 2024-10-08 09:49:01
 */
@Getter
@AllArgsConstructor
public enum OAuth2LoginType {
    GOOGLE(AuthProvider.GOOGLE, "谷歌", "googleOAuth2LoginStrategyImpl"),
    GITHUB(AuthProvider.GITHUB, "GitHub", "githubOAuth2LoginStrategyImpl"),
    MICROSOFT(AuthProvider.MICROSOFT, "微软", "microsoftOAuth2LoginStrategyImpl"),
    ;
    /**
     * 安全认证提供商
     */
    private final AuthProvider authProvider;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 登录策略名称
     */
    private final String strategy;

    /**
     * 根据安全认证提供商获取登录类型
     * @param authProvider 安全认证提供商
     * @return 登录类型
     */
    public static OAuth2LoginType getByAuthProvider(AuthProvider authProvider) {
        for (OAuth2LoginType type : values()) {
            if (type.getAuthProvider() == authProvider) {
                return type;
            }
        }
        return null;
    }
}
