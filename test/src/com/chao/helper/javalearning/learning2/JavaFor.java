package com.chao.helper.javalearning.learning2;

/**
 * Created by think on 2017/1/24.
 * 条件表达式 循环条件表达式  循环后的操作表达式
 * for (int i = 0; i < str.length; i++) {
 *       //执行语句
 * }
 */
public class JavaFor {

    //for
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            System.out.println("i = " + i);
        }

        /**
         * 九九乘法表
         */
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < i + 1; j++) {

                System.out.print(j + "*" + i + " = " + j * i + " ");
            }
            System.out.println(" ");
        }
    }

//    while
//    public static void main(String[] str) {
//
//        int i = 0;
//        while (i < 10) {
//            System.out.println("i = " + i);
//            i++;
//        }
//    }

}
