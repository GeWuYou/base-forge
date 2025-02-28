package com.gewuyou.file;


import com.gewuyou.web.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文件服务启动类
 *
 * @author gewuyou
 */
@SpringBootApplication
@Slf4j
public class BaseForgeFileApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeFileApplication.class, args);
    }

}
