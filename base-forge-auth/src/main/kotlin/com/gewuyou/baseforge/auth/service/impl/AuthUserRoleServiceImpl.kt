package com.gewuyou.baseforge.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.auth.mapper.AuthUserRoleMapper
import com.gewuyou.baseforge.auth.model.AuthUserRole
import com.gewuyou.baseforge.auth.service.AuthUserRoleService
import org.springframework.stereotype.Service

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Service
open class AuthUserRoleServiceImpl : ServiceImpl<AuthUserRoleMapper, AuthUserRole>(), AuthUserRoleService {

}
