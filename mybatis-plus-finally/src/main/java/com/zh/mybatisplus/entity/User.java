package com.zh.mybatisplus.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhouhui
 * @version 1.0
 * @description java中的类跟数据库的对应关系  Long - BIGINT  //  Integer - int
 * 其中数据库中的datetime属性对应JAVA类中的LocalDateTime类别
 * @date 2026/1/12 15:28
 */
@Data
public class User {

    @TableId(type = IdType.ASSIGN_ID)  //默认是雪花算法
    private Long id;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private Integer age;

    private String email;

    // 使用Mybatis-plus的自动填充功能维护这两个字段
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic  // 逻辑删除 一般来说当is_deleted=1的时候等于逻辑上被删除
    @TableField(value = "is_deleted") //映射到真实的数据库中的字段
    private Integer deleted;
}
