package com.suancloud.lcy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/17 0017 21:31
 * Content：这是选择排序的算法实现类
 */
public class SelectSorting {

    /**
     * c、说明：
     * 	    ①选择排序一共有数组.length - 1 轮排序
     * 	    ②每一轮排序，又是一次循环，循环的规则
     * 	        1)、先假定当前元素为最小数
     * 	        2)、然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到下标
     * 	        3)、当遍历到数组的最后时，就得到本轮最小数和下标
     * 	        4)、交换
     */
    public static int[] selectSort(int[] arr){
        // 选择排序时间复杂度是O(n^2)
        for (int i=0; i<arr.length-1; i++){
            int minIndex = i;   // 最小值下标
            int min = arr[i];   // 最小值
            // 在当前假定最小值元素后面找到最小值元素
            for (int j=i+1; j<arr.length; j++){
                if (min > arr[j]){  // 说明min，并不是最小
                    min = arr[j];   // 重置min
                    minIndex = j;   // 重置minIndex
                }
            }
            // 将最小值放置在arr[0]的位置，即交换(排序规则：从小到大——把小的调到前面)
            if (minIndex != i){ // 说明存在更小的值
                arr[minIndex] = arr[i]; // 把大的放到后面
                arr[i] = min;   // 把小的放到前面
            }
        }
        return arr;
    }
}
