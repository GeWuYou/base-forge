package com.gewuyou.baseforge.common.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 异常日志表
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-05
 */
@Schema(name = "ExceptionLog对象", description = "异常日志表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_exception_log")
public class ExceptionLog implements Serializable {
    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 请求uri
     */
    @Schema(description = "请求uri")
    @TableField("opt_uri")
    private String optUri;

    /**
     * 操作方法
     */
    @Schema(description = "操作方法")
    @TableField("opt_method")
    private String optMethod;

    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    @TableField("request_method")
    private String requestMethod;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    @TableField("request_param")
    private String requestParam;

    /**
     * 操作描述
     */
    @Schema(description = "操作描述")
    @TableField("opt_desc")
    private String optDesc;

    /**
     * 异常信息
     */
    @Schema(description = "异常信息")
    @TableField("exception_info")
    private String exceptionInfo;

    /**
     * ip地址
     */
    @Schema(description = "ip地址")
    @TableField("ip_address")
    private String ipAddress;

    /**
     * ip属地
     */
    @Schema(description = "ip属地")
    @TableField("ip_source")
    private String ipSource;

    /**
     * 日志时间
     */
    @Schema(description = "日志时间")
    @TableField(value = "log_time", fill = FieldFill.INSERT)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime logTime;
}
