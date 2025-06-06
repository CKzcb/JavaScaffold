package com.ckzcb.ape.user.controller;

import com.ckzcb.ape.common.zk.util.ZookeeperUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName TestZk
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/6/6 14:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/zk")
public class TestZk {
    private static final Logger logger = LoggerFactory.getLogger(TestZk.class);

    @Resource
    private ZookeeperUtil zookeeperUtil;

    @RequestMapping("/test")
    public String test() {
        try {
            zookeeperUtil.getDataAndWatch("/test", (path, type, oldData, data) -> {
                logger.info("zk: {}, type: {}", data.getPath(), type);
                logger.info("zk data: {}", data.getData() == null ? "" : new String(data.getData(), StandardCharsets.UTF_8));
                logger.info("zk olData: {}", oldData == null ? "" : new String(oldData.getData(), StandardCharsets.UTF_8));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "success";
    }
}
