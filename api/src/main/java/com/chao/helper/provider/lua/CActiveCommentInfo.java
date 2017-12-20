package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/8/9.
 * Description :
 */
public class CActiveCommentInfo {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getPageList());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "0");
        params.put("page_size", "20");
        params.put("active_id", "1|2");
        return params;
    }

    public static Map<String, Object> getHotList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "0");
        params.put("page_size", "20");
        params.put("active_id", "1|2");
        return params;
    }

}
