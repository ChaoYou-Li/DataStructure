package com.suancloud.lcy.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/20 0020 0:01
 * Content：这是归并排序算法实现类
 */
public class MergeSorting {
    public static void main(String[] args){
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分+合的方法
     * @param arr
     * @param left
     * @param right
     * @param temp
     * @return
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * 归并排序：合并的方法
     *
     * 步骤：
     *      1、把数组从中间拆分成(left、right)两个列表
     *      2、定义三个辅助变量：l(左部分遍历指针)、r(右部分遍历指针)、t(有序列表指针)
     *      3、同时遍历左、右列表：依次把左指针指向元素和右指针指向元素做比较：
     *          a、左指针指向元素值 >= 右指针指向元素值：左元素进有序列表
     *          b、右指针指向元素值 >= 左指针指向元素值：右元素进有序列表
     *          c、直到左、右列表有任意一边遍历完成跳出循环体
     *      4、把有剩余的一边的数据依照顺序全部填充到temp 中
     *          a、左指针和左列表边界mid比较：l <= mid 表示左列表还有元素
     *          b、右指针和右列表边界right比较：r <= right 表示右列表还有元素
     *      5、把当前递归进入有序列表的数据拷贝到原数组上
     * @param arr   排序数组
     * @param left  左边有序列表的索引
     * @param mid   中间索引
     * @param right  右边有序列表的索引
     * @param temp  辅助数组
     * @return
     */
    public static int[] merge(int[] arr, int left, int mid, int right, int[] temp){
        int l = left;   // 左边有序列表的索引
        int r = mid + 1;    // 右边有序列表的索引
        int t = 0;  // temp 数组的当前索引

        /**
         * 第一步：
         *      1、先把左右两边(有序)的数据按照排序规则填充到temp 数组
         *      2、直到左右两边(有序)列表，有一边的数据处理完毕
         */
        while (l <= mid && r <= right){
            // arr[l] <= arr[r] 排序规则(从小到大)
            if (arr[l] <= arr[r]){
                temp[t] = arr[l];   // 左边小，就把左边的元素填充到temp
                t ++;
                l ++;
            } else {
                temp[t] = arr[r];   // 右边边小，就把右边的元素填充到temp
                t ++;
                r ++;
            }
        }

        /**
         * 第二步：
         *      1、把有剩余的一边的数据依照顺序全部填充到temp 中
         */
        while (l <= mid){   // 左边列表还有元素
            temp[t] = arr[l];   // 充填到temp
            t ++;
            l ++;
        }
        while (r <= right){     // 右边列表还有元素
            temp[t] = arr[r];   // 充填到temp
            t ++;
            r ++;
        }

        /**
         * 第三步：
         *      1、将temp数组的元素拷贝到arr
         *      2、注意：并不是每次都拷贝所有元素
         */
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t ++;
            tempLeft ++;
        }
        return arr;
    }
}
