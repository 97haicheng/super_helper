package com.chao.helper.provider;

import com.chao.helper.exception.HelperException;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by think on 2016/11/22.
 *
 * 社会化企业合作流量直充(电信150M)
 */
public class SHHQYHZLLZC_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(SHHQYHZLLZC_Provider.class);

    @Override
    public String getProviderKey() {
        return "SHHQYHZLLZC";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPost post = new HttpPost(url);
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        String request_no = "ybk_no"+"123456";
        String openid = (String) params.get("openid");
        String phone = (String) params.get("phone");
        String scene_id = (String) params.get("scene_id");
        String key = (String) params.get("SECURE_KEY");
        String sign = EncryptUtils.encodeMD5String(key+ openid + phone+ request_no+ scene_id+key);

        paramsMap.put("request_no",request_no);
        paramsMap.put("openid", openid);
        paramsMap.put("phone", phone);
        paramsMap.put("scene_id",scene_id);
        paramsMap.put("sign",sign);
        String json = JacksonUtil.toJSon(paramsMap);

        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "社会化企业合作流量直充(电信150M)",
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

            logger.info("providerName: {}, type : {}, result : {}", "社会化企业合作流量直充(电信150M)",
                    "processResponse", result);

        } catch (IOException e) {
            throw new HelperException(e);
        }
        return result;
    }

    private static final String SHHQYHZLLZC_URL = "http://kf.bihoo.com/qyfecf/api/llzcforshhqd.php";
    private static final String SHHQYHZLLZC_SP_ID = "99924";
    private static final String SHHQYHZLLZC_SECURE_KEY = "zgdxkfllzc2016";

    public static String shhqyhzllzc(String mobile){

        SHHQYHZLLZC_Provider provider = new SHHQYHZLLZC_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", mobile);
        params.put("phone",mobile);
        params.put("scene_id",SHHQYHZLLZC_SP_ID);
        params.put("SECURE_KEY",SHHQYHZLLZC_SECURE_KEY);

        return provider.orderFor(params,SHHQYHZLLZC_URL);
    }

    public static void main(String[] args) {

//        String mobile = "13390585192";//用户手机号
        String mobile = "15840043215";//用户手机号
        try {
            System.out.println(URLDecoder.decode(SHHQYHZLLZC_Provider.shhqyhzllzc(mobile), "UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        System.out.println(URLDecoder.decode("\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u5019\u91cd\u8bd5\uff01"));
    }
}
