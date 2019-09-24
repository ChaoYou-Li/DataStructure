package com.suancloud.lcy.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/19 0019 22:12
 * Content：这是快速排序算法实现类
 */
public class QuickSorting {
    public static void main(String[] args){
        int arr[] ={-9,78,0,23,8,70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     *
     * 步骤：
     *      1、定义两个辅助变量，代替left、right
     *      2、根据左、右指针确定数组的中间元素
     *      3、利用左、右指针遍历数组
     *      4、利用左指针遍历中间元素的左边部分数组
     *         利用右指针遍历中间元素的右边部分数组
     *      5、证明：右边的数据全部小于pivot，左边数据全部大于pivot
     *      6、交换值(左边大值、右边小值)
     *      7、如果交换完后，发现这个arr[l] == pivot，r --，前移(避免死锁)
     *         如果交换完后，发现这个arr[r] == pivot，l ++，后移(避免死锁)
     *      8、避免后面递归的时候产生栈溢出
     *      9、向左递归
     *         向右递归
     * @param arr   要排序的数组
     * @param left  左指针
     * @param right  右指针
     * @return
     */
    public static int[] quickSort(int[] arr, int left, int right){
        int l = left;   // 左下标
        int r = right;  // 右下标
        int pivot = arr[(l + r) / 2];    // 中间元素
        int temp = 0;   // 辅助变量，用于值交换。
        // 3、利用左、右指针遍历数组
        while (l < r){
            // 4、利用左指针遍历中间元素的左边部分数组
            while (arr[l] < pivot){
                l ++;
            }
            // 5、利用右指针遍历中间元素的右边部分数组
            while (arr[r] > pivot){
                r --;
            }
            // 证明：右边的数据全部小于pivot，左边数据全部大于pivot
            if (l >= r){
                break;
            }
            // 交换值
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            // 如果交换完后，发现这个arr[l] == pivot，r --，前移(避免死锁)
            if (arr[l] == pivot){
                r --;
            }
            // 如果交换完后，发现这个arr[r] == pivot，l ++，后移(避免死锁)
            if (arr[r] == pivot){
                l ++;
            }
        }

        // 避免后面递归的时候产生栈溢出
        if (l == r){
            l ++;
            r --;
        }
        // 向左递归
        if (left < r){
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l){
            quickSort(arr, l, right);
        }
        return arr;
    }
}
