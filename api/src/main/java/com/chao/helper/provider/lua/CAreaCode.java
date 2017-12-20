package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/4.
 * Description :
 */
public class CAreaCode {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getProvince());
        System.out.println("json : " + json);
    }


    public static Map<String, Object> doInsertCountry(){
        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("id", "1");
//        params.put("country_name", "中国");
//        params.put("phone_code", "86");
        params.put("id", "2");
        params.put("country_name", "新加坡");
        params.put("phone_code", "0065");
        return params;
    }

    public static Map<String, Object> doInsertProvince(){
        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("country_id", "1");
//        params.put("id", "1");
//        params.put("province_name", "辽宁");
//        params.put("province_short_name", "辽");

        params.put("country_id", "1");
        params.put("id", "2");
        params.put("province_name", "吉林");
        params.put("province_short_name", "吉");
        return params;
    }

    public static Map<String, Object> doInsertCity(){
        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("province_id", "1");
//        params.put("id", "1");
//        params.put("city_name", "沈阳");
//        params.put("zip_code", "110000");

        params.put("province_id", "1");
        params.put("id", "2");
        params.put("city_name", "阜新");
        params.put("zip_code", "123000");
        return params;
    }

    public static Map<String, Object> getCountryList(){
        Map<String, Object> params = new HashMap<String, Object>();
        return params;
    }

    public static Map<String, Object> getProvinceList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("country_id", "1");
        return params;
    }

    public static Map<String, Object> getCityList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("province_id", "2");
        return params;
    }

    public static Map<String, Object> getProvince(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("country_id", "1");
        params.put("province_id", "1");
        return params;
    }
}
