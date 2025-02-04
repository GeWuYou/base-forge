package com.gewuyou.baseforge.authorization.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.authorization.mapper.AuthUserRoleMapper
import com.gewuyou.baseforge.authorization.model.AuthRole
import com.gewuyou.baseforge.authorization.model.AuthUserRole
import com.gewuyou.baseforge.authorization.service.AuthUserRoleService
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
class AuthUserRoleServiceImpl : ServiceImpl<AuthUserRoleMapper, AuthUserRole>(), AuthUserRoleService{
    override fun getRoleByUserAuthId(userAuthId: Long): List<AuthRole> {
        return baseMapper.getRoleByUserAuthId(userAuthId)
    }

}
