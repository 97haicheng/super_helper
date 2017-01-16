package com.chao.helper.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by think on 2016/11/11.
 *
 * 全国后向加密工具
 */
public class DigestUtils {

    final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    /**
     * 二进制进行MD5加密
     * @param datas 二进制数据
     * @return 加密后二进制数据
     */
    public static byte[] computeMD5Digest(byte[]... datas) {
        try {
            MessageDigest engine = MessageDigest.getInstance("MD5");
            for (byte[] data : datas)
                engine.update(data);
            return engine.digest();
        } catch (Exception e) {
            throw new RuntimeException("Failed to compute MD5 digest", e);
        }
    }

    /**
     * 字符串转UTF－8二进制数据
     * @param data
     * @return UTF－8二进制数据
     */
    public static byte[] stringToBytes(String data) {
        try {
            return data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 二进制转换为大写的十六进制
     * @param digests 二进制数据
     * @return 十六进制
     */
    public static String bytesToHexString(byte[]... digests) {
        return bytesToHexString(false, digests);
    }

    /**
     * 二进制转换为大写的十六进制
     * @param isUpperCase 二进制数据
     * @param digests 二进制数据
     * @return 十六进制
     */
    public static String bytesToHexString(boolean isUpperCase, byte[]... digests) {
        StringBuilder hexBuilder = new StringBuilder();
        for (byte[] digest : digests)
            for (final byte b : digest)
                hexBuilder.append(digits[(b & 0xf0) >> 4]).append(
                        digits[(b & 0x0f)]);
        return isUpperCase ? hexBuilder.toString().toUpperCase() : hexBuilder.toString();
    }

    /**
     * 签名生成算法
     * @param params
     * <String,String> params 请求参数集,所有参数必须已转换为字符串类 型
     * @param secret
     *  secret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(Map<String, Object> params,
                                      String secret) throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedParams = new TreeMap<String, Object>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();
        // 遍历排序后的字典,将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            basestring.append(param.getKey()).append("=")
                    .append(param.getValue()).append("&");
        }
        basestring.append("key=").append(secret);
        // 使用 MD5 对待签名串求签
        byte[] bytes = computeMD5Digest(stringToBytes(basestring.toString()));

        return bytesToHexString(bytes);
    }
}
