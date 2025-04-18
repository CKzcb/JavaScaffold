package com.ckzcb.ape.user;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName UserApplication
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/17 15:59
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.ckzcb.ape")
@MapperScan("com.ckzcb.ape.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
