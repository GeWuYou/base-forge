package com.gewuyou.baseforge.dictionary.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.dictionary.mapper.DictionaryItemHistoryMapper
import com.gewuyou.baseforge.dictionary.model.DictionaryItemHistory
import com.gewuyou.baseforge.dictionary.service.DictionaryItemHistoryService
import org.springframework.stereotype.Service

/**
 * <p>
 * 字典项历史表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Service
class DictionaryItemHistoryServiceImpl : ServiceImpl<DictionaryItemHistoryMapper, DictionaryItemHistory>(),
    DictionaryItemHistoryService
