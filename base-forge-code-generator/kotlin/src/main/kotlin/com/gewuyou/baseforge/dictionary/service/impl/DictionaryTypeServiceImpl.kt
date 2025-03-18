package com.gewuyou.baseforge.dictionary.service.impl

import com.gewuyou.baseforge.dictionary.model.DictionaryType
import com.gewuyou.baseforge.dictionary.mapper.DictionaryTypeMapper
import com.gewuyou.baseforge.dictionary.service.DictionaryTypeService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 *
 * 字典类别表 服务实现类
 *
 * @author gewuyou
 * @since 2025-03-02
 */
@Service
class DictionaryTypeServiceImpl : ServiceImpl<DictionaryTypeMapper, DictionaryType>(), DictionaryTypeService {

}
