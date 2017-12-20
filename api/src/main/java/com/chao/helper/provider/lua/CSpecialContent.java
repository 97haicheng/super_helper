package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/15.
 * Description :
 */
public class CSpecialContent {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getPageList());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("show_order", "1499243022031");
        params.put("special_content_type", "02");
        params.put("content", "展示内容1");
        params.put("db_type_id", "1");
        params.put("db_album_id", "2");
        params.put("db_id", "3");
        params.put("content_id", "4");
        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("show_order", "1499243022031");
        params.put("special_content_type", "03");
        params.put("content", "展示内容3");
        params.put("db_type_id", "1");
        params.put("db_album_id", "2");
        params.put("db_id", "3");
        params.put("content_id", "4");
        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("show_order", "1499243022031");
        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("begin_num", "1");
        params.put("page_size", "20");
        return params;
    }


}
