package com.gewuyou.baseforge.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单响应DTO
 *
 * @author gewuyou
 * @since 2024-11-07 18:38:37
 */
@Schema(description = "菜单响应DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuRespDTO {
    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private String id;
    /**
     * 菜单路由名称
     */
    @Schema(description = "菜单路由名称")
    private String name;
    /**
     * 菜单路由路径
     */
    @Schema(description = "菜单路由路径")
    private String path;
    /**
     * 菜单组件路径
     */
    @Schema(description = "菜单组件路径")
    private String component;
    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private String parentId;
    /**
     * 菜单排序
     */
    @Schema(description = "菜单排序")
    private Integer sort;
    /**
     * 菜单元数据
     */
    @Schema(description = "菜单元数据")
    private MenuMetaProps meta;
}
