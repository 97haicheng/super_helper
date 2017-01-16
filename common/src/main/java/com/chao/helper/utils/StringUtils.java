package com.chao.helper.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by think on 2017/1/16.
 */
public class StringUtils {

    /**
     * 格式化手机字符串"1388888888,1388888888,1388888888,"
     * @return
     */
    public static String mobileStringFormat(String mobiles){
        return mobiles.replaceAll("(?<=\\d)(?=(?:\\d{11})+$)", ",");
    }

    /**
     * NULL字符串转换为空
     * @param str
     * @return
     */
    public static String StringtoNull(String str) {
        if (str == null) {
            str = "";
        } else if (str.trim().equals("NULL") || str.trim().equals("null")) {
            str = "";
        } else {
            str = str.trim();
        }
        return str;
    }

    /**
     * 获取随机数，以数字组成
     * @param num 随机数的位数
     * @return num位的随机数
     */
    public static String getRandom(int num){
        Random r = new Random();
        String rand = "";
        for(int i = 1; i <= num; i++){
            rand = rand + r.nextInt(10);
        }
        return rand;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param str 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String str) {
        if (null == null) {
            return 0;
        }
        char[] c = str.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) len++;
        }
        return len;
    }

    /**
     * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
     *
     * @param c 需要判断的字符
     * @return boolean, 返回true,Ascill字符
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(getRandom(10));
    }


    /**
     * 方法说明：判断字符串是否为空，空格、空字符串、null都视为空
     * @param
     * @return 为空返回true，不为空返回false
     */
    public static boolean isEmptyValue(String str) {
        if (str == null) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方法说明：去除空格、制表符、换行符、回车
     */
    public static String getStringNoBlank(String str) {
        if (str != null && !"".equals(str)) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            return strNoBlank;
        } else {
            return str;
        }
    }

    /**
     * 方法说明：截取文件格式的后缀(包括.)
     * @param fileName	文件名称
     * @return 文件格式的后缀(包括.)
     */
    public static String getFileSuffix(String fileName) {
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        return fileSuffix;
    }
    /**
     * 获得随机子母和数字的组合
     * @param length
     * @return 随机字母和数字的组合
     */
    public static String getCharAndNum(int length) {
        String val = "";
        Random random = new Random();
        String charOrNum = "";
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                charOrNum = "char";
            }else if (i == (length-1)) {
                charOrNum = "num";
            }else {
                charOrNum = (random.nextInt(2)%2 == 0) ? "char":"num";
            }
            if ("num".equalsIgnoreCase(charOrNum)) {//如何获得 0-9之间的数字
                int num = random.nextInt(10);
                if (num == 0) {
                    num = 2;
                }
                if (num == 1) {
                    num = 5;
                }
                val += String.valueOf(num);
            }else {//如何获得随机的字符				//97 - 122   0-25
                char char1  = (char)(97+random.nextInt(26));
                if (char1 == 'o') {
                    char1 = 'm';
                }
                val += String.valueOf(char1);
            }
        }
        val = val.toUpperCase();
        return val;

    }
}
