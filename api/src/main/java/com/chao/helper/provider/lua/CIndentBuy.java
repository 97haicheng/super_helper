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
public class CIndentBuy {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getDetailPageList());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("indent_number", "2");
        params.put("user_id", "1");
        params.put("operate_system", "IOS");
        params.put("pay_price", "100");
        params.put("discount_price", "3");
        params.put("status", "1");
        params.put("is_pay", "1");
        params.put("invoice_id", "1");
        params.put("is_invoice", "1");
        params.put("is_clearing", "1");
        params.put("is_show", "1");
        params.put("create_time", "1499243022031");

        List<Map> detail = new ArrayList<>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("indent_detail_id", "2");
        param1.put("indent_id", "1");
        param1.put("content_type", "02");
        param1.put("content_id", "1");
        param1.put("pay_price", "1");
        param1.put("cost_price", "2");
        detail.add(param1);

        params.put("detail", detail);

        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("indent_number", "22");
        params.put("user_id", "1");
        params.put("operate_system", "IOSIOS");
        params.put("pay_price", "100100");
        params.put("discount_price", "33");
        params.put("status", "0");
        params.put("is_pay", "0");
        params.put("invoice_id", "1");
        params.put("is_invoice", "1");
        params.put("is_clearing", "1");
        params.put("is_show", "1");
        params.put("create_time", "1499243022031");

        List<Map> detail = new ArrayList<>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("indent_detail_id", "2");
        param1.put("indent_id", "1");
        param1.put("content_type", "0202");
        param1.put("content_id", "11");
        param1.put("pay_price", "11");
        param1.put("cost_price", "22");
        detail.add(param1);

        params.put("detail", detail);

        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("user_id", "1");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("user_id", "1");
        return params;
    }

    public static Map<String, Object> getDetailPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("indent_id", "1");
        params.put("user_id", "1");
        params.put("begin_num", "0");
        params.put("page_size", "10");
        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "1");
        params.put("begin_num", "0");
        params.put("page_size", "10");
        return params;
    }

}
