package com.gewuyou.mail.consumer;

import com.gewuyou.common.constant.MessageQueueConstant;
import com.gewuyou.common.dto.HTMLAttachmentsMailDTO;
import com.gewuyou.common.dto.HTMLMailDTO;
import com.gewuyou.common.dto.SimpleAttachmentsMailDTO;
import com.gewuyou.common.dto.SimpleMailDTO;
import com.gewuyou.mail.service.IMailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 邮件消费者
 *
 * @author gewuyou
 * @since 2024-10-18 15:38:36
 */
@Component
public class MailConsumer {
    private final IMailService mailService;

    @Autowired
    public MailConsumer(IMailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 处理简单邮件的消息。
     *
     * @param simpleMailDTO 包含简单邮件信息的DTO对象。
     */
    @RabbitListener(queues = MessageQueueConstant.TOPIC_SIMPLE_MAIL_QUEUE)
    public void handleSimpleMail(SimpleMailDTO simpleMailDTO) {
        mailService.sendSimpleMail(simpleMailDTO);
    }

    /**
     * 处理简单HTML邮件的消息。
     *
     * @param htmlMailDTO 包含简单HTML邮件信息的DTO对象。
     */
    @RabbitListener(queues = MessageQueueConstant.TOPIC_SIMPLE_HTML_MAIL_QUEUE)
    public void handleHtmlMail(HTMLMailDTO htmlMailDTO) {
        mailService.sendHtmlMail(htmlMailDTO);
    }

    /**
     * 处理带附件的简单邮件的消息。
     *
     * @param simpleAttachmentsMailDTO 包含带附件简单邮件信息的DTO对象。
     */
    @RabbitListener(queues = MessageQueueConstant.TOPIC_SIMPLE_ATTACHMENT_MAIL_QUEUE)
    public void handleSimpleAttachmentMail(SimpleAttachmentsMailDTO simpleAttachmentsMailDTO) {
        mailService.sendSimpleWithAttachmentsMail(simpleAttachmentsMailDTO);
    }

    /**
     * 处理带HTML附件的邮件的消息。
     *
     * @param htmlAttachmentsMailDTO 包含带HTML附件邮件信息的DTO对象。
     */
    @RabbitListener(queues = MessageQueueConstant.TOPIC_SIMPLE_ATTACHMENT_HTML_MAIL_QUEUE)
    public void handleHtmlAttachmentMail(HTMLAttachmentsMailDTO htmlAttachmentsMailDTO) {
        mailService.sendHtmlWithAttachmentsMail(htmlAttachmentsMailDTO);
    }
}
