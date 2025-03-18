package com.gewuyou.baseforge.dictionary.repository;

import com.gewuyou.baseforge.dictionary.model.EnumDataHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
*
* 枚举数据历史表 Repository 接口
*
*
* @author gewuyou
* @since 2025-03-02
*/
interface EnumDataHistoryRepository : JpaRepository<EnumDataHistory, Long>, JpaSpecificationExecutor<EnumDataHistory>{

}