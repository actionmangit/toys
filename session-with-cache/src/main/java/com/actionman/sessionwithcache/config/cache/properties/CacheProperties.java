package com.actionman.sessionwithcache.config.cache.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("sample.cache.redis")
@Getter
@Setter
public class CacheProperties {
    
    private String host;
    private Integer port;
}
