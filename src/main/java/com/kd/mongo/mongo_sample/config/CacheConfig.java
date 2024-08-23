package com.kd.mongo.mongo_sample.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CacheConfig {

    @Bean
    public Caffeine<Object, Object> caffeine(){
        return Caffeine.newBuilder().expireAfterWrite(Duration.ofDays(2))
                .maximumSize(500)
                .initialCapacity(100);
    }

    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("player");
        cacheManager.setCaffeine(caffeine());
        return cacheManager;
    }
}
