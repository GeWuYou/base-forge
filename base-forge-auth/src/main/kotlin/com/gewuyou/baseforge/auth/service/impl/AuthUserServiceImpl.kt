package com.gewuyou.baseforge.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.auth.mapper.AuthUserMapper
import com.gewuyou.baseforge.auth.model.AuthUser
import com.gewuyou.baseforge.auth.service.AuthUserService
import org.springframework.stereotype.Service

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-15
 */
@Service
open class AuthUserServiceImpl : ServiceImpl<AuthUserMapper, AuthUser>(), AuthUserService {

}
