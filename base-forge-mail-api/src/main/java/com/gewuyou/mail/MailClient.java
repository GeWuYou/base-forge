package com.gewuyou.mail;

import com.gewuyou.common.dto.HTMLAttachmentsMailDTO;
import com.gewuyou.common.dto.HTMLMailDTO;
import com.gewuyou.common.dto.SimpleAttachmentsMailDTO;
import com.gewuyou.common.dto.SimpleMailDTO;
import com.gewuyou.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 消息客户端
 *
 * @author gewuyou
 * @since 2024-10-15 20:07:14
 */
@FeignClient(name = "base-forge-message", url = "${base-forge.url.mail}${base-forge.services.mail}")
public interface MailClient {
    /**
     * 发送简单邮件
     *
     * @param simpleMailDTO 邮件DTO
     * @return 响应信息
     */
    @PostMapping("/send")
    Result<String> sendSimpleMail(@RequestBody SimpleMailDTO simpleMailDTO);

    /**
     * 发送HTML邮件
     *
     * @param htmlMailDTO 邮件DTO
     * @return 响应信息
     */
    @PostMapping("/sendHtml")
    Result<String> sendHtmlMail(@RequestBody HTMLMailDTO htmlMailDTO);

    /**
     * 发送带附件的邮件
     *
     * @param simpleWithAttachmentsMailDTO 邮件DTO
     * @return 响应信息
     */
    @PostMapping("/sendAttachment")
    Result<String> sendSimpleWithAttachmentsMail(@RequestBody SimpleAttachmentsMailDTO simpleWithAttachmentsMailDTO);

    /**
     * 发送HTML带附件的邮件
     *
     * @param htmlWithAttachmentsMailDTO 邮件DTO
     * @return 响应信息
     */
    @PostMapping("/sendHtmlAttachment")
    Result<String> sendHtmlWithAttachmentsMail(@RequestBody HTMLAttachmentsMailDTO htmlWithAttachmentsMailDTO);
}
