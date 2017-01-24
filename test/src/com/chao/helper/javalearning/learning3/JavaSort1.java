package com.chao.helper.javalearning.learning3;

/**
 * Created by think on 2017/1/24.
 * 选择排序
 */
public class JavaSort1 {

    public static void main(String[] args) {

        int[] arr = { 3, 6, 8, 74, 99, 12 };

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        // 打印
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}
