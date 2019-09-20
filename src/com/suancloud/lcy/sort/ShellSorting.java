package com.suancloud.lcy.sort;

import java.util.Arrays;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/18 0018 23:03
 * Content：这是希尔排序算法的实现类
 */
public class ShellSorting {
    public static void main(String[] args){
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序：交换法
     *
     * 步骤：
     *      1、定义一个中间件变量供后面元素间交换
     *      2、循环以二倍速缩小分组步长(增量)
     *      3、按指定步长遍历每一个分组
     *      4、遍历分组中的元素
     *      5、元素间的比较
     *      6、元素交换
     */
    public static int[] shellSort1(int[] arr) {
        int temp = 0;   // 交换中间件
        for (int gap = arr.length / 2; gap > 0; gap /= 2) { // gap 为分组步长(增量)
            for (int i = gap; i < arr.length; i++) {    // 遍历每一个分组
                for (int j = i - gap; j >= 0; j -= gap) {   // 分组元素遍历
                    // arr[j] > arr[j + gap] 排序规则：从小到大
                    // arr[j] > arr[j + gap] 排序规则：从大到小
                    if (arr[j] > arr[j + gap]) {    // 分组元素间比较
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
        return arr;
    }

    /**
     * 希尔排序：移位法
     *
     * 步骤：
     *      1、定义两个辅助变量，待插入元素、插入位
     *      2、循环以二倍速缩小分组步长(增量)
     *      3、按指定步长遍历每一个分组
     *      4、初始化辅助变量
     *      5、遍历分组元素，并且按指定排序规则进行元素移位
     *      6、把待插入元素插入到正确位置
     */
    public static int[] shellSort2(int[] arr){
        int insertVal = 0;     // 需要插入的元素
        int insertIndex = 0;    // 插入位
        for (int gap = arr.length / 2; gap > 0; gap /= 2) { // gap 为分组步长(增量)
            for (int i = gap; i < arr.length; i++) {    // 遍历每一个分组
                insertIndex = i;    // 假设插入位
                insertVal = arr[insertIndex];   // 假设待插入元素
//                if (arr[insertIndex] < arr[insertIndex - gap]){
                    // insertIndex >= 0 插入位必须在数组范围
                    // insertVal < arr[insertIndex - gap]  排序规则：从小到大——把后面小的插到前面
                    // insertVal > arr[insertIndex - gap]  排序规则：从大到小——把后面大的插到前面
                    while (insertIndex - gap >= 0 && insertVal < arr[insertIndex - gap]){
                        arr[insertIndex] = arr[insertIndex - gap];  // 元素移位
                        insertIndex -= gap; // 依次往前面移动
                    }
//                    if (insertIndex - gap != i){  // 说明插入位不在有序列表中
                        arr[insertIndex] = insertVal;   // 把待插入元素插入到正确位置
//                    }
//                }
            }
        }
        return arr;
    }
}
