package com.ckzcb.ape.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPollConfig
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/6/13 16:26
 * @Version 1.0
 */
@Configuration
public class ThreadPollConfig {
    @Bean(name = "threadPoolExecutor")
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(
                10,
                20,
                1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(50),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }
}
