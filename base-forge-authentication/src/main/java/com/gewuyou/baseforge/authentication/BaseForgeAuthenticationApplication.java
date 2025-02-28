package com.gewuyou.baseforge.authentication;


import com.gewuyou.baseforge.core.app.BaseForgeApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 统一认证服务启动类
 *
 * @author gewuyou
 */
@SpringBootApplication
public class BaseForgeAuthenticationApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeAuthenticationApplication.class, args);
    }

}
