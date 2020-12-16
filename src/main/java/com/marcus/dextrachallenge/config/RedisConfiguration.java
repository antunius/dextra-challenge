package com.marcus.dextrachallenge.config;

import com.marcus.dextrachallenge.house.House;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    @Bean
    public static RedisTemplate<String, House> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, House> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
