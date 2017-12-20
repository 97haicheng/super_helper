package com.chao.helper.provider.lua;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/16.
 * Description :
 */
public class CUserInfo {

    public static Map<String, Object> doInsert(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "7");
        params.put("user_type", "user_type");
        params.put("host_honor", "host_honor");
        params.put("tag_id", "tag_id");
        params.put("identify_type", "identify_type");
        params.put("account_number", "account_number");
        params.put("nick_name", "nick_name");
        params.put("image_addr", "image_addr");
        params.put("app_image_addr", "app_image_addr");
        params.put("pcweb_image_addr", "pcweb_image_addr");
        params.put("birthday", "birthday");
        params.put("sex", "sex");
        params.put("signature", "signature");
        params.put("area_code", "area_code");
        params.put("area_name", "area_name");
        return params;
    }

    public static Map<String, Object> getDataOne(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "1");
        return params;
    }

    public static Map<String, Object> doUpdate(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "1");
        params.put("nick_name", "1");
        params.put("image_addr", "1");
        params.put("app_image_addr", "1");
        params.put("pcweb_image_addr", "1");
        params.put("birthday", "1");
        params.put("sex", "1");
        params.put("signature", "1");
        params.put("area_code", "1");
        params.put("area_name", "1");
        return params;
    }

    public static Map<String, Object> doUserType(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "2");
        params.put("user_type", "user_type");
        return params;
    }

}
