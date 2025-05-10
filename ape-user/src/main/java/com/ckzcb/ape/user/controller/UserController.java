package com.ckzcb.ape.user.controller;

import com.ckzcb.ape.user.entity.dto.UserDto;
import com.ckzcb.ape.user.entity.req.UserReq;
import com.ckzcb.ape.user.service.ConditionService;
import com.ckzcb.ape.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/17 23:11
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private ConditionService conditionService;

    @PostMapping
    public Integer addUser(@RequestBody UserReq userReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq, userDto);
        conditionService.showTest();
        return userService.addUser(userDto);
    }


    @RequestMapping("/test")
    private String test(@RequestParam("id") String id) {
        userService.test(id);
        return "ok";
    }
}
