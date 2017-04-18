package com.chao.helper.provider.helper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by think on 2017/4/17.
 *
 * Http请求（线程）
 */
public class HttpByThread extends Thread{

    private static final String SECURE_KEY = "QQQQQCCCCCCCCCCCCCCCCCCCCCCCCCCC";
    private static final String SP_ID = "QQ42692108000000";
    private static final String URL = "http://218.60.136.202:8020/api/b2b/v1.0";

    private String name;

    public HttpByThread(String name) {
        this.name = name;
    }

    private static String httpGet(String url){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity responseEntity = response.getEntity();
            Integer statusCode = response.getStatusLine().getStatusCode();
            if(statusCode.toString().startsWith("4")||statusCode.toString().startsWith("5")){
                return statusCode.toString();
            }
            String responseText = EntityUtils.toString(responseEntity, "UTF-8").trim();
            return responseText;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "未知错误";
    }

    static int count = 0;

    public static synchronized void addCount(){
        count ++;
    }

    public static  synchronized void printCount(){
        System.out.println(count);
    }

    public void run(){
        String result = httpGet(get_product_url());
        System.out.println(name + "请求成功:" + result);
        addCount();
        printCount();
    }

    /**
     * 获取产品
     */
    public static String get_product_url() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String timestamp = df.format(new Date());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sp_id", SP_ID);//渠道号
        params.put("timestamp", timestamp);

        String sign = null;
        try {
            sign = getSignature(params, SECURE_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return URL+"/get_product?sp_id="+SP_ID+"&timestamp="+timestamp+"&sign="+sign.toUpperCase();
    }

    /**
     * 获取加密串
     *
     */
    public static String getSignature(Map<String, Object> params, String secret) throws IOException {
        Map<String, Object> sortedParams = new TreeMap<String, Object>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            basestring.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        basestring.append("key=").append(secret);
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++){
            HttpByThread thread = new HttpByThread("("+ i +") -> run()");
            thread.start();
        }
    }
}
