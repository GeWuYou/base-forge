package com.gewuyou.baseforge.dictionary.repository;

import com.gewuyou.baseforge.dictionary.model.I18nMessageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
*
* i18n信息项表 Repository 接口
*
*
* @author gewuyou
* @since 2025-03-02
*/
interface I18nMessageItemRepository : JpaRepository<I18nMessageItem, Long>, JpaSpecificationExecutor<I18nMessageItem>{

}