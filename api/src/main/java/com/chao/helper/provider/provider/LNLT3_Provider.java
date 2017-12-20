package com.chao.helper.provider.provider;

import com.chao.helper.enumeration.ProductsCode;
import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.HttpProvider;
import com.chao.helper.provider.Provider;
import com.chao.helper.utils.EncryptUtils;
import com.chao.helper.utils.JacksonUtil;
import com.chao.helper.utils.RandomUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by think on 2016/11/9.
 *
 * 辽宁联通后向(3.0)
 */
public class LNLT3_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(LNLT3_Provider.class);

    private String password;

    private static final String SUCCESS = "0000";

    @Override
    public String getProviderKey() {
        return "LNLT3";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPost post = new HttpPost(url);

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        String channelCode = (String) params.get("channelCode");
        String channelOrderId = (String) params.get("channelOrderId");
        String mobile = (String) params.get("mobile");
        String productId = (String) params.get("productId");
        String contractNum = (String) params.get("contractNum");
        String operaType = (String) params.get("operaType");
        String key = (String) params.get("key");
        String timeStamp = df.format(new Date());

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("channelCode", channelCode);
        paramsMap.put("transactionId", timeStamp+channelOrderId);
        paramsMap.put("timeStamp", timeStamp);

        Map<String, Object> messageMap = new HashMap<String, Object>();
        messageMap.put("channelOrderId", timeStamp+channelOrderId);
        messageMap.put("mobile", mobile);
        messageMap.put("productId",productId);
        messageMap.put("contractNum",contractNum);
        messageMap.put("operaType",operaType);

        String messageContent = Base64.encodeBase64URLSafeString(JacksonUtil.toJSon(messageMap).getBytes());
        paramsMap.put("messageContent", messageContent);

        String sign = EncryptUtils.encodeMD5String(channelCode+timeStamp+channelOrderId+timeStamp+messageContent+key);
        paramsMap.put("sign", sign);

        String json = JacksonUtil.toJSon(paramsMap);

        logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "辽宁联通3.0",
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
        post.setEntity(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        return  post;
    }

    protected String processResponse(CloseableHttpResponse response) {

        HttpEntity entityRsp = response.getEntity();
        String result  = null;
        try {
            result = EntityUtils.toString(entityRsp);

            logger.info("providerName: {}, type : {}, result : {}", "辽宁联通3.0",
                    "processResponse", result);

            JSONObject jsonObject = new JSONObject(result);
            String resultCode = jsonObject.get("resultCode").toString();

            if(SUCCESS.equals(resultCode)){
                String messageContent = new String(Base64.decodeBase64(jsonObject.get("messageContent").toString().getBytes()));
                JSONObject messageContentJsonObject = new JSONObject(messageContent);
                String channelOrderId = messageContentJsonObject.get("channelOrderId").toString();
                String mobile = messageContentJsonObject.get("mobile").toString();
                String network = messageContentJsonObject.get("network").toString();

                logger.info("providerName: {}, type : {}, result : {}", "辽宁联通3.0",
                        "processResponse", "channelOrderId = "+channelOrderId+" mobile = "+mobile+" network = "+network);
            }

        } catch (IOException e) {
            throw new HelperException(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ==================================================
     *
     * 辽宁联通3.0接口
     */
    private static final String LNLT3_URL = "http://api3.wo800.cn/lnbackward-api/v3/order";
    private static final String LNLT3_SP_ID = "F56EE6869E6E4C3AA0805F525CC58572";
    private static final String LNLT3_SECURE_KEY = "02882164";

    public static String lnlt3(String mobile, String productId){

        Provider provider = new LNLT3_Provider();
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("channelCode",LNLT3_SP_ID);
        params.put("channelOrderId", RandomUtil.getRandomString(8));
        params.put("mobile",mobile);
        params.put("productId",productId);
        params.put("contractNum","NO.84");
        params.put("operaType","1");
        params.put("key",LNLT3_SECURE_KEY);

        return provider.orderFor(params, LNLT3_URL);
//        return getMessage(params);
    }

    public static void main(String[] args) throws Exception {

        System.out.println(LNLT3_Provider.lnlt3("15640139415", ProductsCode.LNLTRHENUM.G20.getName()));

    }

    public static String getMessage(Map<String, Object> msgParam){
        String message = "请求信息为 : {0}";

        if (!msgParam.isEmpty()) {
            String temp = JacksonUtil.toJSon(msgParam);
            System.out.println(temp.substring(1,temp.length()-1).split(",")[0]);
            message = MessageFormat.format(message, temp);
        }
        return message;
    }
}
