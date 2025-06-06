package com.ckzcb.ape.common.zk.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @ClassName ZookeeperConfig
 * @Description connect-string: 127.0.0.1:2181
 * session-timeout: 5000
 * connection-timeout: 5000
 * base-sleep-time: 1000
 * max-retries: 3
 * @Author 蛋白质先生
 * @Date 2025/6/6 10:08
 * @Version 1.0
 */
@Configuration
public class ZookeeperConfig {


    @Value("${zookeeper.connect-string}")
    private String connectString;

    @Value("${zookeeper.session-timeout}")
    private int sessionTimeout;

    @Value("${zookeeper.connection-timeout}")
    private int connectionTimeout;

    @Value("${zookeeper.base-sleep-time}")
    private int baseSleepTime;

    @Value("${zookeeper.max-retries}")
    private int maxRetries;

    @Bean
    public ZooKeeper getZooKeeper() {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return zooKeeper;
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework getCuratorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTime, maxRetries);
        return CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeout)
                .connectionTimeoutMs(connectionTimeout)
                .retryPolicy(retryPolicy)
                .build();
    }

}
