package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/6.
 * Description :
 */
public class CspecialVote {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doDelete());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("show_order", "2");
        params.put("db_type_id", "3");
        params.put("db_album_id", "4");
        params.put("db_id", "5");
        params.put("user_id", "6");
        params.put("vote_content_id", "1");

        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("show_order", "2");
        params.put("votes", "99");
        params.put("db_type_id", "3");
        params.put("db_album_id", "4");
        params.put("db_id", "5");
        params.put("user_id", "6");
        params.put("vote_content_id", "1");

        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("db_type_id", "3");
        params.put("db_album_id", "4");
        params.put("db_id", "5");
        params.put("user_id", "6");
        params.put("vote_content_id", "1");

        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("special_id", "1");
        params.put("begin_num", "1");
        params.put("page_size", "1");
        return params;
    }
}
