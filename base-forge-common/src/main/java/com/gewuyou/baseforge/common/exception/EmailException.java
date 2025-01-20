package com.gewuyou.baseforge.common.exception;

import com.gewuyou.baseforge.common.enums.ResponseInformation;

/**
 * 邮件异常
 *
 * @author gewuyou
 * @since 2024-10-05 20:00:38
 */
public class EmailException extends BaseException{

    public EmailException(ResponseInformation responseInformation) {
        super(responseInformation);
    }

    public EmailException(ResponseInformation responseInformation, Throwable cause) {
        super(responseInformation, cause);
    }
}
