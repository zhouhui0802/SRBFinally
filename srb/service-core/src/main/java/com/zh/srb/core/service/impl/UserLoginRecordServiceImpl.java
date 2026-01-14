package com.zh.srb.core.service.impl;

import com.zh.srb.core.pojo.entity.UserLoginRecord;
import com.zh.srb.core.mapper.UserLoginRecordMapper;
import com.zh.srb.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2026-01-14
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
