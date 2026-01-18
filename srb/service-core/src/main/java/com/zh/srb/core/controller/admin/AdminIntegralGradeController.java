package com.zh.srb.core.controller.admin;

import com.zh.srb.common.exception.Assert;
import com.zh.srb.common.result.R;
import com.zh.srb.common.result.ResponseEnum;
import com.zh.srb.core.pojo.entity.IntegralGrade;
import com.zh.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "积分等级管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
@Slf4j
public class AdminIntegralGradeController {

    @Autowired
    private IntegralGradeService integralGradeService;


    @GetMapping("/test")  //lombok的使用，尽量带上version 版本号，防止出现数据为空的现象
    public List listAllTest() {
        List<IntegralGrade> list=integralGradeService.list();
        return list;
    }

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R listAll() {
        List<IntegralGrade> list=integralGradeService.list();
        return R.ok().data("list", list).message("success");
    }

    @ApiOperation(value="根据ID删除数据记录",notes = "逻辑删除数据记录")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value="数据ID",example = "100",required = true)
            @PathVariable Long id) {
        boolean result = integralGradeService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade
    ){
        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean result = integralGradeService.save(integralGrade);
        if(result){
            return R.ok().message("保存成功");
        }else{
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据ID",required = true,example = "1")
            @PathVariable Long id
    ){
        IntegralGrade integralGrade=integralGradeService.getById(id);
        if(integralGrade!=null){
            return R.ok().data("record", integralGrade);
        }else {
            return R.error().message("获取数据失败");
        }
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade
    ){
        boolean result = integralGradeService.updateById(integralGrade);
        if(result){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("更新失败");
        }
    }
}
