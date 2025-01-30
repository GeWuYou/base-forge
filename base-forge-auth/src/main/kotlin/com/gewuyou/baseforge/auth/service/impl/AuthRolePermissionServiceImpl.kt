package com.gewuyou.baseforge.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.auth.mapper.AuthRolePermissionMapper
import com.gewuyou.baseforge.auth.model.AuthRolePermission
import com.gewuyou.baseforge.auth.service.AuthRolePermissionService
import org.springframework.stereotype.Service

/**
 * <p>
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Service
open class AuthRolePermissionServiceImpl : ServiceImpl<AuthRolePermissionMapper, AuthRolePermission>(), AuthRolePermissionService {

}
