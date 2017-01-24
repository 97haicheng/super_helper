package com.chao.helper.javalearning.learning2;

/**
 * Created by think on 2017/1/24.
 * do while要结合while语句这样更容易说明一些事情
 */
public class JavaDoWhile {
    public static void main(String[] args) {
        int a = 1;
        do {
            System.out.println("a = " + a);
            a++;
        } while (a < 10);
    }
}
