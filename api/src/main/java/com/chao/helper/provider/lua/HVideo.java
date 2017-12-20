package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/8/8.
 * Description :
 */
public class HVideo {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doDeleteCommentPraise());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsertShare(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "4");
        params.put("video_id", "1");
        params.put("share_platform", "01");
        return params;
    }

    public static Map<String, Object> doInsertComment(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("video_id", "1");
        params.put("parent_id", "2");
        params.put("user_id", "4");
        params.put("content", "评论内容");
        return params;
    }

    public static Map<String, Object> doDeleteComment(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("video_comment_id", "1|1");
        return params;
    }

    public static Map<String, Object> doInsertView(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "4");
        params.put("video_id", "1");
        params.put("is_optimize", "1");
        return params;
    }

    public static Map<String, Object> doInsertCommentPraise(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "4");
        params.put("video_comment_id", "1|1");
        return params;
    }

    public static Map<String, Object> doDeleteCommentPraise(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "4");
        params.put("video_comment_id", "1|1");
        return params;
    }


}
