package com.gewuyou.baseforge.dictionary.service.impl

import com.gewuyou.baseforge.dictionary.model.DictionaryTypeHistory
import com.gewuyou.baseforge.dictionary.mapper.DictionaryTypeHistoryMapper
import com.gewuyou.baseforge.dictionary.service.DictionaryTypeHistoryService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 *
 * 字典类别表历史表 服务实现类
 *
 * @author gewuyou
 * @since 2025-03-02
 */
@Service
class DictionaryTypeHistoryServiceImpl : ServiceImpl<DictionaryTypeHistoryMapper, DictionaryTypeHistory>(), DictionaryTypeHistoryService {

}
