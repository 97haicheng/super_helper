package com.chao.helper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by think on 2017/1/16.
 */
public class ValidationUtils {
    // 手机号正则表达式
    private static final Pattern MOBILE_PATTERN = Pattern
            .compile("^(13[0-9]|14[5|7]|15[0-3|5-9]|17[6|7|8]|18[0|2|3|5-9])\\d{8}$");

    private static final Pattern ORDER_PATTERN = Pattern.compile("^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$");

    //整数金额正则表达式
    private static final Pattern PRICE_PATTERN = Pattern.compile("^[0-9]{1,}$");

    public static boolean isLegalPrice(String price){
        if (price != null && price.length() > 0) {
            return PRICE_PATTERN.matcher(price).find();
        } else {
            return false;
        }
    }

    /**
     * 手机号验证
     *
     * @param mobile
     * @return
     */
    public static boolean isLegalMobile(String mobile) {
        if (mobile != null && mobile.length() > 0) {
            return MOBILE_PATTERN.matcher(mobile).find();
        } else {
            return false;
        }
    }

    /**
     * orderId验证 数字字母下划线 下划线不能开头与结尾
     *
     * @param orderId
     * @return
     */
    public static boolean isLegalOrder(String orderId) {
        if (orderId != null && orderId.length() > 0) {
            return ORDER_PATTERN.matcher(orderId).find();
        } else {
            return false;
        }
    }

    /**
     * 时间戳验证
     *
     * @param timestamp
     * @return
     */
    public static boolean isLegalTime(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            Date d = sdf.parse(timestamp);
            if (System.currentTimeMillis() > d.getTime() + (60 * 1000 * 10)
                    || System.currentTimeMillis() <= d.getTime() - (60 * 1000 * 10)) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }



}
