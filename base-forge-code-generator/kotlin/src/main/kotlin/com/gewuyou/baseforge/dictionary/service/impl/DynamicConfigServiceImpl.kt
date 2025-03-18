package com.gewuyou.baseforge.dictionary.service.impl

import com.gewuyou.baseforge.dictionary.model.DynamicConfig
import com.gewuyou.baseforge.dictionary.mapper.DynamicConfigMapper
import com.gewuyou.baseforge.dictionary.service.DynamicConfigService
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
class DynamicConfigServiceImpl : ServiceImpl<DynamicConfigMapper, DynamicConfig>(), DynamicConfigService {

}
