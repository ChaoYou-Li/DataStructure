package com.suancloud.lcy.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/18 0018 21:33
 * Content：
 */
public class TestDemo {
    public static void main(String[] args){
        // 测试80000个随机数的数组排序耗时
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String start = format.format(date);
        System.out.println("开始时间："+start);
//        int[] arr = {3, 9, -1, 10, -2};  // 原始数组
        int[] arr = new int[8000000];
        for (int i=0; i<8000000; i++){
            arr[i] = (int) (Math.random()*800000);
        }
        // 测试冒泡排序耗时：8w = 16664 ms
//        BubbleSorting.bubbleSort(arr);
        // 测试选择排序耗时：8w = 3765 ms
//        SelectSorting.selectSort(arr);
        // 测试插入排序耗时：8w = 1238 ms
//        InsertSorting.insertSort(arr);
        // 测试希尔交换法排序耗时：8w = 12398 ms
//        ShellSorting.shellSort1(arr);
        // 测试希尔移位法排序耗时：800w = 3317 ms
//        ShellSorting.shellSort2(arr);
        // 测试快速排序耗时：800w = 1830 ms
//        QuickSorting.quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
        // 测试归并排序耗时：800w = 1942 ms
//        MergeSorting.mergeSort(arr, 0, arr.length-1, new int[arr.length]);
        // 测试基数排序耗时：1090 ms
        RadixSorting.radixSort(arr);
        Date date1 = new Date();
        String end = format.format(date1);
        System.out.println("结束时间："+end);
        System.out.println("消耗时间："+(date1.getTime() - date.getTime())+" ms");
    }
}
