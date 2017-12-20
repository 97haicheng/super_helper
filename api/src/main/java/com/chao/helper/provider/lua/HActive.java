package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/8/9.
 * Description :
 */
public class HActive {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doDeleteComment());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsertFavor(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "1");
        params.put("active_id", "1|2");
        params.put("is_optimize", "1");
        return params;
    }

    public static Map<String, Object> doDeleteFavor(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "1");
        params.put("active_id", "1|2");
        return params;
    }

    public static Map<String, Object> doInsertComment(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("active_id", "1|2");
        params.put("user_id", "1");
        params.put("content", "评论内容");
        params.put("parent_id", "2");
        params.put("is_optimize", "1");
        return params;
    }

    public static Map<String, Object> doDeleteComment(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("active_comment_id", "1|2");
        params.put("is_optimize", "1");
        return params;
    }

    public static Map<String, Object> doInsertCommentPraise(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "1");
        params.put("active_comment_id", "1|2");
        return params;
    }

    public static Map<String, Object> doDeleteCommentPraise(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "1");
        params.put("active_comment_id", "1|2");
        return params;
    }

}
