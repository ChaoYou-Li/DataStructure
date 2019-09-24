package com.suancloud.lcy.sort;

import java.util.Arrays;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/17 0017 23:55
 * Content：这个是插入排序算法的实现类
 */
public class InsertSorting {

    public static void main(String[] args){
        int[] arr = {3, 9, -1, 10, -2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 注解：插入排序算法把无序部分往有序部分找到适当位置插入
     *
     * 步骤：
     *      1、定义两个辅助变量：待插元素、插入位
     *      2、遍历无序部分列表的元素
     *          遍历顺序：
     *              a、无序列表：从左到右
     *              b、有序列表：从右到左
     *      3、初始化待插元素和插入位
     *      4、遍历有序部分列表
     *          遍历规则：
     *              a、insertIndex >= 0 插入位必须在数组范围
     *              b、insertVal < arr[insertIndex]  排序规则：从小到大——把后面小的插到前面
     *              c、insertVal
     *      5、进行元素移位，移到到插入位
     *      6、把待插元素放进插入位
     */
    public static int[] insertSort(int[] arr){
        int insertVal = 0;     // 需要插入的元素
        int insertIndex = 0;    // 插入位
        // 刚开始的时候有序列表只有数组的首元素，后面n-1 都是无序列表
        for (int i=1; i<arr.length; i++){
            /**
             * 遍历顺序：
             *      1、无序列表：从左到右
             *      2、有序列表：从右到左
             */
            // 初始化待插元素和插入位
            insertVal = arr[i];
            insertIndex = i - 1;
            // insertIndex >= 0 插入位必须在数组范围
            // insertVal < arr[insertIndex]  排序规则：从小到大——把后面小的插到前面
            // insertVal > arr[insertIndex]  排序规则：从大到小——把后面大的插到前面
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex --; // 依次往前面移动
            }
            if (insertIndex + 1 != i){  // 说明找到了插入位
                arr[insertIndex + 1] = insertVal;   // 把待插入元素插入到正确位置
            }
            // 否则当前位置就是插入位
        }
        return null;
    }
}
