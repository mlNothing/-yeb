package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author mlNothing
 * @date 2021/12/3 14:07
 */
@Configuration
public class RedisConfig {
    @Bean
    public  RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnection){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        为String类型的key设置序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        为String类型的value设置序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        为hash类型的key设置序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        为hash类型的value设置序列化
        redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnection);
        return  redisTemplate;
    }
}
