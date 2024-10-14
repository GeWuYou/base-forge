package com.gewuyou.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gewuyou.common.enums.ResponseInformation;
import com.gewuyou.common.exception.GlobalException;
import com.gewuyou.common.utils.ExceptionUtil;
import com.gewuyou.common.utils.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p>
 * 异常日志表	
 * </p>
 *
 * @author gewuyou
 * @since 2024-10-05
 */
@Schema(name="ExceptionLog对象", description="异常日志表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_exception_log")
public class ExceptionLog extends BaseModel implements Serializable  {
    @Schema(hidden = true)
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
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

    public static ExceptionLog toExceptionLog(JoinPoint joinPoint, Exception e, ObjectMapper objectMapper){
        try {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String ipAddress = IpUtil.getIpAddress(Objects.requireNonNull(request));
            String ipSource = IpUtil.getIpSource(ipAddress);
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            String optMethod = className + "." + methodName;
            Operation operation = method.getAnnotation(Operation.class);
            return ExceptionLog.builder()
                    .optUri(Objects.requireNonNull(request).getRequestURI())
                    .optMethod(optMethod)
                    .requestMethod(request.getMethod())
                    .requestParam(objectMapper.writeValueAsString(joinPoint.getArgs()))
                    .optDesc(operation.description())
                    .exceptionInfo(ExceptionUtil.getTrace(e))
                    .ipAddress(ipAddress)
                    .ipSource(ipSource)
                    .build();
        } catch (JsonProcessingException ex) {
            throw new GlobalException(ResponseInformation.JSON_SERIALIZE_ERROR);
        }
    }
}
