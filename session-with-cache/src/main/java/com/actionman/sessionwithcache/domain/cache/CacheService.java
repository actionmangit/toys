package com.actionman.sessionwithcache.domain.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    
    @Cacheable(value = "testCache", key = "#key")
    public String getCache(String key) {
        return "cacheTest";
    }
}
