package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/8/10.
 * Description :
 */
public class HDiscuss {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doInsertShareReply());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsertPraise(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("discuss_reply_id", "1|1");
        params.put("user_id", "4");

        return params;
    }

    public static Map<String, Object> doDeletePraise(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("discuss_reply_id", "1|1");
        params.put("user_id", "4");

        return params;
    }

    public static Map<String, Object> doInsertVote(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("discuss_reply_id", "1|1");
        params.put("user_id", "4");
        params.put("imei", "444444444444");
        params.put("operator_time", "1499243022031");

        return params;
    }

    public static Map<String, Object> doInsertComment(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("reply_id", "1");
        params.put("user_id", "4");
        params.put("content", "评论内容评论内容");
        params.put("parent_id", "2");

        return params;
    }

    public static Map<String, Object> doDeleteComment(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("discuss_comment_id", "1|1");

        return params;
    }

    public static Map<String, Object> doInsertCommentPraise(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("discuss_comment_id", "1|1");
        params.put("user_id", "4");

        return params;
    }

    public static Map<String, Object> doDeleteCommentPraise(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("discuss_comment_id", "1|1");
        params.put("user_id", "4");

        return params;
    }

    public static Map<String, Object> doInsertShare(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "4");
        params.put("discuss_topicId", "1");
        params.put("share_platform", "01");

        return params;
    }

    public static Map<String, Object> doInsertShareReply(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "4");
        params.put("discuss_reply_id", "1|1");
        params.put("share_platform", "01");

        return params;
    }

}
