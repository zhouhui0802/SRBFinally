package com.zh.mybatisplus.entity;


import lombok.Data;

/**
 * @author zhouhui
 * @version 1.0
 * @description java中的类跟数据库的对应关系  Long - BIGINT  //  Integer - int
 * @date 2026/1/12 15:28
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;
}
