package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/23.
 * Description :
 */
public class CSMSCode {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doDelete());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", "15840043215");
        params.put("type", "1");
        params.put("code", "123456");
        params.put("second", 100*1000);
        params.put("uuid", "6dabbabe5e2f40d5905f65b0d4b5f8a2");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", "15840043215");
        params.put("type", "1");
        params.put("uuid", "6dabbabe5e2f40d5905f65b0d4b5f8a2");
        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", "15840043215");
        params.put("type", "1");
        params.put("uuid", "6dabbabe5e2f40d5905f65b0d4b5f8a2");
        return params;
    }
}
