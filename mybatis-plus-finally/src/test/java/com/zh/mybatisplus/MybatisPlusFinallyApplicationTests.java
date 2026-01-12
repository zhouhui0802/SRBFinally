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
