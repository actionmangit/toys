package com.actionman.sessionwithcache.config.session;

import com.actionman.sessionwithcache.config.session.properties.SessionProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import lombok.RequiredArgsConstructor;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 120)
@RequiredArgsConstructor
public class SessionConfig {

    private final SessionProperties prop;

    @Bean
    @SpringSessionRedisConnectionFactory
    public RedisConnectionFactory springSessionRedisConnectionFactory() {
        return new LettuceConnectionFactory(prop.getHost(), prop.getPort());
    }

    /**
     * Session으로 AWS elastic cache 사용시 해당 옵션으로 설정해야 함.
     * @return Configure redis action.
     */
    @Bean
    ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
}
