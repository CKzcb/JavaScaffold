package com.ckzcb.ape.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping("/test")
    public String test(@RequestParam String id) {
        System.out.println(">>>>>>>>>>>>>>>>>" + id);
        return "test";
    }
}
