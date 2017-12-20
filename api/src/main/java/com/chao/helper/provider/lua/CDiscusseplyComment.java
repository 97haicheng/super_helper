package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/8/10.
 * Description :
 */
public class CDiscusseplyComment {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doDelFrozen());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "0");
        params.put("page_size", "20");
        params.put("reply_id", "1");

        return params;
    }

    public static Map<String, Object> getHotList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "0");
        params.put("page_size", "20");
        params.put("reply_id", "1");

        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reply_id", "1");

        return params;
    }

    public static Map<String, Object> doDelFrozen(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reply_id", "1");
        params.put("user_id", "4");

        return params;
    }

}
