package com.chao.helper.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by think on 2017/2/15.
 */
public class RandomUtil {

    /**
     * 返回长度为【strLength】的随机数，在前面补0
     */
    public static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    /**
     * 返回长度为【length】的随机码
     */
    private static String getRandomString(int length) { //length表示生成字符串的长度
//        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getRandomString(16));
        DateFormat df = new SimpleDateFormat("yyMMddHH");
        System.out.println(df.format(new Date())+getRandomString(8));
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());

    }

}
