package com.gewuyou.baseforge.common.dto.mail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * 简单附件邮件 DTO
 *
 * @author gewuyou
 * @since 2024-10-15 23:19:13
 */
@Schema(description = "简单附件邮件 DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleAttachmentsMailDTO implements Serializable {
    /**
     * 收件人邮箱
     */
    @Schema(description = "收件人邮箱")
    private String to;
    /**
     * 主题
     */
    @Schema(description = "主题")
    private String subject;
    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;
    /**
     * 附件列表
     */
    @Schema(description = "附件列表")
    private transient List<MultipartFile> attachments;
}
