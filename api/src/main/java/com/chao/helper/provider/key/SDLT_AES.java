package com.chao.helper.provider.key;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by think on 2017/4/5.
 *
 * 山东联通加密工具类
 */
public class SDLT_AES {
    private static final String ALGO = "AES";


    public static String aesDecrypt(String ciphertext, String password) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            IOException {
        byte[] enCodeFormat =password.getBytes();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);// 创建密码
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        byte[] result = cipher.doFinal(new BASE64Decoder().decodeBuffer(ciphertext));
        return new String(result, "UTF-8"); //
    }

    public static String aesEncrypt(String content, String password) {
        byte[] result = null;
        try{

            byte[] enCodeFormat = password.getBytes();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGO);
            Cipher cipher = Cipher.getInstance(ALGO);// 创建密码
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            result = cipher.doFinal(byteContent);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new BASE64Encoder().encode(result); // 加密

    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        System.out.println(aesEncrypt("aesDecrypt","1265171517918656"));
        System.out.println(aesDecrypt("WZdzsCP8BaGiG7FPpxpr0Q==","1265171517918656"));
    }
}
