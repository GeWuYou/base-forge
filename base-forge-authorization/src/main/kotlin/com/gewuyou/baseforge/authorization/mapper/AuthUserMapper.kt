package com.gewuyou.baseforge.authorization.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.gewuyou.baseforge.authorization.model.AuthUser
import org.apache.ibatis.annotations.Mapper

/**
 * <p>
 * 用户认证表 Mapper 接口
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Mapper
interface AuthUserMapper : BaseMapper<AuthUser>
