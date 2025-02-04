package com.gewuyou.baseforge.authorization.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.authorization.mapper.AuthRolePermissionMapper
import com.gewuyou.baseforge.authorization.model.AuthRolePermission
import com.gewuyou.baseforge.authorization.service.AuthRolePermissionService
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
open class AuthRolePermissionServiceImpl : ServiceImpl<AuthRolePermissionMapper, AuthRolePermission>(),
    AuthRolePermissionService {

}
