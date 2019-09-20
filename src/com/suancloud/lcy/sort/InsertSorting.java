package com.suancloud.lcy.sort;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/17 0017 23:55
 * Content：这个是插入排序算法的实现类
 */
public class InsertSorting {

    public static int[] insertSort(int[] arr){

        int insertVal = 0;     // 需要插入的元素
        int insertIndex = 0;    // 插入位
        for (int i=1; i<arr.length; i++){
            insertVal = arr[i];
            insertIndex = i - 1;
            // insertIndex >= 0 插入位必须在数组范围
            // insertVal < arr[insertIndex]  排序规则：从小到大——把后面小的插到前面
            // insertVal > arr[insertIndex]  排序规则：从大到小——把后面大的插到前面
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex --; // 依次往前面移动
            }
            if (insertIndex + 1 != i){  // 说明插入位不在有序列表中
                arr[insertIndex + 1] = insertVal;   // 把待插入元素插入到正确位置
            }
        }
        return null;
    }
}
