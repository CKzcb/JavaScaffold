package com.ckzcb.ape.common.redis.utils;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisShareLock
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/10 16:54
 * @Version 1.0
 */
@Component
public class RedisDistributedLock {

    @Resource
    private RedisString redisString;

    public static Long TIMEOUT = 30L;

    /**
     * 尝试获取分布式锁
     *
     * @param key      锁的键，通常使用唯一标识符来确保锁的唯一性
     * @param value    锁的值，可以用于存储锁获取的时间戳等信息
     * @param time     锁的过期时间，以指定的时间单位计算
     * @param timeUnit 时间单位，用于指定过期时间的单位
     * @return 返回布尔值，表示是否成功获取锁
     */
    public boolean lock(String key, String value, long time, TimeUnit timeUnit) {
        if (StringUtils.isBlank(value) || StringUtils.isBlank(key)) {
            throw new RuntimeException("锁的值不能为空");
        }
        return redisString.setNx(key, value, time, timeUnit);
    }


    /**
     * 解锁方法，用于释放分布式锁
     *
     * @param key   锁的键，通常代表了要保护的资源
     * @param value 锁的值，用于比较以确保锁的释放是安全的
     * @return 总是返回true，表示解锁操作已尝试执行
     */
    public boolean unlock(String key, String value) {
        if (StringUtils.isBlank(value) || StringUtils.isBlank(key)) {
            return false;
        }
        // 获取当前锁的值
        Object currentValue = redisString.get(key);
        // 比较锁的值，如果匹配，则删除锁，释放资源
        if (value.equals(currentValue)) {
            redisString.del(key);
        }
        // 返回true，表示解锁操作已尝试执行
        return true;
    }

    /**
     * 使用自旋锁的方式尝试获取锁
     * 自旋锁会在一定时间内不断尝试获取锁，而不是直接返回失败，这样可以在高并发环境下提高系统的吞吐量
     *
     * @param key      锁的键，用于标识一个特定的锁
     * @param value    锁的值，通常用于存储锁的持有者的信息
     * @param time     锁的过期时间
     * @param timeUnit 锁的过期时间的时间单位
     * @return 如果成功获取锁，则返回true；否则，在指定的超时时间内未获取到锁，则返回false
     */
    public boolean spinLock(String key, String value, long time, TimeUnit timeUnit) {
        // 计算锁的超时时间，当前时间加上预设的超时时间
        long expireTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(TIMEOUT);
        // 在超时时间之前不断尝试获取锁
        while (System.currentTimeMillis() < expireTime) {
            // 尝试获取锁，如果成功则返回true
            if (lock(key, value, time, timeUnit)) {
                return true;
            }
            // 暂停一段时间后再次尝试获取锁，以避免高并发下的CPU竞争
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // 如果线程被中断，则抛出运行时异常
                throw new RuntimeException(e);
            }
        }
        // 如果超过超时时间仍未获取到锁，则返回false
        return false;
    }
}
