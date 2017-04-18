package com.chao.helper.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.utils.CRequest;
import com.chao.helper.utils.EncryptUtils;
import com.chao.helper.utils.JacksonUtil;
import com.chao.helper.utils.RandomUtil;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by think on 2016/11/9.
 *
 * 时科流量平台
 */
public class SKLLPT_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(SKLLPT_Provider.class);

    @Override
    public String getProviderKey() {
        return "SKLLPT";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        HttpPost post = new HttpPost(url);

        String cpno = (String) params.get("cpno");
        String ts = df.format(new Date());
        String service = (String) params.get("service");
        String productno = (String) params.get("productno");
        String mobileno = (String) params.get("mobileno");
        String cporderno = (String) params.get("cporderno");
        String key = (String) params.get("SECURE_KEY");

        Map<String, Object> paramJson = new HashMap<>();
        paramJson.put("productno", productno);
        paramJson.put("mobileno", mobileno);
        paramJson.put("cporderno", cporderno);
        String strParamJson = JacksonUtil.toJSon(paramJson);

        String hash = EncryptUtils.encodeMD5String(cpno+key+ts+service+strParamJson).toUpperCase();

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cpno", cpno);
        paramsMap.put("hash", hash);
        paramsMap.put("ts", ts);
        paramsMap.put("service", service);
        paramsMap.put("params", strParamJson);

        //创建参数列表
        List<NameValuePair> pairs = null;
        if (paramsMap != null && !paramsMap.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(paramsMap.size());
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
                String value = (String) entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }

        String json = JacksonUtil.toJSon(paramsMap);
        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                "createRequest", json.toString(), url);

        //url格式编码
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(pairs,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        post.setEntity(entity);

        return post;

    }

    protected String processResponse(CloseableHttpResponse response) {
        HttpEntity entityRsp = response.getEntity();
        String result  = null;
        try {
            result = EntityUtils.toString(entityRsp);

            logger.info("providerName: {}, type : {}, result : {}", "时科流量平台",
                    "processResponse", result);

        } catch (IOException e) {
            throw new HelperException(e);
        }
        return result;
    }

    private static final String SKLLPT_URL = "http://211.151.183.169:8085/services";
    private static final String SKLLPT_SP_ID = "XY063";
    private static final String SKLLPT_SECURE_KEY = "d9e1d2b3-631f-4147-9f56-da4fbef71e70";

    public static String skllpt(String mobile, String product_id){

        String orderId = UUID.randomUUID().toString().replace("-", "");

        SKLLPT_Provider provider = new SKLLPT_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cpno", SKLLPT_SP_ID);
        params.put("service", "rechargeFlux");
        params.put("productno", product_id);
        params.put("mobileno", mobile);
        params.put("cporderno", RandomUtil.getFixLenthString(6));

        params.put("SECURE_KEY",SKLLPT_SECURE_KEY);

        return provider.orderFor(params,SKLLPT_URL);
    }

    public static void main(String[] args) {

        String mobile = "15840043215";//用户手机号
//        System.out.println(SKLLPT_Provider.skllpt(mobile, ProductsCode.SKENUM.SKYD10.getName()));
        queryStatus("096012");
        queryBalance();
        queryProducts();

    }

    /**
     * 订单查询接口
     */
    public static void queryStatus(String cporderno) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(SKLLPT_URL);

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String ts = df.format(new Date());

        Map<String, Object> paramJson = new HashMap<>();
        paramJson.put("cporderno", cporderno);
        String strParamJson = JacksonUtil.toJSon(paramJson);

        String hash = EncryptUtils.encodeMD5String(SKLLPT_SP_ID+SKLLPT_SECURE_KEY+ts+"queryStatus"+strParamJson).toUpperCase();

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cpno", SKLLPT_SP_ID);
        paramsMap.put("hash", hash);
        paramsMap.put("ts", ts);
        paramsMap.put("service", "queryStatus");
        paramsMap.put("params", strParamJson);

        //创建参数列表
        List<NameValuePair> pairs = null;
        if (paramsMap != null && !paramsMap.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(paramsMap.size());
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
                String value = (String) entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }

        String json = JacksonUtil.toJSon(paramsMap);
        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                "createRequest", json.toString(), SKLLPT_URL);

        //url格式编码
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(pairs,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        post.setEntity(entity);

        try {
            HttpResponse response = client.execute(post);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(response.getEntity());

                logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                        "response", s, SKLLPT_URL);

            } else {
                logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                        "response", line.getStatusCode(), SKLLPT_URL);
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
     * 商户余额查询接口
     */
    public static void queryBalance() {
        HttpClient client = HttpClientBuilder.create().build();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String ts = df.format(new Date());

        String hash = EncryptUtils.encodeMD5String(SKLLPT_SP_ID+SKLLPT_SECURE_KEY+ts+"queryBalance").toUpperCase();

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cpno", SKLLPT_SP_ID);
        paramsMap.put("hash", hash);
        paramsMap.put("ts", ts);
        paramsMap.put("service", "queryBalance");
        paramsMap.put("params", "");

        HttpGet get = null;
        get = new HttpGet(SKLLPT_URL+"?"+ CRequest.getUrlParamsByMap(paramsMap));
        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                "createRequest", CRequest.getUrlParamsByMap(paramsMap), SKLLPT_URL);

        try {
            HttpResponse response = client.execute(get);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(response.getEntity());

                String gb = new String(s.getBytes("ISO-8859-1"),"UTF-8");

                logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                        "response", gb, SKLLPT_URL);

            } else {
                logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                        "response", line.getStatusCode(), SKLLPT_URL);
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
     * 已开通流量产品查询接口
     */
    public static void queryProducts() {
        HttpClient client = HttpClientBuilder.create().build();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String ts = df.format(new Date());

        String hash = EncryptUtils.encodeMD5String(SKLLPT_SP_ID+SKLLPT_SECURE_KEY+ts+"queryProducts").toUpperCase();

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cpno", SKLLPT_SP_ID);
        paramsMap.put("hash", hash);
        paramsMap.put("ts", ts);
        paramsMap.put("service", "queryProducts");
        paramsMap.put("params", "");

        HttpGet get = null;
        get = new HttpGet(SKLLPT_URL+"?"+ CRequest.getUrlParamsByMap(paramsMap));
        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                "createRequest", CRequest.getUrlParamsByMap(paramsMap), SKLLPT_URL);

        try {
            HttpResponse response = client.execute(get);

            StatusLine line = response.getStatusLine();

            if (line.getStatusCode() == HttpStatus.SC_OK) {
                String s = EntityUtils.toString(response.getEntity());

                logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                        "response", s, SKLLPT_URL);

            } else {
                logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "时科流量平台",
                        "response", line.getStatusCode(), SKLLPT_URL);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
    }
}
