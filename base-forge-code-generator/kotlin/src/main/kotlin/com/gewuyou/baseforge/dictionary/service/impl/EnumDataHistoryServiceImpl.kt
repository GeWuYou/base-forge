package com.gewuyou.baseforge.dictionary.service.impl

import com.gewuyou.baseforge.dictionary.model.EnumDataHistory
import com.gewuyou.baseforge.dictionary.mapper.EnumDataHistoryMapper
import com.gewuyou.baseforge.dictionary.service.EnumDataHistoryService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 *
 * 枚举数据历史表 服务实现类
 *
 * @author gewuyou
 * @since 2025-03-02
 */
@Service
class EnumDataHistoryServiceImpl : ServiceImpl<EnumDataHistoryMapper, EnumDataHistory>(), EnumDataHistoryService {

}
