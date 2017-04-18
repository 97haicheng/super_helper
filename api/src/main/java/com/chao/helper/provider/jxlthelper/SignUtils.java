package com.chao.helper.provider.jxlthelper;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 参数签名类(使用时需另导入commons-codec-1.6.jar包) secretKey 密钥 生成签名时只需调用buildInfo(Map<String, String>
 * sParaTemp,String secretKey)方法，将待签名的map集合与密钥传入即可； 比对签名时只需调用verify(Map<String, String>
 * params,String secretkey)方法，将获得的所有参数加入map集合中与密钥传入即可
 * 
 * @author Administrator
 */
public class SignUtils
{

    public static final String secretKey = "aaa";// 企业的密钥

    /**
     * 生成要请求的参数数组
     * 
     * @param sParaTemp
     *            请求前的参数数组
     * @return 要请求的参数数组
     */
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp, String secretKey)
    {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = buildRequestMysign(sPara, secretKey);

        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", "MD5");

        return sPara;
    }

    /**
     * 除去数组中的空值和签名参数
     * 
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray)
    {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0)
        {
            return result;
        }

        for (String key : sArray.keySet())
        {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type"))
            {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 生成签名结果
     * 
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara, String secretKey)
    {
        String prestr = createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        mysign = MD5.sign(prestr, secretKey, "utf-8");// 后两个参数可变 为用户的私钥和编码字符集
        return mysign;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params)
    {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++ )
        {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1)
            {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            }
            else
            {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 验证消息是否是发出的合法消息
     * 
     * @param params
     *            通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verify(Map<String, String> params, String secretkey)
    {

        // isSign是否为true
        // isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
        String sign = "";
        if (params.get("sign") != null)
        {
            sign = params.get("sign");
        }
        boolean isSign = getSignVeryfy(params, sign, secretkey);
        if (isSign)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 根据反馈回来的信息，生成签名结果
     * 
     * @param Params
     *            通知返回来的参数数组
     * @param sign
     *            比对的签名结果
     * @return 生成的签名结果
     */
    private static boolean getSignVeryfy(Map<String, String> Params, String sign, String secretkey)
    {
        // 过滤空值、sign与sign_type参数
        Map<String, String> sParaNew = paraFilter(Params);
        // 获取待签名字符串
        String preSignStr = createLinkString(sParaNew);
        // 获得签名验证结果
        boolean isSign = false;
        isSign = MD5.verify(preSignStr, sign, secretkey, "utf-8");// 后两个参数可变 为用户的私钥和编码字符集
        return isSign;
    }

    /**
     * 生成请求参数
     * 
     * @param sParaTemp
     * @return
     */
    public static String buildInfo(Map<String, String> sParaTemp, String secretKey)
    {
        sParaTemp = buildRequestPara(sParaTemp, secretKey);
        List<String> keys = new ArrayList<String>(sParaTemp.keySet());
        StringBuffer url = new StringBuffer();
        for (int i = 0; i < keys.size(); i++ )
        {
            String name = (String)keys.get(i);
            String value;
            try
            {
                value = URLEncoder.encode((String)sParaTemp.get(name), "utf-8");
                url.append(name + "=" + value + "&");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

        }

        return url.substring(0, url.length() - 1);
    }

    /**
     * 解码参数
     * 
     * @param sParaTemp
     * @return
     */
    public static Map<String, String> decodeParams(Map<String, String> sParaTemp, String method)
    {

        List<String> keys = new ArrayList<String>(sParaTemp.keySet());
        for (int i = 0; i < keys.size(); i++ )
        {
            String name = (String)keys.get(i);
            // String value = (String)sParaTemp.get(name);
            String value;
            try
            {
                value = URLDecoder.decode((String)sParaTemp.get(name), "utf-8");
                if (method.equals("GET"))
                {
                    value = new String(value.getBytes("iso8859-1"), "UTF-8");
                }
                sParaTemp.put(name, value);
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

        }

        return sParaTemp;
    }

    public static String buildInfoParm(Map<String, String> sParaTemp)
    {

        List<String> keys = new ArrayList<String>(sParaTemp.keySet());
        StringBuffer url = new StringBuffer();
        for (int i = 0; i < keys.size(); i++ )
        {
            String name = (String)keys.get(i);
            // String value = (String)sParaTemp.get(name);
            String value;
            try
            {
                value = URLEncoder.encode((String)sParaTemp.get(name), "utf-8");
                url.append(name + "=" + value + "&");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

        }

        return url.substring(0, url.length() - 1);
    }

    /**
     * test
     * 
     * @param args
     */
    public static void main(String[] args)
    {

        Map<String, String> sParaTemp = new HashMap<String, String>();

        // 按次验证码
        sParaTemp.put("service", "paymentv2codesms");
        sParaTemp.put("companyno", "010002");
        sParaTemp.put("productCode", "0005");
        sParaTemp.put("paymentUser", "15070083561");
        sParaTemp.put("totalfee", "0.01");

        // sParaTemp.put("service", "apppaymentv2");
        // sParaTemp.put("companyno", "010002");
        // sParaTemp.put("productCode", "0005");
        // sParaTemp.put("outTradeNo", "2016030210224661");
        // sParaTemp.put("paymentCodeSms", "861");

        // sParaTemp.put("companyno", "010001");
        // sParaTemp.put("appname", "掌中无限");
        // sParaTemp.put("appdetail",
        // "掌中语音，一款免流量观大片追剧集的聚合类娱乐语音软件。是一款支持人机对话、智能问答的应用，具有娱乐休闲、通讯服务、出行服务、生活服务与闲聊解闷为一体的免流量软件。给快捷的都市生活，带来了无限的便捷和趣味。");

        // String url = "http://localhost:8080/openwo/woUtil_newAppApply?"+buildInfo(sParaTemp,
        // SignUtils.secretKey);
        // String url = "http://localhost:8080/openwo/payInfo_openwoService?"+buildInfo(sParaTemp,
        // SignUtils.secretKey);
        String url = "http://inf.mewifi.mobi/openwo/payInfo_openwoService?"+ buildInfo(sParaTemp, SignUtils.secretKey);
        System.out.println(url);

    }

}
