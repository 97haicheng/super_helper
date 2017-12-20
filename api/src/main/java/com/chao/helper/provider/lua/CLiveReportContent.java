package com.chao.helper.provider.lua;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/21.
 * Description :
 */
public class CLiveReportContent {

    public static void main(String[] args) {
        String json = JSONObject.toJSONString(doDelete());
        System.out.println("json : " + json);
    }

    public static Map<String, Object> doInsert(){

        List<Map> image = new ArrayList<>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("img_id", "1");
        param1.put("image_addr", "/pic/img");
        param1.put("img_format", "jpg");
        param1.put("img_width", "100");
        param1.put("img_height", "100");
        image.add(param1);

        Map<String, Object> param2 = new HashMap<String, Object>();
        param2.put("img_id", "2");
        param2.put("image_addr", "/pic/img");
        param2.put("img_format", "jpg");
        param2.put("img_width", "100");
        param2.put("img_height", "100");
        image.add(param2);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("live_report_content_id", "4");
        params.put("report_id", "1");
        params.put("audio_path", "/audio/path");
        params.put("duration", "500");
        params.put("size", "100");
        params.put("report_text", "现场报道");
        params.put("create_time", "1499243022031");
        params.put("image", image);

        return params;
    }

    public static Map<String, Object> doUpdate(){
        List<Map> image = new ArrayList<>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put("img_id", "1");
        param1.put("image_addr", "/pic/img/pic/img");
        param1.put("img_format", "jpgjpg");
        param1.put("img_width", "200");
        param1.put("img_height", "200");
        image.add(param1);

        Map<String, Object> param2 = new HashMap<String, Object>();
        param2.put("img_id", "2");
        param2.put("image_addr", "/pic/img/pic/img");
        param2.put("img_format", "jpgjpg");
        param2.put("img_width", "200");
        param2.put("img_height", "200");
        image.add(param2);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("live_report_content_id", "4");
        params.put("report_id", "1");
        params.put("audio_path", "/audio/path");
        params.put("duration", "5000");
        params.put("size", "1000");
        params.put("report_text", "现场报道现场报道");
        params.put("create_time", "1499243022031");
        params.put("image", image);

        return params;
    }

    public static Map<String, Object> doDelete(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("live_report_content_id", "4");
        params.put("report_id", "1");
        return params;
    }

}
