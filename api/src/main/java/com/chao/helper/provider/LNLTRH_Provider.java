package com.chao.helper.provider;

import com.chao.helper.enumeration.ProductsCode;
import com.chao.helper.exception.HelperException;
import com.chao.helper.utils.Des32Util;
import com.chao.helper.utils.EncryptUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by think on 2016/11/9.
 *
 * 辽宁联通后向
 */
public class LNLTRH_Provider extends HttpProvider {

    private static final Logger logger = LoggerFactory.getLogger(LNLTRH_Provider.class);

    private String password;

    @Override
    public String getProviderKey() {
        return "LNLTRH";
    }

    protected HttpRequestBase createRequest(Map<String, Object> params, String url) {
        HttpPost httpPost = new HttpPost(url);

        String channel_code = (String) params.get("channel_code");
        String biz_id = (String) params.get("biz_id");
        String sim = (String) params.get("sim");
        String product_id = (String) params.get("product_id");
        String charset = (String) params.get("charset");
        String opera_type = (String) params.get("opera_type");
        String seq = (String) params.get("seq");
        password = (String) params.get("password");
        httpPost.addHeader("Authorization", channel_code);
        httpPost.addHeader("charset", charset);
        httpPost.addHeader("Content-Version", "1.0");


        int len = 0;
        String contentEncode = null;
        try {
            //为true按照put顺序排序，为false时按照默认排序
            JSONObject json = new JSONObject();
            json.put("channel_code", channel_code);
            json.put("biz_id", biz_id);
            json.put("sim", sim);
            json.put("product_id", product_id);
            json.put("opera_type", opera_type);
            json.put("seq", seq);
            json.put("sign", EncryptUtils.encodeMD5String(channel_code + biz_id + sim + product_id + opera_type + seq + password));
            contentEncode = Des32Util.encode(json.toString(), password);

            logger.info("providerName: {}, type : {}, paramList : {}, providerUrl : {}", "辽宁联通融合",
                    "createRequest", json.toString(), url);

            len = contentEncode.getBytes(charset).length;
        } catch (UnsupportedEncodingException e) {
            throw new HelperException(e);
        } catch (JSONException e) {
            throw new HelperException(e);
        } catch (Exception e) {
            throw new HelperException(e);
        }

        InputStream is = new ByteArrayInputStream(contentEncode.getBytes());
        InputStreamEntity inputStreamEntity = new InputStreamEntity(is, len);

        httpPost.setEntity(inputStreamEntity);

        return httpPost;
    }

    protected String processResponse(CloseableHttpResponse response) {
        try {
            HttpEntity entityRsp = response.getEntity();

            String readContent =null;
            if (entityRsp != null){
                InputStream instreams = entityRsp.getContent();
                readContent = ConvertStreamToString(instreams);
                System.out.println("Response:" + "\n" + readContent);
            }

            logger.info("providerName: {}, type : {}, readContent : {}", "辽宁联通融合",
                    "processResponse", readContent);

            String result = Des32Util.decode(readContent, password);
            JSONObject jsonObject = new JSONObject(result);
            String code = jsonObject.get("result_code").toString();

            logger.info("providerName: {}, type : {}, result : {}, code : {}", "辽宁联通融合",
                    "processResponse", result, code);

            return result;
        } catch (Exception e) {
            throw new HelperException(e);
        }
    }

    /**
     * ==================================================
     *
     * 辽宁联通融合接口1  有日包（r1024）
     */
    private static final String LNLTRH1_URL = "http://api2.wo800.cn/UnicomBackwardPlatformAPI/openapiV2/";
    private static final String LNLTRH1_SP_ID = "7F996DE06E7941A6A1F566627D88A86C";
    private static final String LNLTRH1_SECURE_KEY = "17810828";

    public static String lnltrh1(String sim, String product_id){

        Provider provider = new LNLTRH_Provider();
        Map<String, Object> params = new HashMap<String, Object>();

        String timestamp = System.currentTimeMillis() + "";
        params.put("channel_code",LNLTRH1_SP_ID);
        params.put("biz_id","generalorder");
        params.put("sim",sim);
        params.put("product_id",product_id);
        params.put("charset","utf-8");
        params.put("opera_type","1");
        params.put("seq",timestamp);
        params.put("password",LNLTRH1_SECURE_KEY);

        return provider.orderFor(params, LNLTRH1_URL);
    }

    /**
     * ==================================================
     *
     * 辽宁联通融合接口2
     */
    private static final String LNLTRH2_URL = "http://api2.wo800.cn/UnicomBackwardPlatformAPI/openapiV2/";
    private static final String LNLTRH2_SP_ID = "C19062FA5D7F474C8B0530F278016DF4";
    private static final String LNLTRH2_SECURE_KEY = "17810828";

    public static String lnltrh2(String sim, String product_id){

        Provider provider = new LNLTRH_Provider();
        Map<String, Object> params = new HashMap<String, Object>();

        String timestamp = System.currentTimeMillis() + "";
        params.put("channel_code",LNLTRH2_SP_ID);
        params.put("biz_id","generalorder");
        params.put("sim",sim);
        params.put("product_id",product_id);
        params.put("charset","utf-8");
        params.put("opera_type","1");
        params.put("seq",timestamp);
        params.put("password",LNLTRH2_SECURE_KEY);

        return provider.orderFor(params, LNLTRH2_URL);
    }

    /**
     * ==================================================
     *
     * 西安雷伊信息技术有限公司
     */
    private static final String LNLTRH3_URL = "http://api2.wo800.cn/UnicomBackwardPlatformAPI/openapiV2/";
    private static final String LNLTRH3_SP_ID = "F98C41895DD84543B696239B95B51B4A";
    private static final String LNLTRH3_SECURE_KEY = "17810828";

    public static String lnltrh3(String sim, String product_id){

        Provider provider = new LNLTRH_Provider();
        Map<String, Object> params = new HashMap<String, Object>();

        String timestamp = System.currentTimeMillis() + "";
        params.put("channel_code",LNLTRH3_SP_ID);
        params.put("biz_id","generalorder");
        params.put("sim",sim);
        params.put("product_id",product_id);
        params.put("charset","utf-8");
        params.put("opera_type","1");
        params.put("seq",timestamp);
        params.put("password",LNLTRH3_SECURE_KEY);

        return provider.orderFor(params, LNLTRH3_URL);
    }

    /**
     * ==================================================
     *
     * 北京塔伯科技有限公司
     */
    private static final String LNLTRH4_URL = "http://api2.wo800.cn/UnicomBackwardPlatformAPI/openapiV2/";
    private static final String LNLTRH4_SP_ID = "5832043DA82F4FE99DAC8ACDD848BE8D";
    private static final String LNLTRH4_SECURE_KEY = "17810828";

    public static String lnltrh4(String sim, String product_id){

        Provider provider = new LNLTRH_Provider();
        Map<String, Object> params = new HashMap<String, Object>();

        String timestamp = System.currentTimeMillis() + "";
        params.put("channel_code",LNLTRH4_SP_ID);
        params.put("biz_id","generalorder");
        params.put("sim",sim);
        params.put("product_id",product_id);
        params.put("charset","utf-8");
        params.put("opera_type","1");
        params.put("seq",timestamp);
        params.put("password",LNLTRH4_SECURE_KEY);

        return provider.orderFor(params, LNLTRH4_URL);
    }

    public static void main(String[] args) throws Exception {

//        String str = "7EEBE0F1630D897AD23BA8B14BBF3283A3F76CB32C814E1E8E4AF34CDA0311E75F74F0D806570A2E2292FF49D528A5CF8BA543191FC56AFB02F822DE3885A99F7E7856084E2EA1E18F4FB526CEF7560F230502E2AD8AC0E6F3D157F9A23DAA29E3E0686DF94A95C85A07F32816980B301CF4D1AB62371C8D98D0D70A6D7F59F2E99390C2F74173E54CF23D1894BBAE9A6FB7EFC4E7B1D85F46C2256CE35F030DC3C6954E32907ED1C5605624F7A863A15FC219CCE20676B463334A39D9C95060BF5151D9CBF7F2D8733C3F9A9C7FBCD0E804D24C4BA374E44022D4FF2BFBED0947D2C87998AD1DA701829AB97E315775BDE4B9A0B6053108FC7B5FBAD0AAA8E342C5C8EF68919C9CA7BC333276462DB5603215E7FF1D528EED4004F5A6BFBB3A30C77927783145226C619A0528AC51DAAF7DD6704248B7BD50871EAF28E78EAA728D5EE8C9CF9BBF41163DC5716A53B28051E50C9CF3A4D2246F2EAA831E1CF12578342414B868BF450FC2EBF654B999DB4B78DD909DF33AEC345E7D3ABC3A97E6FA274430876E9A915BE18FE8D8BCC8077D84E88304747A6CA53154A5B10C217AB69A88C3D8458FF7E02AEA4B18A2C540965F7C0BB3027AB819593A7C84E94C9FA8A1C7DE617B26F2ACA53409110A17";
//
//        String result = Des32Util.decode(str, "17810828");
//        JSONObject jsonObject = new JSONObject(result);
//        String code = jsonObject.get("result_code").toString();

        //18524470613,15640139415,15640139416,13032409098  时作磊
        //林科通道（有日包）
//        System.out.println(LNLTRH_Provider.lnltrh1("13124240997", ProductsCode.LNLTRHENUM.G500.getName()));

        //辽宁联通融合接口2
//        System.out.println(LNLTRH_Provider.lnltrh2("13124240997", ProductsCode.LNLTRHENUM.G500.getName()));

        //西安雷伊信息技术有限公司
        System.out.println(LNLTRH_Provider.lnltrh3("13124240997", ProductsCode.LNLTRHENUM.G50.getName()));

        //北京塔伯科技有限公司
//        System.out.println(LNLTRH_Provider.lnltrh4("13124240997", ProductsCode.LNLTRHENUM.G100.getName()));

    }


    public static String ConvertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error=" + e.toString());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                System.out.println("Error=" + e.toString());
            }
        }
        System.out.println("====================="+sb.toString());
        return sb.toString();
    }


}
