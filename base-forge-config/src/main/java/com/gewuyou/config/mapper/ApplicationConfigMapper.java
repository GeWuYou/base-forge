package com.gewuyou.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gewuyou.common.model.ApplicationConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 程序配置表，用于存储不同模块的配置信息 Mapper 接口
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-11
 */
@Mapper
public interface ApplicationConfigMapper extends BaseMapper<ApplicationConfig> {

}
