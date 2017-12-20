package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/20.
 * Description :
 */
public class CIndentPay {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getPageList());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("indent_number", "2");
        params.put("pay_type", "1");
        params.put("user_id", "4");
        params.put("operate_system", "IOS");
        params.put("pay_price", "100");
        params.put("ticket_num", "1000");
        params.put("discount_price", "3");
        params.put("surplus_num", "999");
        params.put("status", "1");
        params.put("is_pay", "1");
        params.put("create_time", "1499243022031");
        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("indent_number", "22");
        params.put("pay_type", "11");
        params.put("user_id", "4");
        params.put("operate_system", "IOSIOS");
        params.put("pay_price", "100100");
        params.put("ticket_num", "10001000");
        params.put("discount_price", "33");
        params.put("surplus_num", "999999");
        params.put("status", "11");
        params.put("is_pay", "11");
        params.put("create_time", "1499243022031");
        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("user_id", "4");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("user_id", "4");
        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "0");
        params.put("page_size", "20");
        params.put("user_id", "4");
        return params;
    }

}
