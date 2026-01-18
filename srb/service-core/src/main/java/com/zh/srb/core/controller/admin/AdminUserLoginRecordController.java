package com.zh.srb.core.controller.admin;

import com.zh.srb.common.result.R;
import com.zh.srb.core.pojo.entity.UserLoginRecord;
import com.zh.srb.core.service.UserLoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "会员登录日志接口")
@RestController
@RequestMapping("/admin/core/userLoginRecord")
@Slf4j
//@CrossOrigin
public class AdminUserLoginRecordController {

    @Autowired
    private UserLoginRecordService userLoginRecordService;

    @ApiOperation("会员登录日志列表")
    @GetMapping("/listTop50/{userId}")
    public R listTop50(
            @ApiParam(value="用户id",required = true)
            @PathVariable Long userId) {
        List<UserLoginRecord> userLoginRecordList=userLoginRecordService.listTop50(userId);
        return R.ok().data("list", userLoginRecordList);
    }
}
