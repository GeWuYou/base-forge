package com.gewuyou.baseforge.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 资源角色 DTO
 *
 * @author gewuyou
 * @since 2024-11-09 11:16:44
 */
@Data
@Builder
public class ResourceRoleDTO {
    /**
     * 资源路径
     */
    @Schema(description = "资源路径")
    private String url;

    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    private String requestMethod;

    /**
     * 角色列表
     */
    @Schema(description = "角色列表")
    private List<RoleListDTO> roleList;

    /**
     * 是否匿名访问
     */
    @Schema(description = "是否匿名访问")
    private Boolean isAnonymous;
}
