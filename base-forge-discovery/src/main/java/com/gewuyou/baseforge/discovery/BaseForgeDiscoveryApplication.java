package com.gewuyou.baseforge.discovery;





import com.gewuyou.baseforge.core.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心启动类
 * @author gewuyou
 */
@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class BaseForgeDiscoveryApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeDiscoveryApplication.class, args);
    }
}
