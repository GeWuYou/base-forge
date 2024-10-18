package com.gewuyou.mail.service.impl;

import com.gewuyou.common.enums.ResponseInformation;
import com.gewuyou.common.exception.EmailException;
import com.gewuyou.common.exception.GlobalException;
import com.gewuyou.mail.service.IMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 邮件服务实现
 *
 * @author gewuyou
 * @since 2024-10-15 22:22:34
 */
@Service
@Slf4j
public class MailServiceImpl implements IMailService {

    /**
     * 邮件发送器
     */
    private final JavaMailSender javaMailSender;

    /**
     * 模板引擎
     */
    private final TemplateEngine templateEngine;
    /**
     * 发送邮箱账号
     */
    @Value("${spring.mail.username}")
    private String emailAccount;
    /**
     * 发送人名称
     */
    @Value("${spring.mail.properties.from}")
    private String emailFrom;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * 发送简单邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        try {
            // 创建MimeMessage对象
            MimeMessage message = javaMailSender.createMimeMessage();
            // 创建MemeMessageHelper对象 可选：new MimeMessageHelper(message,true) 携带附件
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            // 设定发送人
            messageHelper.setFrom(emailAccount, emailFrom);
            // 设定发送对象
            messageHelper.setTo(to);
            // 设定邮件主题
            messageHelper.setSubject(subject);
            // 设定邮件内容
            messageHelper.setText(content);
            // 发送邮件
            javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("邮件发送失败", e);
            throw new EmailException(ResponseInformation.SEND_EMAIL_FAILED);
        }
    }


    @Override
    public void sendHtmlMail(String to, String subject, Map<String, Object> contentMap, String template) {
        try {
            // 创建MimeMessage对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            // 创建MemeMessageHelper对象 可选：new MimeMessageHelper(mimeMessage,true) 携带附件
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            Context context = new Context();
            context.setVariables(contentMap);
            // 邮件模板
            String process = templateEngine.process(template, context);
            // 设定发送人
            messageHelper.setFrom(emailAccount, emailFrom);
            // 设定发送对象
            messageHelper.setTo(to);
            // 设定邮件主题
            messageHelper.setSubject(subject);
            // 设定邮件内容
            messageHelper.setText(process, true);
            // 发送邮件
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("邮件发送失败", e);
            throw new GlobalException(ResponseInformation.SEND_EMAIL_FAILED);
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param attachments 附件集合
     */
    @Override
    public void sendSimpleWithAttachmentsMail(String to, String subject, String content, List<MultipartFile> attachments) {
        try {
            // 创建MimeMessage对象
            MimeMessage message = javaMailSender.createMimeMessage();
            // 创建MemeMessageHelper对象 可选：new MimeMessageHelper(message,true) 携带附件
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            // 设定发送人
            messageHelper.setFrom(emailAccount, emailFrom);
            // 设定发送对象
            messageHelper.setTo(to);
            // 设定邮件主题
            messageHelper.setSubject(subject);
            // 设定邮件内容
            messageHelper.setText(content);
            // 附件
            if (!CollectionUtils.isEmpty(attachments)) {
                attachments.forEach(attachment -> {
                    if (Objects.nonNull(attachment) && !attachment.isEmpty() && Objects.nonNull(attachment.getOriginalFilename())) {
                        try {
                            messageHelper.addAttachment(attachment.getOriginalFilename(), attachment);
                        } catch (MessagingException e) {
                            log.error("邮件附件添加失败", e);
                            throw new EmailException(ResponseInformation.ADD_ATTACHMENT_FAILED);
                        }
                    }
                });
            }
            // 发送邮件
            javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("邮件发送失败", e);
            throw new EmailException(ResponseInformation.SEND_EMAIL_FAILED);
        }
    }

    /**
     * 发送HTML带附件的邮件
     *
     * @param to         收件人
     * @param subject    主题
     * @param contentMap 内容
     * @param template   模板
     * @param attachments 附件集合
     */
    @Override
    public void sendHtmlWithAttachmentsMail(String to, String subject, Map<String, Object> contentMap, String template,  List<MultipartFile> attachments) {
        try {
            // 创建MimeMessage对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            // 创建MemeMessageHelper对象 可选：new MimeMessageHelper(mimeMessage,true) 携带附件
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            Context context = new Context();
            context.setVariables(contentMap);
            // 邮件模板
            String process = templateEngine.process(template, context);
            // 设定发送人
            messageHelper.setFrom(emailAccount, emailFrom);
            // 设定发送对象
            messageHelper.setTo(to);
            // 设定邮件主题
            messageHelper.setSubject(subject);
            // 设定邮件内容
            messageHelper.setText(process, true);
            // 附件
            if (!CollectionUtils.isEmpty(attachments)) {
                attachments.forEach(attachment -> {
                    if (Objects.nonNull(attachment) && !attachment.isEmpty() && Objects.nonNull(attachment.getOriginalFilename())) {
                        try {
                            messageHelper.addAttachment(attachment.getOriginalFilename(), attachment);
                        } catch (MessagingException e) {
                            log.error("邮件附件添加失败", e);
                            throw new EmailException(ResponseInformation.ADD_ATTACHMENT_FAILED);
                        }
                    }
                });
            }
            // 发送邮件
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("邮件发送失败", e);
            throw new GlobalException(ResponseInformation.SEND_EMAIL_FAILED);
        }
    }
}
