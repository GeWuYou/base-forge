package com.gewuyou.search;



import com.gewuyou.web.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 搜索服务启动类
 *
 * @author gewuyou
 */
@SpringBootApplication
@Slf4j
public class BaseForgeSearchApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeSearchApplication.class, args);
    }

}
