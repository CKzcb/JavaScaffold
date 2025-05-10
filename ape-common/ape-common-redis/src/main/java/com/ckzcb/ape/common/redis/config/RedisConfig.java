package com.ckzcb.ape.common.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/19 14:38
 * @Version 1.0
 */
@Configuration
public class RedisConfig {

    /**
     * 配置RedisTemplate以支持键值对序列化和反序列化
     * 此方法旨在为Redis操作提供一个模板对象，该对象已配置好连接工厂和序列化方式
     *
     * @param redisConnectionFactory Redis连接工厂，用于创建和管理Redis连接
     * @return RedisTemplate<String, Object> 配置好的Redis模板对象，用于执行Redis操作
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建RedisTemplate实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // 设置连接工厂，负责创建和管理Redis连接
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 配置键序列化方式为StringRedisSerializer，确保键以字符串形式存储和读取
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 配置哈希表键序列化方式为StringRedisSerializer，确保哈希表的键以字符串形式存储和读取
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // 配置字符串序列化方式为StringRedisSerializer，确保字符串类型的数据可以正确序列化和反序列化
        redisTemplate.setStringSerializer(new StringRedisSerializer());

        // 配置值序列化方式为Jackson2JsonRedisSerializer，确保对象类型的数据可以转换为JSON格式存储
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());

        // 配置哈希表值序列化方式为Jackson2JsonRedisSerializer，确保哈希表的值可以转换为JSON格式存储
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());

        // 返回配置好的RedisTemplate实例
        return redisTemplate;
    }

    /**
     * 创建一个Jackson2JsonRedisSerializer实例，用于Redis序列化和反序列化
     * 该方法配置了ObjectMapper以满足特定的序列化需求
     *
     * @return Jackson2JsonRedisSerializer<Object> 实例，用于Redis数据的序列化和反序列化
     */
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        // 创建ObjectMapper实例，用于JSON序列化和反序列化
        ObjectMapper objectMapper = new ObjectMapper();

        // 设置ObjectMapper的可见性，使得所有属性都可以被序列化和反序列化
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 设置ObjectMapper的序列化包含规则，仅包含非默认值的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);

        // 返回新的Jackson2JsonRedisSerializer实例，使用配置好的ObjectMapper
        return new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        return new RedisCacheManager(redisCacheWriter, RedisCacheConfiguration.defaultCacheConfig());
    }

}
