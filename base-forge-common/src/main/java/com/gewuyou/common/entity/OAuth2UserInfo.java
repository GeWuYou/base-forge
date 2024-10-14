package com.gewuyou.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OAuth2用户信息包装类
 *
 * @author gewuyou
 * @since 2024-10-09 17:17:01
 */
@Schema(description = "OAuth2用户信息包装类")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2UserInfo {
    /**
     * 社交提供商
     */
    @Schema(description = "社交提供商")
    private String socialProvider;

    /**
     * 社交用户ID
     */
    @Schema(description = "社交用户ID")
    private String socialId;

    /**
     * 社交用户主页链接
     */
    @Schema(description = "社交用户主页链接")
    private String socialLink;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;
}
