package com.chao.helper;

import org.junit.Test;


public class CommonTest {

    @Test
    public void test() {
        String str = "com.chao.helper.service.ssmone.RediscontentServiceImpl";
        String substring = str.substring(25, str.lastIndexOf("."));
        System.out.println(substring);
    }


}
