package com.gewuyou.baseforge.dictionary.service

import com.baomidou.mybatisplus.extension.service.IService
import com.gewuyou.baseforge.dictionary.enums.OperationType
import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import com.gewuyou.baseforge.dictionary.model.DictionaryItemHistory

/**
 * <p>
 * 字典项历史表 服务类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
interface DictionaryItemHistoryService : IService<DictionaryItemHistory>{
    /**
     * 保存历史记录
     * @param dictionaryItem 字典项
     * @param operationType 操作类型
     */
    fun saveDictionaryItemHistory(dictionaryItem: DictionaryItem,operationType: OperationType)
}
