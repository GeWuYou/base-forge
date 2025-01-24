package com.gewuyou.baseforge.dictionary.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.autoconfigure.util.BeanCopyUtil
import com.gewuyou.baseforge.dictionary.enums.OperationType
import com.gewuyou.baseforge.dictionary.mapper.DictionaryItemHistoryMapper
import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import com.gewuyou.baseforge.dictionary.model.DictionaryItemHistory
import com.gewuyou.baseforge.dictionary.repository.DictionaryItemHistoryRepository
import com.gewuyou.baseforge.dictionary.service.DictionaryItemHistoryService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

/**
 * <p>
 * 字典项历史表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Service
class DictionaryItemHistoryServiceImpl(
    private val dictionaryItemHistoryRepository: DictionaryItemHistoryRepository
) : ServiceImpl<DictionaryItemHistoryMapper, DictionaryItemHistory>(),
    DictionaryItemHistoryService {
    /**
     * 保存历史记录
     * @param dictionaryItem 字典项
     * @param operationType 操作类型
     */
    override fun saveDictionaryItemHistory(dictionaryItem: DictionaryItem, operationType: OperationType) {
        dictionaryItem.let {
            BeanCopyUtil.copyPropertiesWithValidation(
                dictionaryItem,
                DictionaryItemHistory::class.java,
                Objects::nonNull,
                "id"
            )
                .let {
                    it.dictionaryItemId = dictionaryItem.id
                    it.operationTimestamp = LocalDateTime.now()
                    it.operationType = operationType.getValue()
                    dictionaryItemHistoryRepository.save(it)
                }
        }
    }

}
