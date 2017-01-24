package com.chao.helper.javalearning.learning3;

/**
 * Created by think on 2017/1/24.
 * 冒泡排序
 */
public class JavaSort2 {

    public static void main(String[] args) {

        int[] arr = { 3, 6, 8, 74, 99, 12 };

        for (int i = 0; i < arr.length - 1; i++) {
            // 每一次比较的元素-1，避免角标越界
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        // 输出
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

}
