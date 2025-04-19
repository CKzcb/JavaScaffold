package com.ckzcb.ape.common.redis.init;

import org.springframework.stereotype.Component;

/**
 * @ClassName AbstractCache
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/19 17:12
 * @Version 1.0
 */
@Component
public class AbstractCache {
    public void init() {
        System.out.println("初始化缓存");
    }

    public <T> T get(String key) {
        return null;
    }

    public boolean del(String key) {
        return false;
    }

    public boolean clean(String key) {
        return false;
    }
}
