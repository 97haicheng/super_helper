package com.chao.helper.provider.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.HttpProvider;
import com.chao.helper.utils.EncryptUtils;
import com.chao.helper.utils.JacksonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by think on 2017/1/10.
 *
 * 流量银行上游供应商对接
 */
public class LLYH_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(LLYH_Provider.class);

    private static final String SUCCESS = "0";

    private static final String SUCCESSCODE = "0000";

    @Override
    public String getProviderKey() {
        return "LLYH";
    }

    @Override
    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPost post = new HttpPost(url);

        // 设置参数
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = df.format(new Date());

        String phonenum = (String) params.get("phonenum");//注册手机号
        String pkgNo = (String) params.get("pkgNo");//流量包编码，由后续商务提供流量包编码列表。
        String orderid = timeStamp+String.valueOf(params.get("orderid"));//企业包充值订单id，必须唯一
        String appid = (String) params.get("appid");//与服务器约定企业编码
        String version =  (String) params.get("version");//接口的版本

        String appkey = (String) params.get("appkey");
        String appsecret = (String) params.get("appsecret");
        String sign = EncryptUtils.encodeMD5String(appkey+"appid"+appid+"orderid"+orderid+"phonenum"+phonenum+"pkgNo"+pkgNo+"timestamp"+timeStamp+"version"+version+appsecret);//签名



        //创建参数列表
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("phonenum", phonenum));
        list.add(new BasicNameValuePair("pkgNo", pkgNo));
        list.add(new BasicNameValuePair("orderid", orderid));
        list.add(new BasicNameValuePair("appid", appid));
        list.add(new BasicNameValuePair("version", version));
        list.add(new BasicNameValuePair("timestamp", timeStamp));
        list.add(new BasicNameValuePair("sign", sign));

        logger.info("LLYH_Provider request : {}", "phonenum="+phonenum+"&pkgNo="+pkgNo+"&orderid="+orderid+"&appid="+appid+"&version="+version+"&timestamp="+timeStamp+"&sign="+sign);

        String data = URLEncodedUtils.format(list, "UTF-8");

        HttpGet httpGet = new HttpGet(url + "?" + data);
        httpGet.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());

        return httpGet;
    }

    @Override
    protected String processResponse(CloseableHttpResponse response) {
        try {
            HttpEntity entityRsp = response.getEntity();

            String result = EntityUtils.toString(entityRsp);

            logger.info("LLYH_Provider response : {}", result);

            Map map = JacksonUtil.fromJson(result, Map.class);

            if(SUCCESSCODE.equals(map.get("code"))){
                Map mapSub = (Map) map.get("data");

                String code = String.valueOf(mapSub.get("state"));

                logger.info("type : {}, json : {}, code : {}", "LLYHProviderProcessResponse",
                        result, code);
                if (SUCCESS.equals(code)) {
                    return SUCCESSCODE;
                } else {
                    return code;
                }
            }else {
                logger.info("type : {}, json : {}, code : {}", "LLYHProviderProcessResponse",
                        result, (String) map.get("code"));
                return (String) map.get("code");
            }
        } catch (Exception e) {
            throw new HelperException(e);
        }
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
        return fixLenthString.substring(1, strLength + 1);
    }

    private static final String LLYH_URL = "http://admin.sinobyte.cn/ecif/FlowCharge";
    private static final String LLYH_SP_ID = "bjyinbeikekjyxgs_llyh_01";
    private static final String LLYH_SECURE_KEY = "fas43eda0";
    private static final String LLYH_APP_KEY = "bjybkkjyxgs_key_llyh";

    public static String llyh(String mobile, String pkgNo){

        LLYH_Provider provider = new LLYH_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("phonenum", mobile);
        params.put("pkgNo", pkgNo);
        params.put("orderid", getFixLenthString(6));
        params.put("appid", LLYH_SP_ID);
        params.put("version", "1.0");
        params.put("timestamp", "");
        params.put("appkey", LLYH_APP_KEY);
        params.put("appsecret",LLYH_SECURE_KEY);

        return provider.orderFor(params,LLYH_URL);
    }

    public static void main(String[] args) {

        String mobile = "18624308866";
        System.out.println(LLYH_Provider.llyh(mobile, "003000"));

    }

}
