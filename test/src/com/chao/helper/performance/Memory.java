package com.chao.helper.performance;

/**
 * Created by think on 2017/4/6.
 */
public class Memory {
    public static long used()
    {
        long total=Runtime.getRuntime().totalMemory();
        long free=Runtime.getRuntime().freeMemory();
        return (total-free);
    }
}
