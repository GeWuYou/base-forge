package com.gewuyou.baseforge.dictionary.service.impl

import com.gewuyou.baseforge.dictionary.model.EnumData
import com.gewuyou.baseforge.dictionary.mapper.EnumDataMapper
import com.gewuyou.baseforge.dictionary.service.EnumDataService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 *
 * 枚举数据表 服务实现类
 *
 * @author gewuyou
 * @since 2025-03-02
 */
@Service
class EnumDataServiceImpl : ServiceImpl<EnumDataMapper, EnumData>(), EnumDataService {

}
