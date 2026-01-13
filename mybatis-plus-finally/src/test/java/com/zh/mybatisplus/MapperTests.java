package com.zh.mybatisplus;

import com.zh.mybatisplus.entity.User;
import com.zh.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("zh");
        user.setAge(18);

        //不设置email属性，则生成的动态sql中不包括email字段
        int result = userMapper.insert(user);
        System.out.println("影响的行数: "+result); //影响的行数
        System.out.println("id: "+user.getId()); //id自动回填
    }

    @Test
    public void testSelect(){
        //按id查询
        User user = userMapper.selectById(1);
        System.out.println(user);

        //按id列表查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        users.forEach(System.out::println);

        //按条件查询
        Map<String,Object> map = new HashMap<>();
        map.put("name","Helen");  //注意此处是表中的列名，不是类中的属性名
        map.put("age",18);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setAge(18);

        //注意：update时生成的sql自动是动态sql
        int result = userMapper.updateById(user);
        System.out.println("影响的行数是: "+result);
    }

    @Test
    public void testDelete(){
        int result = userMapper.deleteById(1L);
        System.out.println("影响的行数是: "+result);
    }

    @Test
    public void testSelectAllByName(){
        List<User> users = userMapper.selectAllByName("Helen0");
        users.forEach(System.out::println);
    }
}
