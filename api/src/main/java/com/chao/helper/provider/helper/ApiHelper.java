package com.chao.helper.provider.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chao.helper.utils.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by think on 2015/12/25.
 *
 * 全国后向API接口测试工具类（自用）
 */
public class ApiHelper {


    private static final String SECURE_KEY = "QQQQQCCCCCCCCCCCCCCCCCCCCCCCCCCC";
    private static final String SP_ID = "QQ42692108000000";
    private static final String URL = "http://218.60.136.202:8020/api/b2b/v1.0";
//    private static final String URL = "http://127.0.0.1:8080/api/b2b/v1.0";
//    private static final String URL = "http://api.liu-bei.cn/api/b2b/v1.0";


    public static void main(String[] args) {

//        ApiHelper.order_create();
//        ApiHelper.get_order();
//        ApiHelper.get_product();
        ApiHelper.get_preOrder();

    }


    /**
     * 订购接口
     */
    public static void order_create() {
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(URL + "/order_create");

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String timestamp = df.format(new Date());
//        String[] mobileNumber = {"15840043215","15840043210"};//用户手机号
        String[] mobileNumber = {"13224186540"};//用户手机号
        String orderId = UUID.randomUUID().toString().replace("-", "");
        String sp_product_id = "104";//104联通宽带全国50M  108惠付移动30M  105辽宁联通漫游50M  109联通时科50M  110维客移动30M  111维客联通50M

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("order_id", orderId);//每个渠道订单号不超过32个字节
        params.put("sp_id",  SP_ID);//渠道在全国流量系统内注册的渠道号，不超过16个字节
        params.put("sp_product_id", sp_product_id);//渠道对接入的服务的编号，int类型
        params.put("mobile_number", JSONObject.toJSONString(mobileNumber));
        params.put("timestamp", timestamp);//订单的创建时间YYYYMMDDHHMMSS系统当前时间

        try {
            String sign = getSignature(params, SECURE_KEY);//签名
            params.put("sign", sign.toUpperCase());
            params.put("source", 3);//自留字段，区分API订购来源
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.println("signStr : " + "mobile_number="+ JSONObject.toJSONString(mobileNumber)+"&order_id="+orderId+"&sp_id="+SP_ID+"&sp_product_id="+sp_product_id+"&timestamp="+timestamp+"&key="+SECURE_KEY );
        System.out.println("sign : " + DigestUtils.MD5_32("mobile_number="+ JSONObject.toJSONString(mobileNumber)+"&order_id="+orderId+"&sp_id="+SP_ID+"&sp_product_id="+sp_product_id+"&timestamp="+timestamp+"&key="+SECURE_KEY ));

        String json = JSONObject.toJSONString(params);
        System.out.println("request : " + json);

        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        post.setEntity(entity);

        try {
            HttpResponse response = client.execute(post);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(response.getEntity());

                System.out.println("response :" + s);

                System.out.println("signStr : " + "err=0&id="+orderId+"&msg=success&key="+SECURE_KEY);
                System.out.println("sign : " + DigestUtils.MD5_32("err=0&id="+orderId+"&msg=success&key="+SECURE_KEY));
                Map map = JSON.parseObject(s);
                if(DigestUtils.MD5_32("err=0&id="+orderId+"&msg=success&key="+SECURE_KEY).equals(map.get("sign"))){
                    System.out.println("来源 : 来自全国后向");
                }
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


    /**
     * 获取订单
     */
    public static void get_order() {
        HttpClient client = HttpClientBuilder.create().build();

        String[] order_id = new String[1];//由order_id组成的数组进行json化后的字符串，每个商户订单号不超过32字节, order_id数量为1－100之间。
        order_id[0] = "ea4d43330f2545d89e7d9cc0ad11a668";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String timestamp = df.format(new Date());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("order_ids", JSONObject.toJSONString(order_id));//渠道多个订单号
        params.put("sp_id", SP_ID);//渠道号
        params.put("timestamp", timestamp);//系统当前时间戳YYYYMMDDHHMMSS

        HttpGet get = null;
        try {
            String sign = getSignature(params, SECURE_KEY);
            get = new HttpGet(URL+"/get_order?sp_id="+SP_ID+"&order_ids="+ URLEncoder.encode(JSONObject.toJSONString(order_id), "utf-8")
                    + "&timestamp="+timestamp+"&sign=" + sign.toUpperCase());

            System.out.println("request : " + "sp_id="+SP_ID+"&order_ids="+ URLEncoder.encode(JSONObject.toJSONString(order_id), "utf-8")
                    + "&timestamp="+timestamp+"&sign=" + sign.toUpperCase());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            HttpResponse response = client.execute(get);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(response.getEntity());

                System.out.println("response :" + s);
            } else {
                System.out.println(line.getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
    }


    /**
     * 获取产品
     */
    public static void get_product() {
        HttpClient client = HttpClientBuilder.create().build();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String timestamp = df.format(new Date());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sp_id", SP_ID);//渠道号
        params.put("timestamp", timestamp);

        HttpGet get = null;
        try {
            String sign = getSignature(params, SECURE_KEY);
            get = new HttpGet(URL+"/get_product?sp_id="+SP_ID+"&timestamp="+timestamp+"&sign="
                    + sign.toUpperCase());
            System.out.println(URL+"/get_product?sp_id="+SP_ID+"&timestamp="+timestamp+"&sign="
                    + sign.toUpperCase());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            HttpResponse response = client.execute(get);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(response.getEntity());

                String gb = new String(s.getBytes("ISO-8859-1"),"UTF-8");
                System.out.println("response :" + gb);
            } else {
                System.out.println(line.getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
    }


    /**
     * 获取库存
     */
    public static void get_preOrder() {
        HttpClient client = HttpClientBuilder.create().build();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sp_id", SP_ID);//渠道号
        params.put("timestamp",  df.format(new Date()));//系统当前时间戳YYYYMMDDHHMMSS

        HttpGet get = null;
        try {
            String sign = getSignature(params, SECURE_KEY);
            System.out.println(URL+"/get_preOrder?sp_id="+SP_ID
                    + "&timestamp="+df.format(new Date())+"&sign=" + sign.toUpperCase());
            get = new HttpGet(URL+"/get_preOrder?sp_id="+SP_ID
                    + "&timestamp="+df.format(new Date())+"&sign=" + sign.toUpperCase());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            HttpResponse response = client.execute(get);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(response.getEntity());
                String gb = new String(s.getBytes("ISO-8859-1"),"UTF-8");
                System.out.println("response :" + gb);
            } else {
                System.out.println(line.getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
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
}
