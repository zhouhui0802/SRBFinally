package com.zh.srb.core.service.impl;

import com.zh.srb.core.pojo.entity.UserAccount;
import com.zh.srb.core.mapper.UserAccountMapper;
import com.zh.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2026-01-14
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
