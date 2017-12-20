package com.chao.helper.provider.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.HttpProvider;
import com.chao.helper.provider.key.SDLT_AES;
import com.chao.helper.utils.JacksonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by think on 2016/12/26.
 *
 * 山东联通（视讯物联）
 * 工号：
 * 100060001
 * 密码：
 * 111111
 *
 * 1265171517918656
 */
public class SDLT_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(SDLT_Provider.class);

    @Override
    public String getProviderKey() {
        return "SDLT";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPost post = new HttpPost(url);

        String custId = (String) params.get("custId");
        String shopProductId = (String) params.get("shopProductId");
        String telPhone = (String) params.get("telPhone");
        String requestNo = (String) params.get("requestNo");
        String key = (String) params.get("SECURE_KEY");

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("custId", custId);
        jsonMap.put("shopProductId", shopProductId);
        jsonMap.put("telPhone", telPhone);
        jsonMap.put("requestNo",requestNo);

        String json = JacksonUtil.toJSon(jsonMap);

        String str = SDLT_AES.aesEncrypt(json, key);

        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "山东联通",
                "createRequest", json.toString(), url);

        StringEntity entity = null;
        try {
            entity = new StringEntity(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        post.addHeader("custId", custId);
        post.addHeader("timestamp", df.format(new Date()));
        post.addHeader("datatype", "json");
        post.addHeader("content-Type", "text/plain");
        post.setEntity(entity);
        return  post;
    }

    protected String processResponse(CloseableHttpResponse response) {
        HttpEntity entityRsp = response.getEntity();
        String result  = null;
        try {
            result = EntityUtils.toString(entityRsp);

            logger.info("providerName: {}, type : {}, result : {}", "山东联通",
                    "processResponse", result);

        } catch (IOException e) {
            throw new HelperException(e);
        }
        return result;
    }

    private static final String SDLT_URL = "http://106.14.20.229:8098/DataWallet/service/api/recharge/rechargeByShopId";
    private static final String SDLT_SP_ID = "100060001";
    private static final String SDLT_SECURE_KEY = "1265171517918656";

    public static String sdlt(String mobile, String product_id){

        SDLT_Provider provider = new SDLT_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("telPhone", mobile);
        params.put("custId",SDLT_SP_ID);
        params.put("shopProductId",product_id);
        params.put("requestNo","111111");
        params.put("SECURE_KEY",SDLT_SECURE_KEY);

        return provider.orderFor(params,SDLT_URL);
    }

    public static void main(String[] args) {

        String mobile = "15840043215";//用户手机号
        System.out.println(SDLT_Provider.sdlt(mobile, "10003221"));

    }
}
