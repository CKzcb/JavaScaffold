package com.ckzcb.ape.user.service;

import com.ckzcb.ape.user.entity.dto.UserDto;
import com.ckzcb.ape.user.entity.po.User;

/**
 * @ClassName UserService
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/17 23:06
 * @Version 1.0
 */
public interface UserService {
    int addUser(UserDto userDto);

    User test(String id);
}
