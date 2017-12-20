package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuChao on 2017/7/21.
 * Description :
 */
public class CVideo {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(getPageList());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("video_id", "1");
        params.put("video_name", "视频");
        params.put("description", "视频描述");
        params.put("video_path", "/video/path");
        params.put("duration", "500");
        params.put("size", "100");
        params.put("image_addr", "/img/pic");
        params.put("release_time", "1499243022031");
        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("video_id", "1");
        params.put("video_name", "视频视频");
        params.put("description", "视频描述视频描述");
        params.put("video_path", "/video/path/video/path");
        params.put("duration", "500500");
        params.put("size", "100100");
        params.put("image_addr", "/img/pic/img/pic");
        params.put("release_time", "1499243022031");
        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();

        List ids = new ArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        params.put("ids", ids);
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("video_id", "1");
        return params;
    }

    public static Map<String, Object> getPageList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin_num", 0);
        params.put("page_size", 20);
        params.put("release_time", "1499243022031");
        return params;
    }
}
