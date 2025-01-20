package com.gewuyou.baseforge.common.constant;

/**
 * 消息队列常量
 *
 * @author gewuyou
 * @since 2024-10-18 15:46:55
 */
public class MessageQueueConstant {
    // region RabbitMQ常量
    public static final String TOPIC_SIMPLE_MAIL_QUEUE = "mail.simpleMailQueue";
    public static final String TOPIC_SIMPLE_ATTACHMENT_MAIL_QUEUE = "mail.simpleAttachmentMailQueue";
    public static final String TOPIC_SIMPLE_HTML_MAIL_QUEUE = "mail.simpleHTMLMailQueue";
    public static final String TOPIC_SIMPLE_ATTACHMENT_HTML_MAIL_QUEUE = "mail.simpleAttachmentHTMLMailQueue";
    public static final String TOPIC_MAIL_EXCHANGE = "topicMailExchange";
    private MessageQueueConstant() {
    }
    // endregion
}
