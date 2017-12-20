package com.chao.helper.provider.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.HttpSSLProvider;
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

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by think on 2017/1/9.
 *
 * 江西掌中无限上游供应商对接
 */
public class JXZZWX_Provider extends HttpSSLProvider {

    private static final Logger logger = LoggerFactory.getLogger(JXZZWX_Provider.class);

    private static final String SUCCESS = "000";

    private static final String SUCCESSCODE = "0000";

    @Override
    public String getProviderKey() {
        return "JXZZWX";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPost post = new HttpPost(url);

        // 设置参数
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = df.format(new Date());

        String service = (String) params.get("service");//流量充值：payment
        String companyno = (String) params.get("companyno");//企业ID
        String mobilephone = (String) params.get("mobilephone");//手机号
        String productcode = (String) params.get("productcode");//产品代码
        String tradeno = timeStamp+params.get("OrderId");//订单号
        String secretKey = (String) params.get("secretKey");//KEY
        String sign = EncryptUtils.encodeMD5String("companyno="+companyno+"&mobilephone="+mobilephone+"&productcode="+productcode+"&service="+service+"&tradeno="+tradeno+secretKey);//签名

        Map<String, Object> sendParams = new HashMap<>();
        sendParams.put("service", service);
        sendParams.put("companyno", companyno);
        sendParams.put("mobilephone", mobilephone);
        sendParams.put("productcode", productcode);
        sendParams.put("tradeno", tradeno);
        sendParams.put("sign", sign);
        sendParams.put("sign_type", "MD5");

        //创建参数列表
        List<NameValuePair> pairs = null;
        if (sendParams != null && !sendParams.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(sendParams.size());
            for (Map.Entry<String, Object> entry : sendParams.entrySet()) {
                String value = (String) entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }

        logger.info("JXZZWX_Provider request : {}", JacksonUtil.toJSon(pairs));

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

    @Override
    protected String processResponse(CloseableHttpResponse response) {
        try {
            HttpEntity entityRsp = response.getEntity();

            String result = EntityUtils.toString(entityRsp);

            logger.info("JXZZWX_Provider response : {}", result);

            logger.info("providerName: {}, type : {}, result : {}", "江西掌中无限",
                    "processResponse", result);

            return result;
        } catch (Exception e) {
            throw new HelperException(e);
        }
    }


    private static final String JXZZWX_URL = "https://inf.mewifi.cn/fdp/openAPIAction_payment";
    private static final String JXZZWX_SP_ID = "bjybk";
    private static final String JXZZWX_SECURE_KEY = "RhMr3WdYYx0twU3Lqz8A8eAPge2yyKArIRX4masR";

    public static String jxzzwx(String mobile, String productcode){

        JXZZWX_Provider provider = new JXZZWX_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyno", JXZZWX_SP_ID);
        params.put("service", "payment");
        params.put("productcode", productcode);
        params.put("mobilephone", mobile);
        params.put("OrderId", "1");
        params.put("secretKey", JXZZWX_SECURE_KEY);

        return provider.orderFor(params,JXZZWX_URL);
    }

    public static void main(String[] args) {

        String mobile = "13177590452";//用户手机号 江西联通（15607917527）
        System.out.println(JXZZWX_Provider.jxzzwx(mobile, "F0010"));

    }

}
