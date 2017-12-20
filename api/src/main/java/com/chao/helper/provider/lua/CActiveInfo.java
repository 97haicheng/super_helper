package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/19.
 * Description :
 */
public class CActiveInfo {

    public static void main(String[] args) {
        System.out.println("doInsert : " + JSONObject.toJSONString(doInsert()));
        System.out.println("doUpdate : " + JSONObject.toJSONString(doUpdate()));
        System.out.println("doDelete : " + JSONObject.toJSONString(doDelete()));
        System.out.println("getDataOne : " + JSONObject.toJSONString(getDataOne()));
        System.out.println("getPageList : " + JSONObject.toJSONString(getPageList()));
    }


    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("active_id", "1|2");
        params.put("active_type", "1");
        params.put("user_id", "1");
        params.put("item_id", "1");
        params.put("item_type", "1");
        params.put("active_desc", "动态描述");
        params.put("video_path", "/path/video");
        params.put("duration", "500");
        params.put("src_active_id", "1|2");
        params.put("from_active_id", "1|3");
        params.put("is_original", "1");
        params.put("status", "1");
        params.put("post_time", "2017-08-23 00:00:00");

        List<Map> image = new ArrayList<>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("img_id", "2");
        param1.put("image_addr", "/pic/img");
        param1.put("img_width", "200");
        param1.put("img_height", "200");
        param1.put("show_order", "1");
        param1.put("active_id", "1");
        image.add(param1);

        Map<String, Object> param2 = new HashMap<String, Object>();
        param2.put("img_id", "3");
        param2.put("image_addr", "/pic/img");
        param2.put("img_width", "200");
        param2.put("img_height", "200");
        param2.put("show_order", "1");
        param2.put("active_id", "1");
        image.add(param2);

        params.put("image", image);

        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("active_id", "1|2");
        params.put("active_type", "2");
        params.put("user_id", "1");
        params.put("item_id", "2");
        params.put("item_type", "2");
        params.put("active_desc", "动态描述动态描述");
        params.put("video_path", "/path/video/path/video");
        params.put("duration", "1000");
        params.put("src_active_id", "1|2");
        params.put("from_active_id", "1|3");
        params.put("is_original", "0");
        params.put("status", "0");
        params.put("post_time", "2017-08-23 00:00:00");

        List<Map> image = new ArrayList<>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("img_id", "2");
        param1.put("image_addr", "/pic/img/pic/img");
        param1.put("img_width", "400");
        param1.put("img_height", "400");
        param1.put("show_order", "1");
        param1.put("active_id", "1");
        image.add(param1);

        Map<String, Object> param2 = new HashMap<String, Object>();
        param2.put("img_id", "3");
        param2.put("image_addr", "/pic/img/pic/img");
        param2.put("img_width", "400");
        param2.put("img_height", "400");
        param2.put("show_order", "2");
        param2.put("active_id", "1");
        image.add(param2);

        params.put("image", image);

        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("active_id", "1|2");
        params.put("user_id", "1");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("active_id", "1|2");
        params.put("user_id", "1");
        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", "0");
        params.put("page_size", "20");
        params.put("user_id", "1");
        params.put("timestamp", "1503417600");
        return params;
    }

}
