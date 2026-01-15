package com.zh;

import com.zh.srb.core.mapper.DictMapper;
import com.zh.srb.core.pojo.entity.Dict;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTests {

    //@Resource
    //private RedisTemplate redisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DictMapper dictMapper;

    @Test
    public void saveDict(){
        Dict dict = dictMapper.selectById(1L);
        redisTemplate.opsForValue().set("dictTest", dict,5, TimeUnit.MINUTES);
        System.out.println("------");
    }

    @Test
    public void getDict(){
        Dict dict = (Dict) redisTemplate.opsForValue().get("dictTest");
        System.out.println(dict);
    }


}
