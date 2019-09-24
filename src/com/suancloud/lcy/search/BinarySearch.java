package com.suancloud.lcy.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chaoyou
 * @email lichaoyou@suancioud.cn
 * @date 2019-9-24 20:04
 * @Description
 */
// 注意：使用二分查找的前提是该数组必须是有序的
public class BinarySearch {
    public static void main(String[] args){
        int[] arr = {1, 3, 6, 6, 23, 65, 254};
        int index = binarySearch(arr, 0, arr.length, 254);
        List<Integer> list = binarySearch2(arr, 0, arr.length, 6);
        System.out.println(list);
    }

    /**
     * 二分查找实现算法
     *
     * a、思想
     *      1)、首先确定该数租的中间下标：mid = (0 + arr.length) / 2
     *      2)、然后让需要查找的数：findVal 和 arr[mid] 比较
     *          2.1、findVal > arr[mid]，说明要查找的数在mid 的右边，因此需要递归向右查找
     *          2.2、findVal < arr[mid]，说明要查找的数在mid 的左边，因此需要递归向左查找
     *          2.3、findVal == arr[mid]，说明当前mid 就是要找的数
     *      3)、结束递归、
     *          3.1、找到就结束
     *          3.2、递归完整个数组，仍然没有找到findVal，也需要结束递归，当left > right 就需要退出
     *
     * @param arr   数组
     * @param left  左索引
     * @param right   右索引
     * @param findVal   要查找的值
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal){
        int mid = (left + right) / 2;
        if (left < right){  // 设置查找范围
            if (findVal > arr[mid]){    // 要查找的数在左边
                return binarySearch(arr, mid, right, findVal);
            } else if (findVal < arr[mid]){     // 要查找的数在右边
                return binarySearch(arr, left, mid, findVal);
            } else {
                return mid;
            }
        }
        // 数组内不存在此数据
        return -1;
    }

    /**
     * 注解：对上面的二分查找算法进行一些优化(把数组中所有可以匹配的元素都找出来)
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        int mid = (left + right) / 2;
        List<Integer> list = new ArrayList<>();
        if (left > right) {  // 设置查找范围
            return new ArrayList<>();
        }
        if (findVal > arr[mid]){    // 要查找的数在左边
            return binarySearch2(arr, mid, right, findVal);
        } else if (findVal < arr[mid]){     // 要查找的数在右边
            return binarySearch2(arr, left, mid, findVal);
        } else {
            // 找到的当前mid
            list.add(mid);
            // mid 左边查找
            int temp = mid - 1;
            while (true){
                // 在数组左边界之内，匹配查找值
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                list.add(temp);
                temp --;
            }
            // mid 右边查找
            temp = mid + 1;
            while (true){
                // 在数组右边界之内，匹配查找值
                if (temp > arr.length-1 || arr[temp] != findVal){
                    break;
                }
                list.add(temp);
                temp ++;
            }
            return list;
        }
    }
}
