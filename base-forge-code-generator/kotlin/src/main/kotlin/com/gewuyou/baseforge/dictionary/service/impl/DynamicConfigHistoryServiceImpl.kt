package com.gewuyou.baseforge.dictionary.service.impl

import com.gewuyou.baseforge.dictionary.model.DynamicConfigHistory
import com.gewuyou.baseforge.dictionary.mapper.DynamicConfigHistoryMapper
import com.gewuyou.baseforge.dictionary.service.DynamicConfigHistoryService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 *
 * 动态配置表  服务实现类
 *
 * @author gewuyou
 * @since 2025-03-02
 */
@Service
class DynamicConfigHistoryServiceImpl : ServiceImpl<DynamicConfigHistoryMapper, DynamicConfigHistory>(), DynamicConfigHistoryService {

}
