package com.chao.helper.utils;

import java.text.DecimalFormat;

/**
 * Created by think on 2017/1/16.
 * 小数格式化工具
 */
public class DoubleFormatUtils {
    /**
     * 将double格式化保留一位小数
     *
     * @param value
     * @return
     */
    public static double formatDb(double value) {
        DecimalFormat df = new DecimalFormat("#.0");
        if (0.0 == value) {
            return value;
        }
        return Double.parseDouble(df.format(value));
    }


    /**
     * 将double格式化保留小数
     *
     * @param value 数值
     * @param radix 保留多少位小数 最大精度到8位
     * @return Double
     */
    public static double formatDouble(double value, int radix) {
        return Double.parseDouble(doubleFormat(value, radix));
    }

    public static String doubleFormat(double value, int radix) {
        String pattern;
        switch (radix) {
            case 0:
                pattern = "################0";
                break;
            case 1:
                pattern = "################0.0";
                break;
            case 2:
                pattern = "################0.00";
                break;
            case 3:
                pattern = "################0.000";
                break;
            case 4:
                pattern = "################0.0000";
                break;
            case 5:
                pattern = "################0.00000";
                break;
            case 6:
                pattern = "################0.000000";
                break;
            case 7:
                pattern = "################0.0000000";
                break;
            case 8:
                pattern = "################0.00000000";
                break;
            default:
                pattern = "################0.0";
                break;
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    /**
     * 给数值加入千位分隔符
     *
     * @param value
     * @return String
     */
    public static String insertDivide(String value) {
        DecimalFormat df = new DecimalFormat("##,###,###.#######");
        return df.format(Double.parseDouble(value));
    }

    /**
     * 给数值加入千位分隔符
     *
     * @param value
     * @return String
     */
    public static String insertDivide(Double value) {
        DecimalFormat df = new DecimalFormat("##,###,###.#######");
        return df.format(value);
    }

    public static void main(String[] args) {
        System.out.println(formatDouble(1.598d, 2));
    }
}
