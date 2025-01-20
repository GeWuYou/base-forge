package com.gewuyou.baseforge.common.dto.mail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Map;

/**
 * html 邮件 DTO
 *
 * @author gewuyou
 * @since 2024-10-15 23:02:18
 */
@Schema(description = "html 邮件 DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HTMLMailDTO implements Serializable {
    /**
     * 收件人
     */
    @Schema(description = "收件人")
    private String to;
    /**
     * 主题
     */
    @Schema(description = "主题")
    private String subject;
    /**
     * 内容映射
     */
    @Schema(description = "内容映射")
    private Map<String, Object> contentMap;
    /**
     * 模板路径
     */
    @Schema(description = "模板路径")
    private String template;
    /**
     * 附件
     */
    @Schema(description = "附件")
    private MultipartFile file;
}
