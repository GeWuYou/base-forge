package com.gewuyou.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gewuyou.common.model.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-05
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

}
