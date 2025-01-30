package com.gewuyou.baseforge.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.auth.mapper.AuthRoleMapper
import com.gewuyou.baseforge.auth.model.AuthRole
import com.gewuyou.baseforge.auth.service.AuthRoleService
import org.springframework.stereotype.Service

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Service
open class AuthRoleServiceImpl : ServiceImpl<AuthRoleMapper, AuthRole>(), AuthRoleService {

}
