package com.zh.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.mybatisplus.entity.User;
import com.zh.mybatisplus.mapper.UserMapper;
import com.zh.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> listAllByName(String name) {
        return baseMapper.selectAllByName("Helen0");
    }
}
