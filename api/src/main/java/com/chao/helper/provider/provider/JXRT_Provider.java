package com.chao.helper.provider.provider;

import com.chao.helper.exception.HelperException;
import com.chao.helper.provider.HttpProvider;
import com.chao.helper.utils.EncryptUtils;
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
 * 金信瑞通上游供应商对接
 */
public class JXRT_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(JXRT_Provider.class);

    private static final String SUCCESS = "000";

    private static final String SUCCESSCODE = "0000";

    @Override
    public String getProviderKey() {
        return "JXRT";
    }

    @Override
    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPost post = new HttpPost(url);

        // 设置参数
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = df.format(new Date());

        String account = (String) params.get("account");//帐号
        String password = EncryptUtils.Md5_16((String) params.get("password"));//登陆密码16位md5加密后字符串（加密后签名）
        String mobile = (String) params.get("mobile");//充值号码
        String packageSize = (String) params.get("packageSize");//流量包大小,请按照获取流量包接口返回的包对应商品。输入单位是:M)，例如30M就是30,1G为1024,2G为2048
        String outTradeNo = timeStamp+params.get("OrderId");//订单号
        String expdate = (String) params.get("expdate");//订单号
        String range = (String) params.get("range");//订单号
        String apiKey = (String) params.get("apiKey");//订单号
        String sign = EncryptUtils.encodeMD5String("account="+account+"&expdate="+expdate+"&mobile="+mobile+"&outTradeNo="+outTradeNo+"&packageSize="+packageSize+"&password="+password+"&range="+range+"&apiKey="+apiKey);//签名

        //创建参数列表
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("account", account));
        list.add(new BasicNameValuePair("password", password));
        list.add(new BasicNameValuePair("mobile", mobile));
        list.add(new BasicNameValuePair("packageSize", packageSize));
        list.add(new BasicNameValuePair("outTradeNo", outTradeNo));
        list.add(new BasicNameValuePair("expdate", expdate));
        list.add(new BasicNameValuePair("range", range));
        list.add(new BasicNameValuePair("sign", sign));

        logger.info("JXRT_Provider request : {}", "account="+account+"&expdate="+expdate+"&mobile="+mobile+"&outTradeNo="+outTradeNo+"&packageSize="+packageSize+"&password="+password+"&range="+range+"&apiKey="+apiKey);

        //url格式编码
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(list,"UTF-8");
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

            logger.info("JXRT_Provider response : {}", result);

            logger.info("providerName: {}, type : {}, result : {}", "金信瑞通",
                    "processResponse", result);

            return result;
        } catch (Exception e) {
            throw new HelperException(e);
        }
    }


    private static final String JXRT_URL = "http://121.40.100.34:8989/api/rechChannel";
    private static final String JXRT_SP_ID = "linkech";
    private static final String JXRT_SECURE_KEY = "123456";

    public static String jxrt(String mobile, String packageSize){


        JXRT_Provider provider = new JXRT_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", JXRT_SP_ID);
        params.put("password", JXRT_SECURE_KEY);
        params.put("mobile", mobile);
        params.put("packageSize", packageSize);
        params.put("outTradeNo", "1");
        params.put("expdate", "30");
        params.put("range", "1");
        params.put("apiKey","be6b1faaf1e54ca5b3cad7cc82fc48ea");

        return provider.orderFor(params,JXRT_URL);
    }

    public static void main(String[] args) {

//        System.out.println(EncryptUtils.Md5_16("123456"));

        String mobile = "17681129113";//用户手机号 山东联通（13181129113）
        System.out.println(JXRT_Provider.jxrt(mobile, "20"));

    }

}
