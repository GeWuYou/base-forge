package com.gewuyou.cache;


import com.gewuyou.shared.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BaseForgeCacheApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeCacheApplication.class, args);
    }

}
