package com.gewuyou.baseforge.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gewuyou.baseforge.authorization.model.AuthRole;
import com.gewuyou.baseforge.authorization.model.AuthUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关系表 Mapper 接口
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Mapper
public interface AuthUserRoleMapper extends BaseMapper<AuthUserRole> {
    List<AuthRole> getRoleByUserAuthId(@Param("userAuthId") Long userAuthId);
}
