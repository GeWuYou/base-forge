package com.gewuyou.baseforge.dictionary.repository

import com.gewuyou.baseforge.dictionary.model.DictionaryItemHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 *词典项历史存储库
 *
 * @since 2025-01-23 00:44:11
 * @author gewuyou
 */
interface DictionaryItemHistoryRepository: JpaRepository<DictionaryItemHistory, Long>,
    JpaSpecificationExecutor<DictionaryItemHistory>