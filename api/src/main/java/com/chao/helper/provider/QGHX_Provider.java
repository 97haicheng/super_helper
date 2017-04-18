package com.chao.helper.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.utils.DigestUtils;
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
import java.util.UUID;

/**
 * Created by think on 2016/11/9.
 *
 * 全国后向
 */
public class QGHX_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(QGHX_Provider.class);

    @Override
    public String getProviderKey() {
        return "QGHX";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        HttpPost post = new HttpPost(url);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("timestamp", df.format(new Date()));
        paramsMap.put("mobile_number", params.get("mobiles"));
        paramsMap.put("sp_product_id", params.get("sp_product_id"));
        paramsMap.put("sp_id", params.get("sp_id"));
        paramsMap.put("order_id",params.get("order_id"));

        try {
            String sign = DigestUtils.getSignature(paramsMap, (String) params.get("SECURE_KEY"));
            paramsMap.put("sign", sign.toUpperCase());
        } catch (IOException e) {
            throw new HelperException(e);
        }
        String json = JacksonUtil.toJSon(paramsMap);

        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "全国后向",
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

            logger.info("providerName: {}, type : {}, result : {}", "全国后向",
                    "processResponse", result);

        } catch (IOException e) {
            throw new HelperException(e);
        }
        return result;
    }

    //private static final String QGHX_URL = "http://api.liu-bei.cn/api/b2b/v1.0/order_create";
    private static final String QGHX_URL = "http://218.60.136.202:8020/api/b2b/v1.0/order_create";
//    private static final String QGHX_URL = "http://127.0.0.1:8080/api/b2b/v1.0/order_create";
    private static final String QGHX_SP_ID = "QQ42692108000000";
    private static final String QGHX_SECURE_KEY = "QQQQQCCCCCCCCCCCCCCCCCCCCCCCCCCC";

    public static String qghx(String[] mobiles, String product_id){

        String orderId = UUID.randomUUID().toString().replace("-", "");

        QGHX_Provider provider = new QGHX_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mobiles", JacksonUtil.toJSon(mobiles));
        params.put("sp_product_id",product_id);
        params.put("sp_id",QGHX_SP_ID);
        params.put("SECURE_KEY",QGHX_SECURE_KEY);
        params.put("order_id",orderId);

//        return provider.orderFor(params,QGHX_URL);
        return provider.toJson(params,QGHX_URL);
    }

    public static String toJson(Map<String, Object> params, String url){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        HttpPost post = new HttpPost(url);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("timestamp", df.format(new Date()));
        paramsMap.put("mobile_number", params.get("mobiles"));
        paramsMap.put("sp_product_id", params.get("sp_product_id"));
        paramsMap.put("sp_id", params.get("sp_id"));
        paramsMap.put("order_id",params.get("order_id"));

        try {
            String sign = DigestUtils.getSignature(paramsMap, (String) params.get("SECURE_KEY"));
            paramsMap.put("sign", sign.toUpperCase());
        } catch (IOException e) {
            throw new HelperException(e);
        }
        String json = JacksonUtil.toJSon(paramsMap);

        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "全国后向",
                "createRequest", json.toString(), url);
        return json;
    }

    public static void main(String[] args) {

//        String[] mobiles = {"13124240997","13124240997"};//用户手机号
//        String[] mobiles = {"15840043215  "};//用户手机号
//        System.out.println(QGHX_Provider.qghx(mobiles, ProductsCode.QGHXENUM.QGHXTEST112.getName()));

        String[] mobiles = {"15840043215"};//用户手机号
        System.out.println(QGHX_Provider.qghx(mobiles, "521"));//13342493122
    }
}
