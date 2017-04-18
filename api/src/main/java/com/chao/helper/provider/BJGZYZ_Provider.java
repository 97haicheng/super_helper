package com.chao.helper.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.key.BJGZYZ_AES;
import com.chao.helper.utils.CRequest;
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
 * 北京格致益正技术有限责任公司上游供应商对接
 * US:800212
 * 密钥:220@#abx7ybk1227
 * 合作服务平台
 * 地址: http://www.jjliuliang.cn/selluser/loginpage.html
 * 帐号:18640217891
 * 密码:12345678

 */
public class BJGZYZ_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(BJGZYZ_Provider.class);

    @Override
    public String getProviderKey() {
        return "BJGZYZ";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        DateFormat dfh = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        HttpPost post = new HttpPost(url);

        String us = String.valueOf(params.get("us"));
        String thirdSeq = String.valueOf(params.get("thirdSeq"));
        String ts = dfh.format(new Date());
        String phone = String.valueOf(params.get("phone"));
        String flow = String.valueOf(params.get("flow"));
        String type = String.valueOf(params.get("type"));
        String notify = String.valueOf(params.get("notify"));
        String pass = String.valueOf(params.get("pass"));
        String key = String.valueOf(params.get("key"));

        String sig = BJGZYZ_AES.MD5(us + "||" + phone + "||" + flow + "||" + type + "||" + ts+ "||"+key, "UTF-8");

        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("phone", phone);
        parmMap.put("flow", flow);
        parmMap.put("type", type);
        parmMap.put("notify", notify);
        parmMap.put("pass", pass);

        String parm = CRequest.getUrlParamsByMap(parmMap);

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("us", us);
        try {
            paramsMap.put("parm", BJGZYZ_AES.encrypt(parm,key,key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        paramsMap.put("ts", ts);
        paramsMap.put("sig", sig);
        paramsMap.put("thirdSeq", thirdSeq);

        String json = JacksonUtil.toJSon(paramsMap);

        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "北京格致益正",
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

            logger.info("providerName: {}, type : {}, result : {}", "北京格致益正",
                    "processResponse", result);

        } catch (IOException e) {
            throw new HelperException(e);
        }
        return result;
    }

    private static final String BJGZYZ_URL = "http://unify.jjliuliang.com:8099/unify-api/flowOrderT";
    private static final String BJGZYZ_SP_ID = "800212";
    private static final String BJGZYZ_SECURE_KEY = "220@#abx7ybk1227";
    private static final String BJGZYZ_CALL_URL = "http://218.60.136.202:8020/api/b2b/call_back/bjgzyz";

    public static String bjgzyz(String mobile, String flow, String type){

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        BJGZYZ_Provider provider = new BJGZYZ_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("us", BJGZYZ_SP_ID);
        params.put("thirdSeq", df.format(new Date()));
        params.put("phone", mobile);
        params.put("flow", flow);
        params.put("type", type);//漫游属性（全国为1，本地为2）
        params.put("notify", BJGZYZ_CALL_URL);
        params.put("pass", "chao");
        params.put("key",BJGZYZ_SECURE_KEY);

        return provider.orderFor(params,BJGZYZ_URL);
    }

    public static void main(String[] args) {


        String mobile = "15864656465";//用户手机号
        System.out.println(BJGZYZ_Provider.bjgzyz(mobile, "10", "1"));
    }
}
