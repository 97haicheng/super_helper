package com.chao.helper.provider.lua;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/13.
 * Description :
 */
public class CTest {

    public static Map<String, Object> cTest(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("test1", "test1");
        params.put("test2", "test2");
        params.put("test3", "test3");
        params.put("test4", "test4");
        params.put("test5", "test5");
        return params;
    }
}
