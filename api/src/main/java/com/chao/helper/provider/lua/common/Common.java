package com.chao.helper.provider.lua.common;

import com.chao.helper.utils.DateUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by QuChao on 2017/6/9.
 * Description :
 */
public class Common {

    public static String getTime(){
        return DateUtils.format("yyyy-MM-dd HH:mm",new Date().getTime());
    }
    public static String getUUID(){
        return UUID. randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
