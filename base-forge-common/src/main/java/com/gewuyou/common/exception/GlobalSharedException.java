package com.gewuyou.common.exception;


import com.gewuyou.common.enums.ResponseInformation;

/**
 * 全局共享异常
 *
 * @author gewuyou
 * @since 2024-04-13 下午1:44:14
 */

public class GlobalSharedException extends BaseException {

    public GlobalSharedException(ResponseInformation responseInformation) {
        super(responseInformation);
    }

    public GlobalSharedException(Throwable cause, ResponseInformation responseInformation) {
        super(responseInformation, cause);
    }
}
