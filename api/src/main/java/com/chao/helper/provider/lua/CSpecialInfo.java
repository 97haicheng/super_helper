package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/14.
 * Description :
 */
public class CSpecialInfo {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getDataOne());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("special_type", "02");
        params.put("special_name", "音乐专题音乐专题");
        params.put("image_addr", "/img/pic/img/pic");
        params.put("vote_interval", "6060");
        params.put("vote_num", "33");
        params.put("begin_time", System.currentTimeMillis());
        params.put("end_time", System.currentTimeMillis());
        params.put("order_type", "1");
        params.put("special_vote_type", "02");
        params.put("create_time", System.currentTimeMillis());
        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("special_type", "02");
        params.put("special_name", "音乐专题");
        params.put("image_addr", "/img/pic");
        params.put("vote_interval", "60");
        params.put("vote_num", "3");
        params.put("begin_time", System.currentTimeMillis());
        params.put("end_time", System.currentTimeMillis());
        params.put("order_type", "1");
        params.put("special_vote_type", "02");
        params.put("create_time", System.currentTimeMillis());
        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "3");
        params.put("page_size", "10");
        return params;
    }

}
