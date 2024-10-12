package com.gewuyou.message;


import com.gewuyou.shared.app.BaseForgeApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BaseForgeMessageApplication extends BaseForgeApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(BaseForgeMessageApplication.class, args);
	}

}
