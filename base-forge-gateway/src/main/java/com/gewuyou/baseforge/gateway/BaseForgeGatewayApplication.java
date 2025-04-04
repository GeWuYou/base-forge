package com.gewuyou.baseforge.gateway;


import com.gewuyou.baseforge.core.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 网关启动类
 * @author gewuyou
 */
@SpringBootApplication
@Slf4j
public class BaseForgeGatewayApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeGatewayApplication.class, args);
    }

}
