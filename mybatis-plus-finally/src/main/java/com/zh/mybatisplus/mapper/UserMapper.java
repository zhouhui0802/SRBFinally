package com.zh.mybatisplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.mybatisplus.entity.User;

import java.util.List;

/**
 * @author zhouhui
 * @version 1.0
 * @description   修改的地方 将mybatis-plus的版本提升为3.5.4
 * @date 2026/1/12 15:32
 */

public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllByName(String name);
}
