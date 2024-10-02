package com.gewuyou.auth;

import com.gewuyou.common.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BaseForgeAuthApplication extends BaseForgeApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(BaseForgeAuthApplication.class, args);
	}

}
