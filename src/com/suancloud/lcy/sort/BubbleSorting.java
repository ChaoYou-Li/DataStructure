package com.suancloud.lcy.sort;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/16 0016 10:12
 * Content：这是冒泡排序算法的实现类
 */
public class BubbleSorting {
    public static void main(String[] args){
        int[] arr = {3, 9, -1, 10, -2};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * c、排序规则：
     * 	    ①一共进行数组长度-1 次大的循环
     * 	    ②每一趟排序的次数都在逐渐减少
     * 	    ③如果发现在某趟排序中，没有发生一次交换，可以提前结束冒泡排序，这个就是优化。
     */
    public static int[] bubbleSort(int[] arr){
        int temp = 0;   // 辅助变量，用于值的交换
        boolean flag = false;   // 标识变量，标识是否进行交换(用于优化算法)
        // 冒泡排序的时间复杂度O(n^2)
        // ①一共进行数组长度-1 次大的循环
        for (int i=0; i<arr.length-1; i++){    // 执行数组.length - 1 趟
            // ②每一趟排序的次数都在逐渐减少
            for (int j=0; j<arr.length-1-i; j++){   // 每一趟比较的次数
                // arr[j] > arr[j+1]：指定冒泡的规则(从小到大——把大的调往后面)
                // arr[j] < arr[j+1]：指定冒泡的规则(从大到小——把小的调往后面)
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            // ③如果发现在某趟排序中，没有发生一次交换，可以提前结束冒泡排序，这个就是优化。
            if (flag == false){ // 证明数组元素没有发生交换位置
                break;  // 跳出循环结束排序
            } else {
                flag = false;   // 重置flag！！！，进行下一次判断
            }
        }
        return arr;
    }
}
