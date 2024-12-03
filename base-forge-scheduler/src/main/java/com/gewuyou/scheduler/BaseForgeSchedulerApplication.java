package com.gewuyou.scheduler;


import com.gewuyou.shared.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {
        "com.gewuyou.scheduler",
        "com.gewuyou.shared"
})
public class BaseForgeSchedulerApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeSchedulerApplication.class, args);
    }

}
