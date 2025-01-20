package com.gewuyou.baseforge.dictionary.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import org.apache.ibatis.annotations.Mapper

/**
 * <p>
 * 字典项表 Mapper 接口
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Mapper
interface DictionaryItemMapper : BaseMapper<DictionaryItem>
