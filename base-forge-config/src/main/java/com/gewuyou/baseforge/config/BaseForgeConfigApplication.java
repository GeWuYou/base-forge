package com.gewuyou.baseforge.config;




import com.gewuyou.baseforge.core.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心启动类
 * @author gewuyou
 */
@SpringBootApplication
@EnableConfigServer
@Slf4j
public class BaseForgeConfigApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeConfigApplication.class, args);
    }
}
