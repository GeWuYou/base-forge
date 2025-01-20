package com.gewuyou.baseforge.common.dto.mail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 简单邮件数据传输对象
 *
 * @author gewuyou
 * @since 2024-10-15 22:56:36
 */
@Schema(description = "简单邮件数据传输对象")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMailDTO implements Serializable {
    /**
     * 收件人邮箱地址
     */
    @Schema(description = "收件人邮箱地址")
    private String to;
    /**
     * 邮件主题
     */
    @Schema(description = "邮件主题")
    private String subject;
    /**
     * 邮件内容
     */
    @Schema(description = "邮件内容")
    private String content;
}
