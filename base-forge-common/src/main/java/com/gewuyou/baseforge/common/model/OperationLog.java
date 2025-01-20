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
 * 操作日志表
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-05
 */
@Schema(name = "OperationLog对象", description = "操作日志表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_operation_log")
public class OperationLog implements Serializable {

    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @Schema(description = "日志主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @TableField("user_id")
    private String userId;

    /**
     * 调用类
     */
    @Schema(description = "调用类")
    @TableField("opt_class")
    private String optClass;

    /**
     * 操作路径
     */
    @Schema(description = "操作路径")
    @TableField("opt_url")
    private String optUrl;

    /**
     * 操作类型
     */
    @Schema(description = "操作类型")
    @TableField("opt_type")
    private String optType;

    /**
     * 操作方法
     */
    @Schema(description = "操作方法")
    @TableField("opt_method")
    private String optMethod;

    /**
     * 操作描述
     */
    @Schema(description = "操作描述")
    @TableField("opt_desc")
    private String optDesc;

    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    @TableField("request_method")
    private String requestMethod;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    @TableField("request_param")
    private String requestParam;

    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    @TableField("response_data")
    private String responseData;

    /**
     * 操作状态（0:失败, 1:成功, 2:待处理,3:操作异常）
     */
    @Schema(description = "操作状态（0:失败, 1:成功, 2:待处理,3:操作异常）")
    @TableField("status")
    private Byte status;

    /**
     * 操作人用户名
     */
    @Schema(description = "操作人用户名")
    @TableField("user_name")
    private String userName;

    /**
     * 用户登录ip
     */
    @Schema(description = "用户登录ip")
    @TableField("ip_address")
    private String ipAddress;

    /**
     * ip属地
     */
    @Schema(description = "ip属地")
    @TableField("ip_source")
    private String ipSource;

    /**
     * 耗时
     */
    @Schema(description = "耗时")
    @TableField("duration")
    private Long duration;

    /**
     * 日志时间
     */
    @Schema(description = "日志时间")
    @TableField(value = "log_time", fill = FieldFill.INSERT)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime logTime;
}
