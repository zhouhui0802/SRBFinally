package com.zh;

import com.zh.srb.base.util.JwtUtils;
import org.junit.Test;

public class TestToken {

    @Test
    public void test(){
        String token = JwtUtils.createToken(111L, "123456");
    }
}
