package com.gewuyou.baseforge.authorization.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.authorization.mapper.AuthRoleMapper
import com.gewuyou.baseforge.authorization.model.AuthRole
import com.gewuyou.baseforge.authorization.service.AuthRoleService
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
class AuthRoleServiceImpl : ServiceImpl<AuthRoleMapper, AuthRole>(), AuthRoleService
