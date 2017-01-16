package com.chao.helper.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * Created by think on 2016/11/9.
 *
 * DES32对称加密工具类
 */
public class Des32Util {

    private static final String SECRET_KEY_TYPE = "DES";
    private static final String ECB_MOB = "DES/ECB/PKCS5Padding";
    private static final String CHAESET_NAME = "UTF-8";

    private static Key getKey(String password) throws Exception{
        byte[] DESkey = password.getBytes(CHAESET_NAME);
        DESKeySpec keySpec = new DESKeySpec(DESkey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_TYPE);
        return keyFactory.generateSecret(keySpec);
    }

    /**
     * 加密
     * @param data  加密数据
     * @param password  密码  固定八位
     * @return
     * @throws Exception
     */
    public static String encode(String data, String password) throws Exception {
        Cipher enCipher = Cipher.getInstance(ECB_MOB);
        Key key = getKey(password);
        enCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] pasByte = enCipher.doFinal(data.getBytes(CHAESET_NAME));
        return encodeByte2HexStr(pasByte);
    }

    /**
     * 解密
     * @param data 解密数据
     * @param password 密码
     * @return
     * @throws Exception
     */
    public static String decode(String data, String password) throws Exception {
        Cipher deCipher = Cipher.getInstance(ECB_MOB);
        Key key = getKey(password);
        deCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] pasByte = deCipher.doFinal(parseHexStr2Byte(data));
        return new String(pasByte, CHAESET_NAME);
    }


    /**
     * 二进制转变为16进制
     *
     * @param buf
     * @return
     */
    public static String encodeByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转变为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    public static void main(String[] args) {
        String readContent = "7EEBE0F1630D897AD23BA8B14BBF3283A3F76CB32C814E1E8E4AF34CDA0311E75F74F0D806570A2E2292FF49D528A5CF8BA543191FC56AFB02F822DE3885A99F7E7856084E2EA1E18F4FB526CEF7560F230502E2AD8AC0E6F3D157F9A23DAA29E3E0686DF94A95C85A07F32816980B301CF4D1AB62371C8D98D0D70A6D7F59F2E99390C2F74173E54CF23D1894BBAE9A6FB7EFC4E7B1D85F46C2256CE35F030DC3C6954E32907ED1C5605624F7A863A15FC219CCE20676B463334A39D9C95060BF5151D9CBF7F2D8733C3F9A9C7FBCD0E804D24C4BA374E44022D4FF2BFBED0947D2C87998AD1DA701829AB97E315775BDE4B9A0B6053108FC7B5FBAD0AAA8E342C5C8EF68919C9CA7BC333276462DB5603215E7FF1D528EED4004F5A6BFBB3A30C77927783145226C619A0528AC51DAAF7DD6704248B7BD50871EAF28E78EAA728D5EE8C9CF9BBF41163DC5716A53B28051E50C9CF3A4D2246F2EAA831E1CF12578342414B868BF450FC2EBF654B999DB4B78DD909DF33AEC345E7D3ABC3A97E9D48BE050074329915BE18FE8D8BCC8077D84E88304747A6CA53154A5B10C219F52E8E2F68798FD8195C0378BEC473834EB099BA8BC712294DA38159DA34F7A02A7202B925ADFEC320BE40C1D6D6A";
//        String readContent = "7EEBE0F1630D897AD23BA8B14BBF3283AE8D8890BE68CFA31837C1ED55592450E6263E4C1E10F1A186CAC598FD3B04766CA53154A5B10C21BF01BC0011D377E9905C68178BF43601EABF86C3FF34B1B9BD429A8AECDE25F652224A143CDBE4C931793C3425CCCD17D02CDD34351112937FBA3C9F0F1FBE0D";
//        String readContent = "7EEBE0F1630D897AD23BA8B14BBF3283A3F76CB32C814E1E8E4AF34CDA0311E75F74F0D806570A2E2292FF49D528A5CF8BA543191FC56AFB02F822DE3885A99F7E7856084E2EA1E18F4FB526CEF7560F230502E2AD8AC0E6F3D157F9A23DAA29E3E0686DF94A95C85A07F32816980B301CF4D1AB62371C8D98D0D70A6D7F59F2E99390C2F74173E54CF23D1894BBAE9A6FB7EFC4E7B1D85F46C2256CE35F030DC3C6954E32907ED1C5605624F7A863A15FC219CCE20676B463334A39D9C95060BF5151D9CBF7F2D8733C3F9A9C7FBCD0E804D24C4BA374E44022D4FF2BFBED0947D2C87998AD1DA701829AB97E315775BDE4B9A0B6053108FC7B5FBAD0AAA8E342C5C8EF68919C9CA7BC333276462DB5603215E7FF1D528EED4004F5A6BFBB3A30C77927783145226C619A0528AC51DAAF7DD6704248B7BD50871EAF28E78EAA728D5EE8C9CF9BBF41163DC5716A53B28051E50C9CF3A4D2246F2EAA831E1CF12578342414B868BF450FC2EBF654B999DB4B78DD909DF33AEC345E7D3ABC3A97E9D48BE050074329915BE18FE8D8BCC8077D84E88304747A6CA53154A5B10C218A681BD4F6C6D3964D13453BEBBFE27C7A591935E9763B6ED456B9D93E0A705F7236BF8ED96B1859E365FE49DFD";
        try {
            String result = Des32Util.decode(readContent, "17810828");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
