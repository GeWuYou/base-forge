package com.gewuyou.baseforge.dictionary.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.dictionary.mapper.DictionaryItemTranslationMapper
import com.gewuyou.baseforge.dictionary.model.DictionaryItemTranslation
import com.gewuyou.baseforge.dictionary.service.DictionaryItemTranslationService
import org.springframework.stereotype.Service

/**
 * <p>
 * 字典项翻译表，用于存储字典项的多语言翻译信息 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Service
class DictionaryItemTranslationServiceImpl :
    ServiceImpl<DictionaryItemTranslationMapper, DictionaryItemTranslation>(), DictionaryItemTranslationService
