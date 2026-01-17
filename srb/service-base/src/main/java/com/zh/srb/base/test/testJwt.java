package com.zh.srb.base.test;

import com.zh.srb.base.util.JwtUtils;

public class testJwt {

    public static void main(String[] args) {
        JwtUtils.createToken(111L,"123");
    }
}
