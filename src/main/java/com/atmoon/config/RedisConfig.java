package com.atmoon.config;

import com.atmoon.pojo.Girl;
import com.atmoon.utils.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//redis配置加上好像没什么用，，
//@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return  new JedisConnectionFactory();
    }

    public RedisTemplate<String, Girl> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Girl> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

}
