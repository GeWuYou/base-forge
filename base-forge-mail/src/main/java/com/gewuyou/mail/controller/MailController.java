package com.gewuyou.mail.controller;

import com.gewuyou.common.dto.HTMLAttachmentsMailDTO;
import com.gewuyou.common.dto.HTMLMailDTO;
import com.gewuyou.common.dto.SimpleAttachmentsMailDTO;
import com.gewuyou.common.dto.SimpleMailDTO;
import com.gewuyou.common.entity.Result;
import com.gewuyou.common.enums.ResponseInformation;
import com.gewuyou.mail.service.IMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮件控制器
 *
 * @author gewuyou
 * @since 2024-10-15 22:07:53
 */
@Tag(name = "邮件控制器", description = "邮件控制器")
@RestController
@RequestMapping("${base-forge.url.services.mail}")
public class MailController {
    private final IMailService mailService;

    @Autowired
    public MailController(IMailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 发送简单邮件
     *
     * @param simpleMailDTO 邮件DTO
     * @return 响应信息
     */
    @Operation(summary = "发送简单邮件", description = "发送简单邮件")
    @PostMapping("/send")
    public Result<String> sendSimpleMail(@RequestBody SimpleMailDTO simpleMailDTO) {
        mailService.sendSimpleMail(simpleMailDTO);
        return Result.success(ResponseInformation.MAIL_SEND_SUCCESS);
    }

    /**
     * 发送HTML邮件
     *
     * @param htmlMailDTO 邮件DTO
     * @return 响应信息
     */
    @Operation(summary = "发送HTML邮件", description = "发送HTML邮件")
    @PostMapping("/sendHtml")
    public Result<String> sendHtmlMail(@RequestBody HTMLMailDTO htmlMailDTO) {
        mailService.sendHtmlMail(htmlMailDTO);
        return Result.success(ResponseInformation.MAIL_SEND_SUCCESS);
    }

    /**
     * 发送带附件的邮件
     *
     * @param simpleWithAttachmentsMailDTO 邮件DTO
     * @return 响应信息
     */
    @Operation(summary = "发送带附件的邮件", description = "发送带附件的邮件")
    @PostMapping("/sendAttachment")
    public Result<String> sendSimpleWithAttachmentsMail(@RequestBody SimpleAttachmentsMailDTO simpleWithAttachmentsMailDTO) {
        mailService.sendSimpleWithAttachmentsMail(simpleWithAttachmentsMailDTO);
        return Result.success(ResponseInformation.MAIL_SEND_SUCCESS);
    }

    /**
     * 发送HTML带附件的邮件
     *
     * @param htmlWithAttachmentsMailDTO 邮件DTO
     * @return 响应信息
     */
    @Operation(summary = "发送HTML带附件的邮件", description = "发送HTML带附件的邮件")
    @PostMapping("/sendHtmlAttachment")
    public Result<String> sendHtmlWithAttachmentsMail(@RequestBody HTMLAttachmentsMailDTO htmlWithAttachmentsMailDTO) {
        mailService.sendHtmlWithAttachmentsMail(htmlWithAttachmentsMailDTO);
        return Result.success(ResponseInformation.MAIL_SEND_SUCCESS);
    }
}
