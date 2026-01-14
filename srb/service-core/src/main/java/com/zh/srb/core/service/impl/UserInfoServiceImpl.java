package com.zh.srb.core.service.impl;

import com.zh.srb.core.pojo.entity.UserInfo;
import com.zh.srb.core.mapper.UserInfoMapper;
import com.zh.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2026-01-14
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
