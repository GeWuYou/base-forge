package com.gewuyou.baseforge.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gewuyou.baseforge.auth.dto.AuthPermissionRoleDto;
import com.gewuyou.baseforge.auth.model.AuthPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 权限表 Mapper接口
 *
 * @author gewuyou
 * @since 2025-01-27 17:40:55
 */
@Mapper
public interface AuthPermissionMapper extends BaseMapper<AuthPermission> {
    /**
     * 获取权限角色列表
     * @return 权限角色列表
     */
    List<AuthPermissionRoleDto> getAuthPermissionRoleDtoList();
}
