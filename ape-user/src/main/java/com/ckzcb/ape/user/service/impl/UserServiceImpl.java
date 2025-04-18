package com.ckzcb.ape.user.service.impl;

import com.ckzcb.ape.user.entity.dto.UserDto;
import com.ckzcb.ape.user.entity.po.User;
import com.ckzcb.ape.user.mapper.UserMapper;
import com.ckzcb.ape.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/17 23:06
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userPoMapper;

    @Override
    public int addUser(UserDto userDto) {
        User userPo = new User();
        BeanUtils.copyProperties(userDto, userPo);
        return userPoMapper.insert(userPo);
    }
}
