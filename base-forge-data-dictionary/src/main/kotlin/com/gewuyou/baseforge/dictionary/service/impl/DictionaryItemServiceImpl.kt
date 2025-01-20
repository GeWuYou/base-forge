package com.gewuyou.baseforge.dictionary.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.gewuyou.baseforge.dictionary.mapper.DictionaryItemMapper
import com.gewuyou.baseforge.dictionary.model.DictionaryItem
import com.gewuyou.baseforge.dictionary.service.DictionaryItemService
import org.springframework.stereotype.Service

/**
 * <p>
 * 字典项表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2025-01-16
 */
@Service
class DictionaryItemServiceImpl : ServiceImpl<DictionaryItemMapper, DictionaryItem>(), DictionaryItemService
