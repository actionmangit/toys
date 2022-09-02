package com.actionman.sessionwithcache.config.session.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("sample.session.redis")
@Setter
@Getter
public class SessionProperties {
    
    private String host;
    private Integer port;
}
