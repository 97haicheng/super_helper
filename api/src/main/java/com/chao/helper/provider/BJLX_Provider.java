package com.chao.helper.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.utils.EncryptUtils;
import com.chao.helper.utils.JacksonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
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
 * 北京蓝汛通信技术有限责任公司上游供应商对接
 * appId:bjybke   activityNo:bjybke_0328    key:yinbeike123456  url:http://58.67.207.53/cp/order
 * 可以测试电信5M 移动：10M 联通：20M测试环境只调试接口不充值
 */
public class BJLX_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(BJLX_Provider.class);

    @Override
    public String getProviderKey() {
        return "BJLX";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        HttpPost post = new HttpPost(url);

        String appId = String.valueOf(params.get("appId"));
        String activityNo = String.valueOf(params.get("activityNo"));
        String request_no = String.valueOf(params.get("request_no"));
        String timestamp = df.format(new Date());
        String flow_spec = String.valueOf(params.get("flow_spec"));
        String operator = String.valueOf(params.get("operator"));
        String effect_type = String.valueOf(params.get("effect_type"));
        String telephone = String.valueOf(params.get("telephone"));
        String key = String.valueOf(params.get("key"));

        Map<String, Object> order = new HashMap<String, Object>();
        order.put("effect_type", effect_type);
        order.put("flow_spec", flow_spec);
        order.put("operator", operator);
        order.put("telephone", telephone);
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedOrder = new TreeMap<String, Object>(order);

        Map<String, Object> sortRequestData = new HashMap<String, Object>();
        sortRequestData.put("request_no", request_no);
        sortRequestData.put("timestamp", timestamp);
        sortRequestData.put("order", sortedOrder);
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedRequestData = new TreeMap<String, Object>(sortRequestData);
        String jsonRequestData = JacksonUtil.toJSon(sortedRequestData);

        String requestDataStr = EncryptUtils.encodeBase64String(jsonRequestData).replaceAll("\r|\n", "");
        String sign = EncryptUtils.encodeMD5String(EncryptUtils.encodeBase64String(key + jsonRequestData + key).replaceAll("\r|\n", ""));

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("appId", appId);
        paramsMap.put("activityNo", activityNo);
        paramsMap.put("requestData", requestDataStr);
        paramsMap.put("sign", sign);

        String json = JacksonUtil.toJSon(paramsMap);

        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "北京蓝汛",
                "createRequest", json.toString(), url);

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
        //url格式编码
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(pairs,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(entity);
        return  post;
    }

    protected String processResponse(CloseableHttpResponse response) {
        HttpEntity entityRsp = response.getEntity();
        String result  = null;
        try {
            result = EntityUtils.toString(entityRsp);

            logger.info("providerName: {}, type : {}, result : {}", "北京蓝汛",
                    "processResponse", result);

        } catch (IOException e) {
            throw new HelperException(e);
        }
        return result;
    }

    private static final String BJLX_URL = "http://58.67.207.53/cp/order";
    private static final String BJLX_SP_ID = "bjybke";
    private static final String BJLX_SECURE_KEY = "yinbeike123456";
    private static final String BJLX_NO = "bjybke_0328";

    public static String bjlx(String mobile, String flow_spec, String operator){

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        BJLX_Provider provider = new BJLX_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", BJLX_SP_ID);
        params.put("activityNo", BJLX_NO);
        params.put("mobile", mobile);
        params.put("request_no",df.format(new Date()));
        params.put("flow_spec",flow_spec);
        params.put("operator",operator);
        params.put("effect_type",0);
        params.put("telephone", mobile);
        params.put("key",BJLX_SECURE_KEY);

        return provider.orderFor(params,BJLX_URL);
    }

    public static void main(String[] args) {


        String mobile = "15241888487";//用户手机号
        System.out.println(BJLX_Provider.bjlx(mobile, "10", "CMN"));
    }
}
