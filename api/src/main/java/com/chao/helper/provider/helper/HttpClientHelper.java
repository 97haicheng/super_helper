package com.chao.helper.provider.helper;

import com.chao.helper.utils.DigestUtils;
import com.chao.helper.utils.JacksonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 订购接口调用
 */
public class HttpClientHelper {

    private static final String SECURE_KEY = "QQQQQCCCCCCCCCCCCCCCCCCCCCCCCCCC";
    private static final String HOST = "http://218.60.136.202:8020";
    private static final String SP_ID = "QQ42692108000000";

    private static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

    private static HttpClient httpClient;

    private static HttpClientHelper instance = new HttpClientHelper();

    private HttpClientHelper() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        connectionManager.setMaxTotal(1000);
        connectionManager.setDefaultMaxPerRoute(1000);
        httpClientBuilder.setConnectionManager(connectionManager);
        httpClient = httpClientBuilder.build();
    }

    public static HttpClientHelper getInstance() {
        return instance;
    }

    public static void sendRequest(HttpRequestBase request) {

        try {
            HttpResponse response = httpClient.execute(request);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {

            } else {
                System.out.println(line.getStatusCode());
            }

            System.out.println("response :" + EntityUtils.toString(response.getEntity(), "UTF-8"));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.releaseConnection();
        }
    }

    public void testPostString(String str) {
        HttpPost post = new HttpPost(HOST + "api/b2b/v1.0/order_create");

        post.setEntity(new StringEntity(str, ContentType.APPLICATION_JSON));

        sendRequest(post);
    }

    public static void main(String[] args) {

        HttpPost post = new HttpPost(HOST + "/api/b2b/v1.0/order_create");

        String[] mobileNumber = {"15840043215"};
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("timestamp", getTimestamp());
        params.put("mobile_number", JacksonUtil.toJSon(mobileNumber));
        params.put("sp_product_id", "335");
        params.put("sp_id", SP_ID);
        params.put("order_id", System.currentTimeMillis());

        try {
            String sign = DigestUtils.getSignature(params, SECURE_KEY);
            params.put("sign", sign.toUpperCase());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String json = JacksonUtil.toJSon(params);
        System.out.println("request : " + json);
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);

        post.setEntity(entity);

        sendRequest(post);
    }

    private static String getJsonString(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        for (Entry<String, Object> entry : params.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\"").append(":");

            if (entry.getValue() instanceof String) {
                sb.append("\"").append(entry.getValue()).append("\"");
            } else if (entry.getValue() instanceof String[]) {
                String[] arr = (String[]) entry.getValue();
                sb.append("[");
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        sb.append(",");
                    } else {
                        sb.append("\"").append(arr[i]).append("\"").append(",");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("]");
            }
            sb.append(",");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }

    private String getQueryString(Map<String, Object> params) {
        StringBuilder queryStr = new StringBuilder();

        for (Entry<String, Object> entry : params.entrySet()) {
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
            String sign = DigestUtils.getSignature(params, SECURE_KEY);

            queryStr.append("sign=").append(sign.toUpperCase());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return queryStr.toString();
    }

    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
