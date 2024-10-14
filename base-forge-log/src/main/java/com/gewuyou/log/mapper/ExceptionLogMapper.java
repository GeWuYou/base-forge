package com.gewuyou.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gewuyou.common.model.ExceptionLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 异常日志表	 Mapper 接口
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-05
 */
@Mapper
public interface ExceptionLogMapper extends BaseMapper<ExceptionLog> {

}
