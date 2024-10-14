package com.gewuyou.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gewuyou.common.model.ExceptionLog;
import com.gewuyou.log.mapper.ExceptionLogMapper;
import com.gewuyou.log.service.IExceptionLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 异常日志表	 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-05
 */
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements IExceptionLogService {

}
