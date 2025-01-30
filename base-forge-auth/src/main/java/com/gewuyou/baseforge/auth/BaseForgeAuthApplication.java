package com.gewuyou.baseforge.auth;

import com.gewuyou.baseforge.entities.web.app.BaseForgeApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 统一认证服务启动类
 *
 * @author gewuyou
 */
@SpringBootApplication
public class BaseForgeAuthApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeAuthApplication.class, args);
    }

}
