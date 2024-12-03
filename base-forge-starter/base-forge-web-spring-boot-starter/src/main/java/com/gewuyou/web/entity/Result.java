package com.gewuyou.web.entity;


import com.gewuyou.i18n.entity.ResponseInformation;
import com.gewuyou.util.I18nMessageUtil;
import com.gewuyou.web.i18n.enums.WebResponseInformation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 返回结果包装类
 *
 * @author gewuyou
 * @since 2024-04-13 上午12:24:04
 */
@Schema(description = "返回结果包装类")
@Data
public class Result<T> implements Serializable {
    /**
     * 结果代码
     */
    @Schema(description = "结果代码", example = "200")
    private int code;
    /**
     * 是否成功
     */
    @Schema(description = "是否成功")
    private boolean success;
    /**
     * 响应信息
     */
    @Schema(description = "响应信息")
    private String message;
    /**
     * 结果数据
     */
    @Schema(description = "结果数据")
    private transient T data;

    private Result(ResponseInformation responseInformation, boolean success, T data, MessageSource messageSource, Object... args) {
        this.code = responseInformation.getResponseCode();
        this.success = success;
        this.message = messageSource.getMessage(responseInformation.getResponseI8nMessageCode(), args, LocaleContextHolder.getLocale());
        this.data = data;
    }

    private Result(ResponseInformation responseInformation, boolean success, T data, Object... args) {
        this.code = responseInformation.getResponseCode();
        this.success = success;
        this.message = I18nMessageUtil.getI18nMessageSource().getMessage(responseInformation.getResponseI8nMessageCode(), args, LocaleContextHolder.getLocale());
        this.data = data;
    }

    private Result(ResponseInformation responseInformation, boolean success, T data, MessageSource messageSource) {
        this.code = responseInformation.getResponseCode();
        this.success = success;
        this.message = messageSource.getMessage(responseInformation.getResponseI8nMessageCode(), null, LocaleContextHolder.getLocale());
        this.data = data;
    }

    private Result(ResponseInformation responseInformation, boolean success, T data) {
        this.code = responseInformation.getResponseCode();
        this.success = success;
        this.message = I18nMessageUtil.getI18nMessageSource().getMessage(responseInformation.getResponseI8nMessageCode(), null, LocaleContextHolder.getLocale());
        this.data = data;
    }


    private Result(Integer code, String i18nMessageCode, MessageSource messageSource, Object... args) {
        this.code = code;
        this.message = messageSource.getMessage(i18nMessageCode, args, LocaleContextHolder.getLocale());
        this.success = false;
    }

    private Result(Integer code, String i18nMessageCode, Object... args) {
        this.code = code;
        this.message = I18nMessageUtil.getI18nMessageSource().getMessage(i18nMessageCode, args, LocaleContextHolder.getLocale());
        this.success = false;
    }

    private Result(Integer code, String i18nMessageCode, MessageSource messageSource) {
        this.code = code;
        this.message = messageSource.getMessage(i18nMessageCode, null, LocaleContextHolder.getLocale());
        this.success = false;
    }

    private Result(Integer code, String i18nMessageCode) {
        this.code = code;
        this.message = I18nMessageUtil.getI18nMessageSource().getMessage(i18nMessageCode, null, LocaleContextHolder.getLocale());
        this.success = false;
    }

    private Result(ResponseInformation responseInformation, MessageSource messageSource, Object... args) {
        this.code = responseInformation.getResponseCode();
        this.message = messageSource.getMessage(responseInformation.getResponseI8nMessageCode(), args, LocaleContextHolder.getLocale());
        this.success = false;
    }

    private Result(ResponseInformation responseInformation, Object... args) {
        this.code = responseInformation.getResponseCode();
        this.message = I18nMessageUtil.getI18nMessageSource().getMessage(responseInformation.getResponseI8nMessageCode(), args, LocaleContextHolder.getLocale());
        this.success = false;
    }

    /**
     * 无数据成功返回
     *
     * @return 成功结果
     */
    public static <T> Result<T> success(MessageSource messageSource) {
        return new Result<>(WebResponseInformation.OPERATION_SUCCESSFUL, true, null, messageSource);
    }

    /**
     * 无数据成功返回
     *
     * @return 成功结果
     */
    public static <T> Result<T> success() {
        return new Result<>(WebResponseInformation.OPERATION_SUCCESSFUL, true, null);
    }

    /**
     * 设置响应信息成功返回
     *
     * @param responseInformation 响应代码枚举
     * @param <T>          泛型
     * @return 成功结果
     */
    public static <T> Result<T> success(ResponseInformation responseInformation, MessageSource messageSource) {
        return new Result<>(responseInformation, true, null, messageSource);
    }

    /**
     * 设置响应信息成功返回
     *
     * @param responseInformation 响应代码枚举
     * @param <T>          泛型
     * @return 成功结果
     */
    public static <T> Result<T> success(ResponseInformation responseInformation) {
        return new Result<>(responseInformation, true, null);
    }

    /**
     * 有数据成功返回
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 成功结果
     */

    public static <T> Result<T> success(T data, MessageSource messageSource) {
        return new Result<>(WebResponseInformation.OPERATION_SUCCESSFUL, true, data, messageSource);
    }

    /**
     * 有数据成功返回
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 成功结果
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(WebResponseInformation.OPERATION_SUCCESSFUL, true, data);
    }

    /**
     * 有数据设置响应信息成功返回
     *
     * @param responseInformation 响应代码枚举
     * @param data         数据
     * @param <T>          泛型
     * @return 成功结果
     */
    public static <T> Result<T> success(ResponseInformation responseInformation, T data, MessageSource messageSource) {
        return new Result<>(responseInformation, true, data, messageSource);
    }

    /**
     * 有数据设置响应信息成功返回
     *
     * @param responseInformation 响应代码枚举
     * @param data         数据
     * @param <T>          泛型
     * @return 成功结果
     */
    public static <T> Result<T> success(ResponseInformation responseInformation, T data) {
        return new Result<>(responseInformation, true, data);
    }

    /**
     * 失败返回
     *
     * @param code        响应代码
     * @param i18nMessageCode 响应信息i18n代码
     * @return 失败结果
     */
    public static Result<String> failure(int code, String i18nMessageCode) {
        return new Result<>(code, i18nMessageCode);
    }

    /**
     * 失败返回
     *
     * @param code        响应代码
     * @param i18nMessageCode 响应信息i18n代码
     * @param args        占位符参数
     * @return 失败结果
     */
    public static Result<String> failure(int code, String i18nMessageCode, Object... args) {
        return new Result<>(code, i18nMessageCode, args);
    }

    /**
     * 失败返回
     *
     * @return 失败结果
     */
    public static Result<String> failure(String i18nMessageCode, MessageSource messageSource) {
        return new Result<>(HttpStatus.BAD_REQUEST.value(), i18nMessageCode, messageSource);
    }


    /**
     * 失败返回
     *
     * @return 失败结果
     */
    public static Result<String> failure(String i18nMessageCode) {
        return new Result<>(HttpStatus.BAD_REQUEST.value(), i18nMessageCode);
    }

    /**
     * 失败返回
     *
     * @return 失败结果
     */
    public static Result<String> failure(int code, String i18nMessageCode, MessageSource messageSource) {
        return new Result<>(code, i18nMessageCode, messageSource);
    }

    /**
     * 失败返回
     *
     * @return 失败结果
     */
    public static Result<String> failure(ResponseInformation responseInformation, MessageSource messageSource, Object... args) {
        return new Result<>(responseInformation, messageSource, args);
    }

    /**
     * 失败返回
     *
     * @param responseInformation 响应代码枚举
     * @param args         占位符参数
     * @return 失败结果
     */
    public static Result<String> failure(ResponseInformation responseInformation, Object... args) {
        return new Result<>(responseInformation, args);
    }

    /**
     * 设置响应信息失败返回
     *
     * @param responseInformation  响应代码枚举
     * @param messageSource 消息源
     * @return 失败结果
     */
    public static Result<String> failure(ResponseInformation responseInformation, MessageSource messageSource) {
        return new Result<>(responseInformation, false, null, messageSource);
    }

    /**
     * 设置响应信息失败返回
     *
     * @param responseInformation 响应代码枚举
     * @return 失败结果
     */
    public static Result<String> failure(ResponseInformation responseInformation) {
        return new Result<>(responseInformation, false, null);
    }
}



