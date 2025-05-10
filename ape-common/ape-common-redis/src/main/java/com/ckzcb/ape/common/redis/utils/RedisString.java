package com.ckzcb.ape.common.redis.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisStringUtils
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/19 17:11
 * @Version 1.0
 */
@Component
public class RedisString {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Boolean setNx(String key, Object value, long time, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    public void setEx(String key, Object value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public Integer desc(String key) {
        try {
            return redisTemplate.opsForValue().decrement(key).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer incr(String key) {
        try {
            return redisTemplate.opsForValue().increment(key).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer descBy(String key, Integer delta) {
        try {
            return redisTemplate.opsForValue().decrement(key, delta).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer incrBy(String key, Integer delta) {
        try {
            return redisTemplate.opsForValue().increment(key, delta).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public Double incrByFloat(String key, Float delta) {
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public Object getDel(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }

    public Object getEx(String key, long time, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().getAndExpire(key, time, timeUnit);
    }

    public Object getSet(String key, Object value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    public List<Object> mGet(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public void mSet(Map<String, Object> m) {
        redisTemplate.opsForValue().multiSet(m);
    }

    public void mSetNx(Map<String, Object> m) {
        redisTemplate.opsForValue().multiSetIfAbsent(m);
    }

    public void setPex(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
    }

}
