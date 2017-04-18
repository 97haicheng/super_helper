package com.chao.helper.provider.jxlthelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class SecretUtil {
    // 自动生成秘钥
    public static String getSecretKey()
    {
        StringBuffer sb = new StringBuffer();
        String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();// 随机类初始化
        for (int i = 0; i < 40; i++ )
        {
            int number = random.nextInt(temp.length());// [0,62)
            sb.append(temp.charAt(number));
        }
        return sb.toString();
    }
    /**
     * 对获得的参数集合进行处理
     * 
     * @param requestParams
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> verifyMap(Map requestParams, String method)
    {
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();)
        {
            String name = (String)iter.next();
            String[] values = (String[])requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++ )
            {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i]+ ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // try
            // {
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            // }
            // catch (UnsupportedEncodingException e)
            // {
            // e.printStackTrace();
            // }
            params.put(name, valueStr);
        }

        // params = SignUtils.decodeParams(params,method);

        return params;
    }
    public static void main(String[] args){
    	String secretKey = SecretUtil.getSecretKey();
    	System.err.println("secretKey:"+secretKey);
    }
}
