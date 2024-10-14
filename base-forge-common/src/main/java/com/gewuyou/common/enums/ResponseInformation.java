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
    JSON_DESERIALIZE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "JSON反序列化异常"),
    JSON_SERIALIZE_OR_DESERIALIZE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "JSON序列化或反序列化异常"),
    ERROR_GET_LOCAL_IP_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(),"获取本地IP失败" ),
    URL_ENCODE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"URL编码异常" ),
    PUBLIC_KEY_LOCATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"公钥位置异常，请检查配置" ),
    LOG_BUILD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(),"日志构建失败" ),
    USERNAME_EMPTY(HttpStatus.BAD_REQUEST.value(), "用户名不能为空" ),
    PASSWORD_EMPTY(HttpStatus.BAD_REQUEST.value(), "密码不能为空" ),
    JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST.value(), "JSON解析异常" ),
    REPEAT_REQUEST(HttpStatus.BAD_REQUEST.value(), "重复请求" ),
    NOT_SUPPORTED_OPERATION(HttpStatus.NOT_IMPLEMENTED.value(), "不支持的操作" ),
    NOT_SUPPORTED_OAUTH2_LOGIN_TYPE(HttpStatus.NOT_IMPLEMENTED.value(), "不支持的OAuth2登录类型" );


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
