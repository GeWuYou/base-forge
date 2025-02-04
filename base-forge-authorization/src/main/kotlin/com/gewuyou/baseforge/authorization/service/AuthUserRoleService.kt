package com.gewuyou.baseforge.authorization.service

import com.baomidou.mybatisplus.extension.service.IService
import com.gewuyou.baseforge.authorization.model.AuthRole
import com.gewuyou.baseforge.authorization.model.AuthUserRole
import org.apache.ibatis.annotations.Param

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
interface AuthUserRoleService : IService<AuthUserRole>{
    fun getRoleByUserAuthId(@Param("userAuthId") userAuthId: Long): List<AuthRole>
}
