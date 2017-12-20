package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuChao on 2017/8/10.
 * Description :
 */
public class CDiscusseplyAward {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doUpdate());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> getList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topic_id", "1");

        return params;
    }

    public static List<Map> doUpdate(){

        List<Map> update = new ArrayList<>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("reply_id", "1");
        param1.put("show_order", "1");
        param1.put("user_id", "4");
        param1.put("votes", "0");
        param1.put("awards", "好奖品");
        param1.put("topic_id", "1");
        param1.put("rule_id", "1");
        update.add(param1);

        Map<String, Object> param2 = new HashMap<String, Object>();
        param2.put("reply_id", "1");
        param2.put("show_order", "1");
        param2.put("user_id", "4");
        param2.put("votes", "0");
        param2.put("awards", "好奖品");
        param2.put("topic_id", "1");
        param2.put("rule_id", "1");
        update.add(param2);

        return update;
    }

}
