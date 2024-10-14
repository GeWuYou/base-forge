package com.gewuyou.common.exception;

import com.gewuyou.common.enums.ResponseInformation;

/**
 * 认证客户端异常
 *
 * @author gewuyou
 * @since 2024-04-13 下午1:44:14
 */
public class AuthClientException extends BaseException {

    public AuthClientException(ResponseInformation responseInformation) {
        super(responseInformation);
    }


    public AuthClientException(ResponseInformation responseInformation, Throwable cause) {
        super(responseInformation, cause);
    }
}
