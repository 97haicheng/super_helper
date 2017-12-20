package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/21.
 * Description :
 */
public class CLiveReport {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getDataOne());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("live_report_id", "1");
        params.put("title_name", "现场报道");
        params.put("image_addr", "/pic/img");
        params.put("user_id", "4");
        params.put("greeting", "欢迎来到现场报道");
        params.put("begin_time", System.currentTimeMillis());
        params.put("end_time", System.currentTimeMillis());
        params.put("create_time", "1499243022031");
        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("live_report_id", "1");
        params.put("title_name", "现场报道现场报道");
        params.put("image_addr", "/pic/img/pic/img");
        params.put("user_id", "4");
        params.put("greeting", "欢迎来到现场报道欢迎来到现场报道");
        params.put("begin_time", System.currentTimeMillis());
        params.put("end_time", System.currentTimeMillis());
        params.put("create_time", "1499243022031");
        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("live_report_id", "1");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("live_report_id", "1");
        return params;
    }

}
