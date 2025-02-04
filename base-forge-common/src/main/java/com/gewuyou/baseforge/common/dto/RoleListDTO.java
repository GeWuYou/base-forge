package com.gewuyou.baseforge.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 角色列表 DTO
 *
 * @author gewuyou
 * @since 2024-11-09 18:10:45
 */
@Data
@Builder
public class RoleListDTO {

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 是否禁用 0否 ：1是
     */
    @Schema(description = "是否禁用 0否 ：1是")
    private Boolean isDisabled;
}
