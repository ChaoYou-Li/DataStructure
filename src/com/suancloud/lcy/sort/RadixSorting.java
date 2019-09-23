package com.suancloud.lcy.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/23 0023 23:03
 * Content：
 */
public class RadixSorting {
    public static void main(String[] args){
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序算法
     *
     * 步骤：
     *      1、得到数组中最大数字的位数长度，用于确定要排序几趟
     *      2、定义一个二维数组包含十个一维数组来模拟十个桶
     *      3、定义一个一维数组来记录每个桶放进的数据量
     *      4、定义一个变量记录当前的位数(个、十、百、千。。。。。)处理级别
     *      5、进行位数级别处理流程
     *
     * 缺点：处理大数据量的时候可能会出现内存溢出
     */
    public static void radixSort(int[] arr){

        // 1、得到数组中最大数字的位数长度，用于确定要排序几趟
        int maxLength = 0;
        int max = arr[0];
        for (int i=1; i<arr.length; i++){
            if (max < arr[i]){
                max = arr[i];
            }
        }
        maxLength = (max + "").length();

        /**
         * 2、定义一个二维数组包含十个一维数组来模拟十个桶
         *    2.1、为了防止放入数据的时候产生数据溢出，则每一个数组(桶)大小定位arr.length
         *    2.2、明确：这就是典型的以空间换时间的经典算法
         */
        int[][] bucket = new int[10][arr.length];

        /**
         * 3、定义一个一维数组来记录每个桶放进的数据量
         *    3.1、可以这样理解：bucketElementCounts[0] 记录的就是bucket[0] 桶中数据的个数
         */
        int[] bucketElementCounts = new int[10];

        /**
         * 4、定义一个变量记录当前的位数(个、十、百、千。。。。。)处理级别
         */
        int digit = 1;

        /**
         * 5、进行位数级别处理流程
         *    5.1、n 代表了进行多少轮位数级别处理
         *    5.2、第一轮个位数、第二轮十位数、第三轮百位数.....
         */
        for (int n=0; n<maxLength; n++){
            // 遍历数组中的元素，把位数进行切割并且放进对应桶内
            for (int i=0; i<arr.length; i++){
                // 数据位数切割出来作为入桶判别标识
                int digitOfElment = arr[i] / digit % 10;
                // 根据入桶标识把数据放进桶内
                bucket[digitOfElment][bucketElementCounts[digitOfElment]] = arr[i];
                bucketElementCounts[digitOfElment] ++;
            }
            int index = 0;  // 当前一维数组(桶)的下标，用于遍历桶中的数据
            // 遍历记录桶数据个数的数组
            for (int k=0; k<bucketElementCounts.length; k++){
                // 对应桶中有数据
                if (bucketElementCounts[k] > 0){
                    // 把数据从桶中取出放进arr
                    for (int j=0; j<bucketElementCounts[k]; j++){
                        arr[index] = bucket[k][j];
                        index ++;
                    }
                }
                // 每一轮处理之后都要把记录桶数据个数的数组清空(避免数据重复)
                bucketElementCounts[k] = 0;
            }
            // 进入下一位数级别处理 542 53 3 14 214 748
            digit *= 10;
        }


    }
}
