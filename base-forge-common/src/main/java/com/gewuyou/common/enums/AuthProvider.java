package com.gewuyou.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 身份验证提供程序
 *
 * @author gewuyou
 * @since 2024-10-06 08:50:56
 */
@Getter
@AllArgsConstructor
public enum AuthProvider implements IEnum<Byte> {
    LOCAL((byte) 0),
    GOOGLE((byte) 1),
    GITHUB((byte) 2),
    MICROSOFT((byte) 3)
    ;
    private final byte code;

    /**
     * 根据第三方注册ID获取身份验证提供商枚举
     * @param registrationId 注册ID
     * @return 身份验证提供程序枚举
     */
    public static AuthProvider getByRegistrationId(String registrationId) {
        return switch (registrationId) {
            case "google" -> AuthProvider.GOOGLE;
            case "github" -> AuthProvider.GITHUB;
            case "microsoft" -> AuthProvider.MICROSOFT;
            default -> throw new IllegalArgumentException("Invalid authentication provider: " + registrationId);
        };
    }

    @Override
    public Byte getValue() {
        return this.code;
    }
}
