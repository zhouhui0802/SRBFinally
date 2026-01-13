package com.zh.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.mybatisplus.entity.Product;
import com.zh.mybatisplus.entity.User;
import com.zh.mybatisplus.mapper.ProductMapper;
import com.zh.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InterceptorTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testSelectPage(){

        //创建分页参数
        Page<User> pageParam=new Page<>(1,5);
        //执行分页查询
        userMapper.selectPage(pageParam,null);
        //查看分页参数的成员
        System.out.println("pageParam: "+pageParam);

        List<User> users=pageParam.getRecords();
        users.forEach(System.out::println);

        long total=pageParam.getTotal();
        System.out.println("total: "+total);

        boolean bn=pageParam.hasNext();
        System.out.println("bn: "+bn);

        boolean bp=pageParam.hasPrevious();
        System.out.println("bp: "+bp);
    }

    /*
    其中报出了Caused by: org.apache.ibatis.binding.BindingException:
    Parameter 'age' not found. Available parameters are [arg1, arg0, param1, param2]错误
    必须在接口中添加方法名的配置
    IPage<User> selectPageByPage(@Param("page") Page<User> page, @Param("age") Integer age);
    这样就可以识别出数据库中的age字段
     */
    @Test
    public void testSelectPageVo(){
        Page<User> pageParam=new Page<>(1,4);
        userMapper.selectPageByPage(pageParam,31);
        List<User> users=pageParam.getRecords();
        users.forEach(System.out::println);
    }

    //乐观锁的测试  没有添加乐观锁
    @Test
    public void testConcurrentUpdate(){

        Product p1=productMapper.selectById(1L);

        Product p2=productMapper.selectById(1L);

        p1.setPrice(p1.getPrice()+50);
        int result1=productMapper.updateById(p1);
        System.out.println("result1修改的结果为 : "+result1);

        p2.setPrice(p2.getPrice()-30);
        int result2=productMapper.updateById(p2);
        System.out.println("result2修改的结果为: " +result2);

        Product p3=productMapper.selectById(1L);
        System.out.println("p3.getPrice(): "+p3.getPrice());
    }

    @Test  //添加乐观锁之后
    public void testAddConcurrentUpdate(){

        Product p1=productMapper.selectById(1L);

        Product p2=productMapper.selectById(1L);

        p1.setPrice(p1.getPrice()+50);
        int result1=productMapper.updateById(p1);
        System.out.println("result1修改的结果为 : "+result1);

        p2.setPrice(p2.getPrice()-30);
        int result2=productMapper.updateById(p2);
        System.out.println("result2修改的结果为: " +result2);
        if(result2==0){
            p2=productMapper.selectById(1L);
            System.out.println("p2.getPrice(): "+p2.getPrice());
            p2.setPrice(p2.getPrice()-30);
            result2=productMapper.updateById(p2);
        }

        Product p3=productMapper.selectById(1L);
        System.out.println("p3.getPrice(): "+p3.getPrice());
    }

}
