package com.gewuyou.admin;


import com.gewuyou.web.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台管理系统启动类
 *
 * @author gewuyou
 */
@SpringBootApplication
@Slf4j
public class BaseForgeAdminApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeAdminApplication.class, args);
    }

}
