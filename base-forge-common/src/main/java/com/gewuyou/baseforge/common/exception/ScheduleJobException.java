package com.gewuyou.baseforge.common.exception;

import com.gewuyou.baseforge.common.enums.ResponseInformation;

/**
 * 计划作业异常
 *
 * @author gewuyou
 * @since 2024-10-19 23:47:00
 */
public class ScheduleJobException extends BaseException {
    public ScheduleJobException(ResponseInformation responseInformation) {
        super(responseInformation);
    }

    public ScheduleJobException(ResponseInformation responseInformation, Throwable cause) {
        super(responseInformation, cause);
    }
}
