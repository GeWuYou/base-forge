package com.gewuyou.baseforge.authorization;

import com.gewuyou.baseforge.core.app.BaseForgeApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 授权服务启动类
 * @author gewuyou
 */
@SpringBootApplication
public class BaseForgeAuthorizationApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeAuthorizationApplication.class, args);
    }

}
