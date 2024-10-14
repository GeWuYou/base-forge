package com.gewuyou.shared.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson 配置
 *
 * @author gewuyou
 * @since 2024-10-09 09:49:08
 */
@Configuration
@Slf4j
public class RedissonConfig {

    @Value("${spring.data.redis.host}")
    private String redisAddress;
    @Value("${spring.data.redis.database}")
    private int redisDatabase;
    @Value("${spring.data.redis.port}")
    private String redisPort;
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://"+redisAddress+":"+redisPort)
                .setDatabase(redisDatabase);
        return Redisson.create(config);
    }
}
