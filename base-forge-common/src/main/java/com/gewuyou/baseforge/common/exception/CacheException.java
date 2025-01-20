package com.gewuyou.baseforge.common.exception;

import com.gewuyou.baseforge.common.enums.ResponseInformation;

/**
 * 缓存异常
 *
 * @author gewuyou
 * @since 2024-10-03 14:51:55
 */
public class CacheException extends BaseException {

    public CacheException(ResponseInformation responseInformation) {
        super(responseInformation);
    }

    public CacheException(Throwable cause, ResponseInformation responseInformation) {
        super(responseInformation, cause);
    }
}
