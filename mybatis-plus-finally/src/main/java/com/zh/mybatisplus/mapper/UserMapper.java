package com.zh.mybatisplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhouhui
 * @version 1.0
 * @description   修改的地方 将mybatis-plus的版本提升为3.5.4
 * @date 2026/1/12 15:32
 */

public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllByName(String name);

    IPage<User> selectPageByPage(@Param("page") Page<User> page, @Param("age") Integer age);
}
