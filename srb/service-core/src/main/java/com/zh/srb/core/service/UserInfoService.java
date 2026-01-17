package com.zh.srb.core.service;

import com.zh.srb.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.srb.core.pojo.vo.LoginVO;
import com.zh.srb.core.pojo.vo.RegisterVO;
import com.zh.srb.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author zhouhui
 * @since 2026-01-14
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO,String ip);
}
