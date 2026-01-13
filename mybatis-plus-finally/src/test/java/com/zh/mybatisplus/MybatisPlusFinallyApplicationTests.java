package com.zh.mybatisplus;

import com.zh.mybatisplus.entity.User;
import com.zh.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisPlusFinallyApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    /**
    * @description TODO    当出现Caused by: javax.net.ssl.SSLException: Received fatal alert: internal_error错误错误的时候
     * 配置上修改，其中mysql为5版本的时候  添加 &useSSL=false
    *
     * 从GitHub上拉取下来，直接使用IDEA软件中的Git clone命令
     * 修改了配置；
     * 因为板子上的MySQl版本可以适配5,但是自己电脑上不可以，所以升级了MySQL的版本为8，配置的写法也对应发生了改变
     * 添加时区间  &serverTimezone=Asia/Shanghai
     * DriverClass的写法修改为： com.mysql.cj.jdbc.Driver
     * 增加的知识点： 查询自己数据库的版本  select version()
    * @return void
    * @author zhouhui
    * @date 2026/1/12 20:47
    */
    @Test
    void testSelectList(){
        List<User> users=userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
