package com.actionman.sessionwithcache.config.cache;

import java.time.Duration;

import com.actionman.sessionwithcache.config.cache.properties.CacheProperties;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import lombok.RequiredArgsConstructor;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfig {
    
    private final CacheProperties prop;

    @Bean
    public RedisConnectionFactory cacheConnectionFactory() {
        return new LettuceConnectionFactory(prop.getHost(), prop.getPort());
    }

    @Bean
    public RedisCacheManager cacheManager(RedisCacheConfiguration defaultRedisCacheConfiguration) {
        // @formatter:off
        return RedisCacheManager.builder(cacheConnectionFactory())
                .cacheDefaults(defaultRedisCacheConfiguration)
                .build();
        // @formatter:on
    }

    @Bean
    RedisCacheConfiguration defaultRedisCacheConfiguration() {
        // @formatter:off
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60L))
                .disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        // @formatter:on
    }
}
