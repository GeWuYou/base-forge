package com.gewuyou.baseforge.dictionary.repository

import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * 词典项存储库
 *
 * @author gewuyou
 * @since 2025-01-21 14:05:11
 */
interface DictionaryItemRepository : JpaRepository<DictionaryItem, Long>, JpaSpecificationExecutor<DictionaryItem> {
    fun findByCodeAndCategoryId(code: String, categoryId: Long): DictionaryItem?
    fun findByCodeAndName(code: String, name: String): DictionaryItem?
}