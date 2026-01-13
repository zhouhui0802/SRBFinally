package com.zh.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.mybatisplus.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> listAllByName(String name);
}
