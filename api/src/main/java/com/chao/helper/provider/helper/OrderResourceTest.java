package com.chao.helper.provider.helper;

import com.chao.helper.utils.DigestUtils;
import com.chao.helper.utils.JacksonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by barry on 15/11/4.
 * 接口调用
 */
public class OrderResourceTest {

    private HttpClientHelper httpClient = HttpClientHelper.getInstance();

    private static final String CREATE_ORDER_URL = "/api/b2b/v1.0/order_create";
    private static final String GET_ORDER_URL = "/api/b2b/v1.0/get_order";
    private static final String GET_GOODS_URL = "/api/b2b/v1.0/get_product";

    private static final String SECURE_KEY = "QQQQQCCCCCCCCCCCCCCCCCCCCCCCCCCC";
    private static final String HOST = "http://218.60.136.202:8020";
    private static final String SP_ID = "QQ42692108000000";
    private static final String PRODUCT_ID = "335";

    public void testErrorCreateOrder(String host, String secureKey) {

        HttpPost post = new HttpPost(host + CREATE_ORDER_URL);

        String[] mobileNumber = {"15840043215", "15840043215"};
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("timestamp", getTimestamp());
        params.put("mobile_number", JacksonUtil.toJSon(mobileNumber));
        params.put("sp_product_id", PRODUCT_ID);
        params.put("sp_id", SP_ID);
        params.put("order_id", getOrderId());

        try {
            String sign = DigestUtils.getSignature(params, secureKey);
            params.put("sign", sign.toUpperCase());

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            parameters.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue() + ""));
        }

        HttpEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(parameters);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        post.setEntity(entity);

        httpClient.sendRequest(post);
    }

    public void testCreateOrder(String host, String secureKey, String orderId) {

        HttpPost post = new HttpPost(host + CREATE_ORDER_URL);

        String[] mobileNumber = {"15840043215", "15840043215"};
        Map<String, Object> params = new HashMap<>();
        params.put("timestamp", getTimestamp());
        params.put("mobile_number", JacksonUtil.toJSon(mobileNumber));
        params.put("sp_product_id", PRODUCT_ID);
        params.put("sp_id", SP_ID);
        params.put("order_id", orderId);

        try {
            String sign = DigestUtils.getSignature(params, secureKey);
            params.put("sign", sign.toUpperCase());

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String json = JacksonUtil.toJSon(params);
        System.out.println("request : " + json);
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);

        post.setEntity(entity);

        httpClient.sendRequest(post);
    }

    public void testGetOrders(String host, String secureKey, String[] orderIds) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("order_ids", JacksonUtil.toJSon(orderIds));
        params.put("sp_id", SP_ID);
        params.put("timestamp", getTimestamp());

        httpClient.sendRequest(new HttpGet(host + GET_ORDER_URL + "?"
                + getQueryString(params, secureKey)));
    }

    public void testGetGoodses(String host, String secureKey){
        Map<String, Object> params = new HashMap<>();
        params.put("sp_id", SP_ID);
        params.put("timestamp", getTimestamp());

        httpClient.sendRequest(new HttpGet(host + GET_GOODS_URL + "?"
                + getQueryString(params, secureKey)));
    }

    public static String getOrderId() {
        return "O_" + getTimestamp() + "_" + Thread.currentThread().getId();
    }

    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    private String getQueryString(Map<String, Object> params, String secureKey) {
        StringBuilder queryStr = new StringBuilder();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                queryStr.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue() + "",
                                "utf-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            String sign = DigestUtils.getSignature(params, secureKey);

            queryStr.append("sign=").append(sign.toUpperCase());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return queryStr.toString();
    }

    public static void main(String args[]) {
        OrderResourceTest test = new OrderResourceTest();

        String host = HOST;

        String orderId = getOrderId();

        test.testCreateOrder(host, SECURE_KEY, orderId);

        test.testGetOrders(host, SECURE_KEY, new String[]{orderId});

        test.testGetGoodses(host, SECURE_KEY);

    }
}
