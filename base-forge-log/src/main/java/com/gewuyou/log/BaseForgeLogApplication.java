package com.gewuyou.log;


import com.gewuyou.web.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 日志服务启动类
 *
 * @author gewuyou
 */
@SpringBootApplication
@Slf4j
public class BaseForgeLogApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeLogApplication.class, args);
    }

}
