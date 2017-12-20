package com.chao.helper.provider.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.HttpProvider;
import com.chao.helper.utils.EncryptUtils;
import com.chao.helper.utils.JacksonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by think on 2016/12/26.
 *
 * 南京蓝海-辽宁电信
 */
public class NJLH_LNDX_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(NJLH_LNDX_Provider.class);

    @Override
    public String getProviderKey() {
        return "NJLH";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpPost post = new HttpPost(url);

        String userid = (String) params.get("userid");
        String staffCode = (String) params.get("staffCode");
        String secur_key = (String) params.get("SECURE_KEY");

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userid", userid);
        paramsMap.put("staffCode", staffCode);
        paramsMap.put("pricePlanCd", params.get("pricePlanCd"));
        paramsMap.put("transactionId",params.get("transactionId"));
        paramsMap.put("reqTime", df.format(new Date()));

        paramsMap.put("Sign", EncryptUtils.Encrypt(EncryptUtils.encodeMD5String((userid+staffCode+secur_key).toUpperCase()),secur_key));

        String json = JacksonUtil.toJSon(paramsMap);

        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "南京蓝海",
                "createRequest", json.toString(), url);

        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        post.setEntity(entity);
        return  post;
    }

    protected String processResponse(CloseableHttpResponse response) {
        HttpEntity entityRsp = response.getEntity();
        String result  = null;
        try {
            result = EntityUtils.toString(entityRsp);

            logger.info("providerName: {}, type : {}, result : {}", "南京蓝海",
                    "processResponse", result);

        } catch (IOException e) {
            throw new HelperException(e);
        }
        return result;
    }

    /**
     * 返回长度为【strLength】的随机数，在前面补0
     */
    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(2, strLength + 2);
    }

    private static final String NJLH_URL = "http://lnflow.10006.info/yll/rechargeFlow";
    private static final String NJLH_SP_ID = "TEST";
    private static final String NJLH_SECURE_KEY = "1111111111111111";

    public static String njlh(String mobile, String product_id){

        String orderId = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date())+getFixLenthString(7);

        NJLH_LNDX_Provider provider = new NJLH_LNDX_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", mobile);
        params.put("staffCode",NJLH_SP_ID);
        params.put("pricePlanCd",product_id);
        params.put("transactionId",orderId);
        params.put("SECURE_KEY",NJLH_SECURE_KEY);

        return provider.orderFor(params,NJLH_URL);
    }

    public static void main(String[] args) {

        String mobile = "15840043215";//用户手机号
        System.out.println(NJLH_LNDX_Provider.njlh(mobile, "1"));

    }
}
