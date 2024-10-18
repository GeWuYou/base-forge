package com.gewuyou.mail.config.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 邮件属性
 *
 * @author gewuyou
 * @since 2024-10-15 22:28:21
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String defaultEncoding;
    private String protocol;
    private Properties properties;
}
