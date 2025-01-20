package com.gewuyou.baseforge.dictionary.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.gewuyou.baseforge.dictionary.model.DictionaryItemTranslation
import org.apache.ibatis.annotations.Mapper

/**
 * <p>
 * 字典项翻译表，用于存储字典项的多语言翻译信息 Mapper 接口
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Mapper
interface DictionaryItemTranslationMapper : BaseMapper<DictionaryItemTranslation>
