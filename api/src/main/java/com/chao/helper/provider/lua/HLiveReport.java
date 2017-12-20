package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/8/10.
 * Description :
 */
public class HLiveReport {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doUpdateView());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsertShare(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "4");
        params.put("report_id", "1");
        params.put("share_platform", "01");

        return params;
    }

    public static Map<String, Object> doInsertView(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "4");
        params.put("live_report_id", "1");
        params.put("view_begin_time", "1499243022031");
        params.put("view_end_time", "1499243022031");

        return params;
    }

    public static Map<String, Object> doUpdateView(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_live_report_view_id", "1");
        params.put("view_end_time", "1499243022031");

        return params;
    }

}
