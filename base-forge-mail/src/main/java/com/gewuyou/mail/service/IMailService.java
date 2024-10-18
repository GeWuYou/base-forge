package com.gewuyou.mail.service;

import com.gewuyou.common.dto.HTMLAttachmentsMailDTO;
import com.gewuyou.common.dto.HTMLMailDTO;
import com.gewuyou.common.dto.SimpleAttachmentsMailDTO;
import com.gewuyou.common.dto.SimpleMailDTO;

/**
 * 邮件服务接口
 *
 * @author gewuyou
 * @since 2024-10-15 22:14:05
 */
public interface IMailService {
    /**
     * 发送简单邮件
     *
     * @param simpleMailDTO 简单邮件DTO
     */
    void sendSimpleMail(SimpleMailDTO simpleMailDTO);


    /**
     * 发送HTML邮件
     *
     * @param htmlMailDTO HTML邮件DTO
     */
    void sendHtmlMail(HTMLMailDTO htmlMailDTO);

    /**
     * 发送简单带附件的邮件
     *
     * @param simpleAttachmentsMailDTO 简单带附件的邮件DTO
     */
    void sendSimpleWithAttachmentsMail(SimpleAttachmentsMailDTO simpleAttachmentsMailDTO);

    /**
     * 发送HTML带附件的邮件
     *
     * @param htmlAttachmentsMailDTO HTML带附件的邮件DTO
     */
    void sendHtmlWithAttachmentsMail(HTMLAttachmentsMailDTO htmlAttachmentsMailDTO);
}
