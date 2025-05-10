package com.ckzcb.ape.user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserPo
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/17 22:39
 * @Version 1.0
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private int age;
}
