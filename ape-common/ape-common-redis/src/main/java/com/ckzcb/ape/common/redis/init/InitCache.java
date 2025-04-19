package com.ckzcb.ape.common.redis.init;

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
    @Override
    public void run(String... args) throws Exception {
        System.out.println("init cache ... ");
    }
}
