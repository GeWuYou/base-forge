package com.gewuyou.baseforge.auth.repository

import com.gewuyou.baseforge.auth.model.AuthRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 *身份验证角色存储库
 *
 * @since 2025-01-28 16:58:31
 * @author gewuyou
 */
interface AuthRoleRepository : JpaRepository<AuthRole, Long>, JpaSpecificationExecutor<AuthRole>