package com.chao.helper.provider;

import com.chao.helper.exception.HelperException;
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
 * Created by think on 2017/3/9.
 *
 * 至酷锋云上游供应商对接
 */
public class ZKFY_Provider extends HttpProvider {
    private static final Logger logger = LoggerFactory.getLogger(ZKFY_Provider.class);

    private static final String SUCCESS = "0";

    private static final String SUCCESSCODE = "0000";

    @Override
    public String getProviderKey() {
        return "ZKFY";
    }

    @Override
    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPost post = new HttpPost(url);

        // 设置参数
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = df.format(new Date());

        String userid = (String) params.get("userid");//订购用户号码
        String qdid = (String) params.get("qdid");//渠道ID
        String sqnid  = (String) params.get("sqnid");//产品ID
        String key  = (String) params.get("key");//产品ID
        String transactionid = timeStamp+params.get("OrderId");//业务流水号 长度18位, 由调用方生成,可用渠道号+yyyyMMddHHmmss+随机数,此流水号为接口调用对帐使用
        String sign = EncryptUtils.encodeMD5String((userid+qdid+key).toUpperCase());//签名

        //创建参数列表
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("userid", userid));
        list.add(new BasicNameValuePair("qdid", qdid));
        list.add(new BasicNameValuePair("sqnid", sqnid));
        list.add(new BasicNameValuePair("sign", sign));
        list.add(new BasicNameValuePair("transactionid", transactionid));

        logger.info("ZKFY_Provider request : {}", "userid="+userid+"&qdid="+qdid+"&sqnid="+sqnid+"&sign="+sign+"&transactionid="+transactionid);

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

            logger.info("ZKFY_Provider response : {}", result);

            logger.info("providerName: {}, type : {}, result : {}", "至酷锋云",
                    "processResponse", result);

            return result;
        } catch (Exception e) {
            throw new HelperException(e);
        }
    }


    private static final String ZKFY_URL = "http://123.56.71.97/AK47/Flowdd.ashx";
    private static final String ZKFY_SP_ID = "1000006";
    private static final String ZKFY_SECURE_KEY = "S3g5H8o1";

    public static String zkfy(String userid, String sqnid){

        ZKFY_Provider provider = new ZKFY_Provider();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        params.put("qdid", ZKFY_SP_ID);
        params.put("sqnid", sqnid);
        params.put("OrderId", "chaochaochao");
        params.put("key", ZKFY_SECURE_KEY);

        return provider.orderFor(params,ZKFY_URL);
    }

    public static void main(String[] args) {

        String mobile = "13342493122"; //13342493122  电信
        System.out.println(ZKFY_Provider.zkfy(mobile, "1"));

    }
}
