package com.gewuyou.common.exception;

import com.gewuyou.common.enums.ResponseInformation;

/**
 * 日志异常
 *
 * @author gewuyou
 * @since 2024-10-05 20:00:38
 */
public class LogException extends BaseException{

    public LogException(ResponseInformation responseInformation) {
        super(responseInformation);
    }

    public LogException(ResponseInformation responseInformation, Throwable cause) {
        super(responseInformation, cause);
    }
}
