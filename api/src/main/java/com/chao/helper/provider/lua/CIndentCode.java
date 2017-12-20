package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/20.
 * Description :
 */
public class CIndentCode {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getCode());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> getCode(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", "1");
        return params;
    }
}
