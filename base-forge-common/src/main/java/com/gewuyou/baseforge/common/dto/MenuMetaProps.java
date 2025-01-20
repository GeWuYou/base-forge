package com.gewuyou.baseforge.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单Meta Props DTO
 *
 * @author gewuyou
 * @since 2024-11-07 18:40:53
 */
@Schema(description = "菜单Meta Props DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuMetaProps {
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;
    /**
     * 菜单标题
     */
    @Schema(description = "菜单标题")
    private String title;
    /**
     * 是否为链接
     */
    @Schema(description = "是否为链接")
    private boolean isLink;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private boolean isEnable;
    /**
     * 是否固定
     */
    @Schema(description = "是否固定")
    private boolean isAffix;
    /**
     * 是否缓存
     */
    @Schema(description = "是否缓存")
    private boolean isKeepAlive;
}
