package com.gewuyou.baseforge.authorization.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.gewuyou.baseforge.authorization.model.AuthRolePermission
import org.apache.ibatis.annotations.Mapper

/**
 * <p>
 * 角色权限关系表 Mapper 接口
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Mapper
interface AuthRolePermissionMapper : BaseMapper<AuthRolePermission>
