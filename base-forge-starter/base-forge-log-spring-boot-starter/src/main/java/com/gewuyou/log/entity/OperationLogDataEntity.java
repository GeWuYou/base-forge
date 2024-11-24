package com.gewuyou.log.entity;

import lombok.*;

/**
 * 操作日志数据实体
 *
 * @author gewuyou
 * @since 2024-11-21 15:00:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationLogDataEntity {
    /**
     * 操作路径
     */
    private String optUrl;
    /**
     * 操作方法
     */
    private String optMethod;
    /**
     * 操作类型
     */
    private String optType;
    /**
     * 操作描述
     */
    private String optDesc;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 返回结果
     */
    private String result;
    /**
     * ip地址
     */
    private String ipAddress;
    /**
     * 用户安全认证id
     */
    private String userAuthId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 耗时
     */
    private Long duration;
}
