package com.ckzcb.ape.common.redis.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisHashUtils
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/19 17:11
 * @Version 1.0
 */
@Component
public class RedisHash {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public boolean hDel(String key, String hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey) > 0;
    }

    public boolean clean(String key) {
        return redisTemplate.opsForHash().delete(key) > 0;
    }

    public boolean hExists(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public void setEx(String key, String hashKey, Object value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, time, timeUnit);
    }


    public Object get(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Long hIncrBy(String key, String hashKey, Integer delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    public Double hIncrByFloat(String key, String hashKey, Float delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    public Integer hLen(String key) {
        return redisTemplate.opsForHash().size(key).intValue();
    }

    public List<Object> hMGet(String key, Set<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    public void hMSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }
}
