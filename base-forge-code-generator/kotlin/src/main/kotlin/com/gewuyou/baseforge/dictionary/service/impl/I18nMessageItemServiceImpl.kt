package com.gewuyou.baseforge.dictionary.service.impl

import com.gewuyou.baseforge.dictionary.model.I18nMessageItem
import com.gewuyou.baseforge.dictionary.mapper.I18nMessageItemMapper
import com.gewuyou.baseforge.dictionary.service.I18nMessageItemService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 *
 * i18n信息项表 服务实现类
 *
 * @author gewuyou
 * @since 2025-03-02
 */
@Service
class I18nMessageItemServiceImpl : ServiceImpl<I18nMessageItemMapper, I18nMessageItem>(), I18nMessageItemService {

}
