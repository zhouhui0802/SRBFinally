package com.zh.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class Product
{
    private Long id;

    private String name;

    private Integer price;

    @Version  //添加乐观锁
    private Integer version;
}
