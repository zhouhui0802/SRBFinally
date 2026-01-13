package com.zh.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
* @description TODO
* @param
* @return     启动报出： Factory method 'namedParameterJdbcTemplate' threw exception错误 因为缺少spring-jdbc依赖   namedParameterJdbcTemplate在spring-jdbc
 * 同时
* @author zhouhui
* @date 2026/1/12 15:42
*/
@SpringBootApplication
// @MapperScan("com.zh.mybatisplus.mapper")  //将次数注解移动到mybatisplus的配置类中
public class MybatisPlusFinallyApplication {

    public static void main(String[] args) {

        SpringApplication.run(MybatisPlusFinallyApplication.class, args);
    }

}
