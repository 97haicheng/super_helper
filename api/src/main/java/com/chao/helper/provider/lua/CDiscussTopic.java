package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/18.
 * Description :
 */
public class CDiscussTopic {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doInsert());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){

        // 有奖相对论
        List rule_ids = new ArrayList();
        rule_ids.add(1);
        rule_ids.add(2);
        rule_ids.add(3);

        List rule_names = new ArrayList();
        rule_names.add(1111);
        rule_names.add(2222);
        rule_names.add(3333);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");
        params.put("topic_type", "03");
        params.put("top_title", "主标题");
        params.put("second_title", "副标题");
        params.put("image_addr", "/pic/img");
        params.put("describe", "这是一条新的相对论");
        params.put("award_rule", "一等奖必中");
        params.put("begin_time", "2017-08-17 00:00:00");
        params.put("end_time", "2017-08-17 23:59:59");
        params.put("is_top", "0");
        params.put("vote_interval", "60");
        params.put("vote_num", "3");
        params.put("release_time", "2017-08-17 00:00:00");
        params.put("rule_ids", rule_ids);
        params.put("rule_names", rule_names);

        // 普通相对论
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("topic_id", "2");
//        params.put("topic_type", "02");
//        params.put("top_title", "主标题");
//        params.put("second_title", "副标题");
//        params.put("image_addr", "/pic/img");
//        params.put("describe", "这是一条新的相对论");
//        params.put("is_top", "0");
//        params.put("release_time", "2017-08-17 00:00:00");

        return params;
    }

    public static Map<String, Object> doUpdate(){
        // 有奖相对论
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");
        params.put("topic_type", "03");
        params.put("top_title", "主标题主标题");
        params.put("second_title", "副标题副标题");
        params.put("image_addr", "/pic/img/pic/img");
        params.put("describe", "这是一条新的相对论这是一条新的相对论");
        params.put("award_rule", "一等奖必中一等奖必中");
        params.put("end_time", "2017-08-18 23:59:59");
        params.put("is_top", "1");
        params.put("release_time", "2017-08-17 00:00:00");
        params.put("rule_id", "13");

        // 普通相对论
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("topic_id", "2");
//        params.put("topic_type", "02");
//        params.put("top_title", "主标题主标题");
//        params.put("second_title", "副标题副标题");
//        params.put("image_addr", "/pic/img/pic/img");
//        params.put("describe", "这是一条新的相对论这是一条新的相对论");
//        params.put("is_top", "0");
//        params.put("release_time", "2017-08-17 00:00:00");

        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");
        return params;
    }

    public static Map<String, Object> doUpdateTop(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");
        params.put("is_top", "1");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");
        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "0");
        params.put("page_size", "1");
        params.put("timestamp", "1502899200");
        return params;
    }

}
