package com.gewuyou.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 响应信息
 *
 * @author gewuyou
 * @since 2024-10-03 10:41:45
 */
@Getter
public enum ResponseInformation {
    OPERATION_SUCCESSFUL(HttpStatus.OK.value(), "操作成功"),
    JSON_SERIALIZE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "JSON序列化异常"),
    JSON_DESERIALIZE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "JSON反序列化异常");

    /**
     * 代码
     */
    private final int code;
    /**
     * 信息
     */
    private final String message;

    ResponseInformation(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
