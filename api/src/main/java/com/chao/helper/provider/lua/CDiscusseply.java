package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/21.
 * Description :
 */
public class CDiscusseply {

    public static void main(String[] args) {
        System.out.println("doInsert : " + JSONObject.toJSONString(doInsert()));
        System.out.println("getPageList : " + JSONObject.toJSONString(getPageList()));
        System.out.println("getPageList : " + JSONObject.toJSONString(getDataOne()));
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("db_reply_id", "1");
        params.put("topic_id", "1");
        params.put("user_id", "1");
        params.put("show_order", "1");
        params.put("reply_text", "回复内容");
        params.put("audio_path", "/audio/path");
        params.put("duration", "500");
        params.put("size", "100");
        params.put("image_addr", "/img/pic");
        params.put("img_format", "jpg");
        params.put("img_width", "200");
        params.put("img_height", "200");
        params.put("reply_time", "2017-08-17 00:00:00");
        params.put("status", "1");
        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("db_reply_id", "1");
        params.put("topic_id", "1");
        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");
        params.put("begin_num", "0");
        params.put("page_size", "10");
        params.put("timestamp", "1502899200");
        params.put("login_user_id", "1");
        return params;
    }

    public static Map<String, Object> getHotList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");
        params.put("begin_num", "0");
        params.put("page_size", "10");
        return params;
    }

    public static Map<String, Object> getHotListBySize(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");
        params.put("size", "10");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reply_id", "1|1");
        params.put("login_user_id", "1");
        return params;
    }



}
