package com.chao.helper.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by think on 2016/11/9.
 *
 * 常用加密算法工具类
 */
public class EncryptUtils {


    /**
     * 16位MD5加密
     * @param plainText
     * @return
     */
    public static String Md5_16(String plainText) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // result = buf.toString();  //md5 32bit
            // result = buf.toString().substring(8, 24))); //md5 16bit
            result = buf.toString().substring(8, 24);
            System.out.println("mdt 16bit: " + buf.toString().substring(8, 24));
            System.out.println("md5 32bit: " + buf.toString() );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 用MD5算法进行加密
     * @param str 需要加密的字符串
     * @return MD5加密后的结果
     */
    public static String encodeMD5String(String str) {
        return encode(str, "MD5");
    }

    /**
     * 用SHA算法进行加密
     * @param str 需要加密的字符串
     * @return SHA加密后的结果
     */
    public static String encodeSHAString(String str) {
        return encode(str, "SHA");
    }

    /**
     * 用base64算法进行加密
     * @param str 需要加密的字符串
     * @return base64加密后的结果
     */
    public static String encodeBase64String(String str) {
        BASE64Encoder encoder =  new BASE64Encoder();
        return encoder.encode(str.getBytes());
    }

    /**
     * 用base64算法进行解密
     * @param str 需要解密的字符串
     * @return base64解密后的结果
     * @throws IOException
     */
    public static String decodeBase64String(String str) throws IOException {
        BASE64Decoder encoder =  new BASE64Decoder();
        return new String(encoder.decodeBuffer(str));
    }

    private static String encode(String str, String method) {
        MessageDigest md = null;
        String dstr = null;
        try {
            md = MessageDigest.getInstance(method);
            md.update(str.getBytes());
            dstr = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return dstr;
    }


    /**
     * 南京蓝海辽宁电信start
     */
    // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
    private static final String IVSTR = "3018767842113708";

    // 加密
    public static String Encrypt( String sSrc ,String Skey){
        try{
            if(sSrc==null || sSrc.length()<2){
                return null;
            }
            if( Skey == null ) {
                System.out.print( "Key为空null" );
                return null;
            }
            // 判断Key是否为16位
            if( Skey.length() != 16 ) {
                System.out.print( "Key长度不是16位" );
                return null;
            }
            byte[] raw = Skey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
            Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );// "算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec( IVSTR.getBytes() );// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init( Cipher.ENCRYPT_MODE, skeySpec, iv );
            byte[] encrypted = cipher.doFinal( sSrc.getBytes() );

            return encodeBytes( encrypted );
        }catch(Exception ex){
            return null;
        }

    }

    // 解密
    public static String Decrypt( String sSrc,String sKey){
        try {
            // 判断Key是否正确
            if(sSrc==null || sSrc.length()<16){
                return null;
            }
            if( sKey == null ) {
                System.out.print( "Key为空null" );
                return null;
            }
            // 判断Key是否为16位
            if( sKey.length() != 16 ) {
                System.out.print( "Key长度不是16位" );
                return null;
            }
            byte[] raw = sKey.getBytes( "ASCII" );
            SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
            Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
            IvParameterSpec iv = new IvParameterSpec( IVSTR.getBytes() );
            cipher.init( Cipher.DECRYPT_MODE, skeySpec, iv );

            byte[] encrypted1 = decodeBytes( sSrc );
            try {
                byte[] original = cipher.doFinal( encrypted1 );
                String originalString = new String( original );
                return originalString;
            }
            catch( Exception e ) {
                System.out.println( e.toString() );
                return null;
            }
        }
        catch(Exception ex ) {
            return null;
        }
    }
    public static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }

    public static byte[] decodeBytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i += 2) {
            char c = str.charAt(i);
            bytes[i / 2] = (byte) ((c - 'a') << 4);
            c = str.charAt(i + 1);
            bytes[i / 2] += (c - 'a');
        }
        return bytes;
    }
    /**
     * 南京蓝海辽宁电信end
     */



    public static void main(String[] args) throws IOException {
        String user = "helper";
        System.out.println("原始字符串 " + user);
        System.out.println("MD5加密 " + encodeMD5String(user));
        System.out.println("SHA加密 " + encodeSHAString(user));
        String base64Str = encodeBase64String(user);
        System.out.println("Base64加密 " + base64Str);
        System.out.println("Base64解密 " + decodeBase64String(base64Str));
    }
}
