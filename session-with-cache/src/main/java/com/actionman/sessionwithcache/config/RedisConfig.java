package com.actionman.sessionwithcache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    /**
     * spring-boot-starter-data-redis를 사용하게 되면 
     * connection fatory가 반드시 필요한데
     * session, cache를 default connection fatory 없이 사용하게 되면
     * 에러가 발생하게 되므르 사용하지 않더라도 
     * default connection factory를 생성하여 primay 어노테이션을 붙여준다.
     * 
     * @return default connection factory
     */
    @Bean
    @Primary
    public RedisConnectionFactory defaultConnectionFactory() {
        return new LettuceConnectionFactory();
    }
}
