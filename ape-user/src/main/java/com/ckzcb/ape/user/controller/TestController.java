package com.ckzcb.ape.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/17 22:15
 * @Version 1.0
 */
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/test")
    public String test() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>.");
        for (int i = 0; i < 10; i++) {
            log.info("测试日志填充: {}", i);
        }
        return "test";
    }

    @RequestMapping("/test2")
    public String test2(@RequestBody String name) {
        return "hello " + name;
    }
}
