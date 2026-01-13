package com.zh.mybatisplus.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zh.mybatisplus.mapper")
public class MybatisConfig {

    @Bean   //默认的配置写法，主需要添加DbType.MYSQL 告诉是让MySQL的数据库分类
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//添加分页插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());//添加乐观锁插件
        return interceptor;
    }
}
