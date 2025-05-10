package com.ckzcb.ape.user.test;

import com.ckzcb.ape.common.redis.utils.RedisDistributedLock;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestRedisDistributedLock
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/10 17:28
 * @Version 1.0
 */
@Component
public class TestRedisDistributedLock implements CommandLineRunner {
    @Resource
    private RedisDistributedLock redisDistributedLock;

    @Override
    public void run(String... args) throws Exception {
    }
}
