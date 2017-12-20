package com.chao.helper.provider.lua;

import com.chao.helper.provider.lua.common.Common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/9.
 * Description :
 */
public class CAudioInfo {

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("s_id", "4");
        params.put("s_album_id", "2");
        params.put("audio_name", "声音");
        params.put("description", "描述");
        params.put("audio_text", "听声音");
        params.put("audio_path", "https://xxx/path/shengyin1.mp3");
        params.put("duration", "256");
        params.put("size", "1000");
        params.put("image_addr", "https://xxx/path/image.png");
        params.put("user_id", "10");
        params.put("status", "1");
        params.put("show_order", "2");
        params.put("release_time", Common.getTime());
        params.put("modify_time", Common.getTime());
        params.put("offline_time", Common.getTime());
        params.put("offline_reason", "offline_reason");
        params.put("is_pay", "1");
        params.put("tag_ids", "1,2,3");
        params.put("id", "2|1");
        params.put("album_id", "3|2");

        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "2|1");
        return params;
    }
}
