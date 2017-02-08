package com.chao.helper.qghx;

import com.chao.helper.utils.JacksonUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by think on 2017/2/6.
 */
public class MapToList {

    public static void main(String[] args) {

        Map<String, Object> params = new HashMap<String, Object>();
        ;
        params.put("1", 1);
        params.put("2", 2);
        params.put("3", 3);
        params.put("4", 4);
        params.put("5", 5);

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            parameters.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue() + ""));
        }

        System.out.println(JacksonUtil.toJSon(parameters));

    }
}
