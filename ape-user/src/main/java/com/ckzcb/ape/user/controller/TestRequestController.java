package com.ckzcb.ape.user.controller;

import com.ckzcb.ape.user.entity.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestRequestController
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/27 16:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/request")
public class TestRequestController {
    /**
     * @RequestMapping 用于将HTTP请求映射到控制器方法，可定义路径、请求方法等属性。
     */
    @RequestMapping("/t1")
    public String t1() {
        return "t1";
    }

    @RequestMapping(value = "/t2", method = {RequestMethod.GET})
    public String t2() {
        return "t2";
    }

    @RequestMapping(value = "/t3", method = {RequestMethod.POST})
    public String t3() {
        return "t3";
    }

    /**
     * 参数传递
     * (1) 路径变量：@PathVariable
     */
    @RequestMapping("/t4/{id}")
    public String t4(HttpServletRequest request, @PathVariable("id") String id) {
        return "t4" + id + ":" + request.getRequestURL();
    }

    /**
     * (2) 查询参数：@RequestParam
     * 从URL的查询字符串中获取参数，如 /users?name=John。
     */
    @RequestMapping("/t5")
    public String t5(@RequestParam("name") String name) {
        return "t5" + name;
    }

    /**
     * 默认参数
     */
    @RequestMapping("/t6")
    public String t6(@RequestParam(value = "name", defaultValue = "John") String name,
                     @RequestParam(value = "age", defaultValue = "18") String age) {
        return "t6" + name + ":" + age;
    }

    /**
     * 表单数据直接绑定
     */
    @RequestMapping("/t7")
    public String t7(String name, String age) {
        return "t7" + name + ":" + age;
    }

    /**
     * (3) 绑定到对象  formdata格式
     * 将表单参数封装到一个对象中（推荐更清晰的代码结构）：
     */
    @RequestMapping("/t8")
    public String t8(UserDto userDto) {
        return "t8" + userDto.getName() + ":" + userDto.getAge();
    }

    /**
     * (3) 绑定到对象  json格式
     * 将表单参数封装到一个对象中（推荐更清晰的代码结构）：
     */
    @RequestMapping("/t9")
    public String t9(@RequestBody UserDto userDto) {
        return "t9" + userDto.getName() + ":" + userDto.getAge();
    }

    /**
     * 多参数
     */
    @RequestMapping("/t10/{name}/{id}")
    public String t10(@PathVariable("name") String name, @PathVariable("id") String id) {
        return "t10: " + name + ":" + id;
    }
}
