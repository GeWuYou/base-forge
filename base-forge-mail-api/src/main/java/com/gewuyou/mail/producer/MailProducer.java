package com.gewuyou.mail.producer;

import com.gewuyou.common.constant.MessageQueueConstant;
import com.gewuyou.common.dto.HTMLAttachmentsMailDTO;
import com.gewuyou.common.dto.HTMLMailDTO;
import com.gewuyou.common.dto.SimpleAttachmentsMailDTO;
import com.gewuyou.common.dto.SimpleMailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 邮件生产者
 *
 * @author gewuyou
 * @since 2024-10-18 16:44:29
 */
@Service
public class MailProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送简单邮件
     *
     * @param simpleMailDTO 邮件信息
     */
    public void sendSimpleMail(SimpleMailDTO simpleMailDTO) {
        rabbitTemplate
                .convertAndSend(MessageQueueConstant.TOPIC_MAIL_EXCHANGE,
                        MessageQueueConstant.TOPIC_SIMPLE_MAIL_QUEUE, simpleMailDTO);
    }

    /**
     * 发送HTML邮件
     *
     * @param htmlMailDTO 邮件信息
     */
    public void sendHtmlMail(HTMLMailDTO htmlMailDTO) {
        rabbitTemplate
                .convertAndSend(MessageQueueConstant.TOPIC_MAIL_EXCHANGE,
                        MessageQueueConstant.TOPIC_SIMPLE_HTML_MAIL_QUEUE, htmlMailDTO);
    }

    /**
     * 发送带附件的邮件
     *
     * @param simpleAttachmentsMailDTO 邮件信息
     */
    public void sendSimpleAttachmentMail(SimpleAttachmentsMailDTO simpleAttachmentsMailDTO) {
        rabbitTemplate
                .convertAndSend(MessageQueueConstant.TOPIC_MAIL_EXCHANGE,
                        MessageQueueConstant.TOPIC_SIMPLE_ATTACHMENT_MAIL_QUEUE, simpleAttachmentsMailDTO);
    }

    /**
     * 发送HTML带附件的邮件
     * @param htmlAttachmentsMailDTO 邮件信息
     */
    public void sendHtmlAttachmentMail(HTMLAttachmentsMailDTO htmlAttachmentsMailDTO) {
        rabbitTemplate
                .convertAndSend(MessageQueueConstant.TOPIC_SIMPLE_ATTACHMENT_HTML_MAIL_QUEUE, htmlAttachmentsMailDTO);
    }
}
