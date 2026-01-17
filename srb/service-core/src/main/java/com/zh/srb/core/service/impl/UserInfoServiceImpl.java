package com.zh.srb.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.srb.base.util.JwtUtils;
import com.zh.srb.common.exception.Assert;
import com.zh.srb.common.result.ResponseEnum;
import com.zh.srb.common.util.Md5;
import com.zh.srb.core.mapper.UserAccountMapper;
import com.zh.srb.core.mapper.UserLoginRecordMapper;
import com.zh.srb.core.pojo.entity.UserAccount;
import com.zh.srb.core.pojo.entity.UserInfo;
import com.zh.srb.core.mapper.UserInfoMapper;
import com.zh.srb.core.pojo.vo.LoginVO;
import com.zh.srb.core.pojo.vo.RegisterVO;
import com.zh.srb.core.pojo.vo.UserInfoVO;
import com.zh.srb.core.service.UserAccountService;
import com.zh.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zh.srb.core.pojo.entity.UserLoginRecord;
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

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserLoginRecordMapper userLoginRecordMapper;


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void register(RegisterVO registerVO) {

        //判断用户是否被注册
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", registerVO.getMobile());
        Integer count = baseMapper.selectCount(queryWrapper);
        //MOBILE_EXIST_ERROR(-207, "手机号已被注册"),
        Assert.isTrue(count==0, ResponseEnum.MOBILE_EXIST_ERROR);

        //插入用户基本信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserType(registerVO.getUserType());
        userInfo.setMobile(registerVO.getMobile());
        userInfo.setNickName(registerVO.getMobile());
        userInfo.setName(registerVO.getMobile());
        userInfo.setPassword(Md5.encrypt(registerVO.getPassword()));
        userInfo.setStatus(UserInfo.STATUS_NORMAL);

        //设置一张静态资源服务器上的头像图片
        userInfo.setHeadImg("https://zhouhui-srb.oss-cn-beijing.aliyuncs.com/avatar/07.jpg");
        baseMapper.insert(userInfo);

        //创建会员账户
        UserAccount userAccount=new UserAccount();
        userAccount.setUserId(userInfo.getId());
        userAccountMapper.insert(userAccount);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserInfoVO login(LoginVO loginVO, String ip) {

        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();

        //获取会员
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        queryWrapper.eq("user_type", userType);
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);

        //用户不存在
        //LOGIN_MOBILE_ERROR(-208, "用户不存在"),
        Assert.notNull(userInfo, ResponseEnum.LOGIN_MOBILE_ERROR);

        //校验密码
        //LOGIN_PASSWORD_ERROR(-209, "密码不正确"),
        Assert.equals(Md5.encrypt(password), userInfo.getPassword(), ResponseEnum.LOGIN_PASSWORD_ERROR);

        //用户是否被禁用
        //LOGIN_DISABLED_ERROR(-210, "用户已被禁用"),
        Assert.equals(userInfo.getStatus(), UserInfo.STATUS_NORMAL, ResponseEnum.LOGIN_LOKED_ERROR);

        //记录登录日志
        UserLoginRecord userLoginRecord = new UserLoginRecord();
        userLoginRecord.setUserId(userInfo.getId());
        userLoginRecord.setIp(ip);
        userLoginRecordMapper.insert(userLoginRecord);

        System.out.println("++++++++++++++++");
        //生成token
        String token = JwtUtils.createToken(userInfo.getId(), userInfo.getName());
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setToken(token);
        userInfoVO.setName(userInfo.getName());
        userInfoVO.setNickName(userInfo.getNickName());
        userInfoVO.setHeadImg(userInfo.getHeadImg());
        userInfoVO.setMobile(userInfo.getMobile());
        userInfoVO.setUserType(userType);

        return userInfoVO;
    }
}
