package com.gewuyou.mail.config;

import com.gewuyou.common.constant.MessageQueueConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit MQ 配置
 *
 * @author gewuyou
 * @since 2024-10-18 11:15:35
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 创建一个简单邮件队列。
     *
     * @return 返回一个新的Queue实例，用于处理简单邮件。
     */
    @Bean
    public Queue simpleMailQueue() {
        return new Queue(MessageQueueConstant.TOPIC_SIMPLE_MAIL_QUEUE);
    }

    /**
     * 创建一个简单HTML邮件队列。
     *
     * @return 返回一个新的Queue实例，用于处理简单HTML邮件。
     */
    @Bean
    public Queue simpleHTMLMailQueue() {
        return new Queue(MessageQueueConstant.TOPIC_SIMPLE_HTML_MAIL_QUEUE);
    }

    /**
     * 创建一个简单附件邮件队列。
     *
     * @return 返回一个新的Queue实例，用于处理简单附件邮件。
     */
    @Bean
    public Queue simpleAttachmentMailQueue() {
        return new Queue(MessageQueueConstant.TOPIC_SIMPLE_ATTACHMENT_MAIL_QUEUE);
    }

    /**
     * 创建一个简单附件HTML邮件队列。
     *
     * @return 返回一个新的Queue实例，用于处理简单附件HTML邮件。
     */
    @Bean
    public Queue simpleAttachmentHTMLMailQueue() {
        return new Queue(MessageQueueConstant.TOPIC_SIMPLE_ATTACHMENT_HTML_MAIL_QUEUE);
    }

    /**
     * 创建一个主题交换器。
     *
     * @return 返回一个新的TopicExchange实例，用于消息的主题发布与订阅。
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MessageQueueConstant.TOPIC_MAIL_EXCHANGE);
    }

    /**
     * 将简单邮件队列绑定到主题交换器。
     *
     * @param simpleMailQueue 简单邮件队列实例。
     * @param topicExchange   主题交换器实例。
     * @return 返回一个新的Binding实例，用于连接简单邮件队列和主题交换器。
     */
    @Bean
    public Binding bindingSimpleMail(Queue simpleMailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(simpleMailQueue).to(topicExchange).with("mail.simple");
    }

    /**
     * 将简单HTML邮件队列绑定到主题交换器。
     *
     * @param simpleHTMLMailQueue 简单HTML邮件队列实例。
     * @param topicExchange       主题交换器实例。
     * @return 返回一个新的Binding实例，用于连接简单HTML邮件队列和主题交换器。
     */
    @Bean
    public Binding bindingSimpleHTMLMail(Queue simpleHTMLMailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(simpleHTMLMailQueue).to(topicExchange).with("mail.simple.html");
    }

    /**
     * 将简单附件邮件队列绑定到主题交换器。
     *
     * @param simpleAttachmentMailQueue 简单附件邮件队列实例。
     * @param topicExchange             主题交换器实例。
     * @return 返回一个新的Binding实例，用于连接简单附件邮件队列和主题交换器。
     */
    @Bean
    public Binding bindingSimpleAttachmentMail(Queue simpleAttachmentMailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(simpleAttachmentMailQueue).to(topicExchange).with("mail.simple.attachment");
    }

    /**
     * 将简单附件HTML邮件队列绑定到主题交换器。
     *
     * @param simpleAttachmentHTMLMailQueue 简单附件HTML邮件队列实例。
     * @param topicExchange                 主题交换器实例。
     * @return 返回一个新的Binding实例，用于连接简单附件HTML邮件队列和主题交换器。
     */
    @Bean
    public Binding bindingSimpleAttachmentHTMLMail(Queue simpleAttachmentHTMLMailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(simpleAttachmentHTMLMailQueue).to(topicExchange).with("mail.simple.html.attachment");
    }

}
