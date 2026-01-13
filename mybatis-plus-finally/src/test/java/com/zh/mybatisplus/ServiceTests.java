package com.zh.mybatisplus;

import com.zh.mybatisplus.entity.User;
import com.zh.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void testCount(){
        long count=userService.count();
        System.out.println(count);
    }

    @Test
    public void testSaveBatch(){
        ArrayList<User> users=new ArrayList<User>();
        for(int i=0;i<5;i++){
            User user=new User();
            user.setName("Helen"+i);
            user.setAge(10+i);
            users.add(user);
        }

        userService.saveBatch(users);
    }

    @Test
    public void testListAllByName(){
        List<User> users=userService.listAllByName("Helen0");
        users.forEach(System.out::println);
    }
}
