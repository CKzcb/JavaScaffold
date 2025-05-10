package com.ckzcb.ape.common.redis.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName InitCache
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/19 17:14
 * @Version 1.0
 */
@Component
public class InitCache implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(InitCache.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化缓存");
    }
}
