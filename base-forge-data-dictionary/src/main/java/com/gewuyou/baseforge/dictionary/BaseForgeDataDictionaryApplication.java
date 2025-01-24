package com.gewuyou.baseforge.dictionary;

import com.gewuyou.baseforge.entities.web.app.BaseForgeApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 字典服务启动类
 *
 * @author gewuyou
 */
@SpringBootApplication
@EnableCaching
public class BaseForgeDataDictionaryApplication extends BaseForgeApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseForgeDataDictionaryApplication.class, args);
    }

}
