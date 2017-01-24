package com.chao.helper.javalearning.learning2;

/**
 * Created by think on 2017/1/24.
 */
public class JavaContinue {

    public static void main(String[] str) {
        for (int i = 0; i < 10; i++) {

            if (i % 2 == 1) {
                // 继续循环
                continue;
            }
            System.out.println("i : " + i);
        }
    }

}
