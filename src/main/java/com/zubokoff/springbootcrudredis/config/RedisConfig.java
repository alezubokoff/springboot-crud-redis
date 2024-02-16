package com.zubokoff.springbootcrudredis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RedisConfig {

    @Bean
    RedisClusterConfiguration redisClusterConfiguration(@Value("${spring.redis.nodes}") String[] nodes,
                                                        @Value("${spring.redis.max-redirects}") Integer maxRedirects) {
        List<String> clusterNodes = Arrays.asList(nodes);
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterNodes);
        redisClusterConfiguration.setMaxRedirects(maxRedirects);
        return redisClusterConfiguration;
    }

    @Bean
    RedisConnectionFactory redisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration) {
        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder().build();
        return new LettuceConnectionFactory(redisClusterConfiguration, clientConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
