package com.gewuyou.mail.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);


    /**
     * 发送HTML邮件
     *
     * @param to         收件人
     * @param subject    主题
     * @param contentMap 内容
     * @param template   模板
     */
    void sendHtmlMail(String to, String subject, Map<String, Object> contentMap, String template);

    /**
     * 发送简单带附件的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param attachments    附件
     */
    void sendSimpleWithAttachmentsMail(String to, String subject, String content, List<MultipartFile> attachments);

    /**
     * 发送HTML带附件的邮件
     *
     * @param to         收件人
     * @param subject    主题
     * @param contentMap 内容
     * @param template   模板
     * @param attachments    附件
     */
    void sendHtmlWithAttachmentsMail(String to, String subject, Map<String, Object> contentMap, String template, List<MultipartFile> attachments);
}
