package com.gewuyou.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gewuyou.common.model.OperationLog;
import com.gewuyou.log.mapper.OperationLogMapper;
import com.gewuyou.log.service.IOperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-05
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
