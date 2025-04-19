package com.ckzcb.ape.user.service.impl;

import com.ckzcb.ape.common.redis.utils.RedisList;
import com.ckzcb.ape.user.entity.dto.UserDto;
import com.ckzcb.ape.user.entity.po.User;
import com.ckzcb.ape.user.mapper.UserMapper;
import com.ckzcb.ape.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    @Resource
    private RedisList redisList;

    @Override
    public int addUser(UserDto userDto) {
        User userPo = new User();
        BeanUtils.copyProperties(userDto, userPo);
        return userPoMapper.insert(userPo);
    }

    public void test() {
        User userPo = new User();
        userPo.setAge(1);
        userPo.setName("test");
        redisList.rPush("zzzz", userPo);
        System.out.println(redisList.lPop("zzzz", 1000, TimeUnit.MILLISECONDS));
        System.out.println(System.currentTimeMillis());
        System.out.println(redisList.lPop("zzzz", 10000, TimeUnit.MILLISECONDS));
        System.out.println(System.currentTimeMillis());
    }
}
