package com.ckzcb.ape.common.redis.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisList
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/19 17:57
 * @Version 1.0
 */
@Component
public class RedisList {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void lPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public void lPushAll(String key, Object... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    public void lPushAll(String key, Long time, Object... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
        redisTemplate.expire(key, Duration.ofMillis(time));
    }

    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public Long rPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Long rPushAll(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    public Long rPushAll(String key, Long time, Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        redisTemplate.expire(key, Duration.ofMillis(time));
        return count;
    }

    public Object lPop(String key, long time, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key, time, unit);
    }

    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public void lSet(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    public void lTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    public void lRem(String key, long count, Object value) {
        redisTemplate.opsForList().remove(key, count, value);
    }
}
