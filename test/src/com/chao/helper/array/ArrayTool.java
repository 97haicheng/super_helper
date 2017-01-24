package com.chao.helper.array;

/**
 * 这是一个可以对数组进行操作的工具类，该类中提供了，获取最值，排序等功能。
 *
 * Created by think on 2017/1/22.
 * @version V1.1
 */

public class ArrayTool {
    /**
     * 无参构造
     */
    public ArrayTool() {
    }

    /**
     * 获取一个整形数组中的最大值。
     * @param arr 接收一个int类型的数组。
     * @return 会返回一个该数组中最大值。
     */
    public static int getMax(int[] arr) {
        int max = 0;
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > arr[max])
                max = x;
        }
        return arr[max];
    }

    /**
     * 给int数组进行选择排序。
     * @param arr 接收一个int类型的数组。
     */
    public static void selectSort(int[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {
            for (int y = x + 1; y < arr.length; y++) {
                if (arr[x] > arr[y]) {
                    swap(arr, x, y);
                }
            }
        }
    }

    /**
     * 给int数组进行冒泡排序。
     * @param arr 接收一个int类型的数组。
     */
    public static void bubbleSort(int[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {
            for (int y = 0; y < arr.length - x - 1; y++) {
                if (arr[y] > arr[y + 1]) {
                    swap(arr, y, y + 1);
                }
            }
        }
    }

    /**
     * 给数组中元素进行位置的置换。
     * @param arr 接收一个int类型的数组。
     * @param a 要置换的位置
     * @param b 要置换的位置
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 用于打印数组中的元素。打印形式是：[elemet1, element2, ...]
     * @param arr 接收一个int类型的数组。
     */
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int x = 0; x < arr.length; x++) {
            if (x != arr.length - 1)
                System.out.print(arr[x] + ", ");
            else
                System.out.println(arr[x] + "]");
        }
    }
}