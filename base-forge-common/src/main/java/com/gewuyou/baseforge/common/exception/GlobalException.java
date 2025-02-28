package com.gewuyou.baseforge.common.exception;


import com.gewuyou.baseforge.common.enums.ResponseInformation;

/**
 * 全局异常
 *
 * @author gewuyou
 * @since 2024-04-13 下午1:44:14
 */

public class GlobalException extends BaseException {

    public GlobalException(ResponseInformation responseInformation) {
        super(responseInformation);
    }

    public GlobalException(Throwable cause, ResponseInformation responseInformation) {
        super(responseInformation, cause);
    }
}
