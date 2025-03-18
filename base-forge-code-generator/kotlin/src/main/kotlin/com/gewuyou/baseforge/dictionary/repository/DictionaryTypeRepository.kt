package com.gewuyou.baseforge.dictionary.repository;

import com.gewuyou.baseforge.dictionary.model.DictionaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
*
* 字典类别表 Repository 接口
*
*
* @author gewuyou
* @since 2025-03-02
*/
interface DictionaryTypeRepository : JpaRepository<DictionaryType, String>, JpaSpecificationExecutor<DictionaryType>{

}