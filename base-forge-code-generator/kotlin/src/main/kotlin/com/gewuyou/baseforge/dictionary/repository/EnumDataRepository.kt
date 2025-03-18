package com.gewuyou.baseforge.dictionary.repository;

import com.gewuyou.baseforge.dictionary.model.EnumData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
*
* 枚举数据表 Repository 接口
*
*
* @author gewuyou
* @since 2025-03-02
*/
interface EnumDataRepository : JpaRepository<EnumData, Long>, JpaSpecificationExecutor<EnumData>{

}