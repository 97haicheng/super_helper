package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/4.
 * Description :
 */
public class CImgCode {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doInsert());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", "058b6e6afad14e268416849ede40a47e");
        params.put("type", "1");
        params.put("code", "123456");
        params.put("second", 100*1000);
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", "058b6e6afad14e268416849ede40a47e");
        params.put("type", "1");
        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", "058b6e6afad14e268416849ede40a47e");
        params.put("type", "1");
        return params;
    }

}
