package com.gewuyou.baseforge.common.dto.mail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * HTML 附件邮件 DTO
 *
 * @author gewuyou
 * @since 2024-10-15 23:44:56
 */
@Schema(description = "HTML 附件邮件 DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HTMLAttachmentsMailDTO implements Serializable {
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
     * 内容模板参数
     */
    @Schema(description = "内容模板参数")
    private Map<String, Object> contentMap;
    /**
     * HTML 模板路径
     */
    @Schema(description = "HTML 模板路径")
    private String template;
    /**
     * 附件列表
     */
    @Schema(description = "附件列表")
    private List<MultipartFile> attachments;
}
