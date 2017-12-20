package com.chao.helper.provider.lua.common;

import com.alibaba.fastjson.JSONObject;
import com.chao.helper.provider.lua.CAreaCode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by think on 2015/12/25.
 *
 * 调用lua脚本
 */
public class LuaHelper {


    public static void main(String[] args) {

        LuaHelper.post_create(Constant.URL_cAreaCode_getCityList, CAreaCode.getCityList());

    }

    public static void post_create(String url, Map<String, Object> params) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式

        String json = JSONObject.toJSONString(params);
        System.out.println("request : " + json);

        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        post.setEntity(entity);

        try {
            HttpResponse response = client.execute(post);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(response.getEntity(), "UTF-8");

                System.out.println("response :" + s);

            } else {
                System.out.println(line.getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
    }


}
