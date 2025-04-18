package com.ckzcb.ape.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckzcb.ape.user.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserPoMapper
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/17 22:40
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
