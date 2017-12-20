package com.chao.helper.provider.lua;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuChao on 2017/6/9.
 * Description :
 */
public class CCommonModule {

    public static Map<String, Object> getDatakey(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table_name", "table_name");
        return params;
    }
}
