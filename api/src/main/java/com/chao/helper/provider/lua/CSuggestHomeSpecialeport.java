package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/8.
 * Description :
 */
public class CSuggestHomeSpecialeport {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getPageList());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> getPageList(){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "1");
        params.put("page_size", "1");
        params.put("timestamp", "1499243022031");
        return params;
    }

}
