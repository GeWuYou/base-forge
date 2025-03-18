package com.gewuyou.baseforge.dictionary.repository;

import com.gewuyou.baseforge.dictionary.model.DynamicConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
*
* 动态配置表  Repository 接口
*
*
* @author gewuyou
* @since 2025-03-02
*/
interface DynamicConfigRepository : JpaRepository<DynamicConfig, Long>, JpaSpecificationExecutor<DynamicConfig>{

}