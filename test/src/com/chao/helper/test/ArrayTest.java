package com.chao.helper.test;

/**
 * Created by think on 2017/2/10.
 */
public class ArrayTest {
    public static void main(String[] args) {
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        //预期大于3，结果是3
        System.out.println(ary.length);
    }
}
