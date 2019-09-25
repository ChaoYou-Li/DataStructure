package com.suancloud.lcy.search;

/**
 * @author chaoyou
 * @email lichaoyou@suancioud.cn
 * @date 2019-9-25 20:02
 * @Description 插值查找算法实现类
 */
public class InsertValueSearch {
    public static void main(String[] args){
        int[] arr = new int[100];
        for (int i=0; i<arr.length; i++){
            arr[i] = i+1;
        }
        int index =insertValueSearch(arr, 0, arr.length - 1, 55);
        System.out.println("index = "+index);
    }

    /**
     * 注解：差值查找算法
     *
     * 前提：要求数组是有序的
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal){
        // findVal < arr[0] || findVal > arr[arr.length-1]：规定查找范围在数组内
        if (left > right || findVal < arr[0] || findVal > arr[arr.length-1]){
            return -1;
        }
        // 自适应查找mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        // 在中间值的左边
        if (findVal > midVal){
            insertValueSearch(arr, left, mid - 1, findVal);
        }
        // 在中间值的右边
        if (findVal < midVal){
            insertValueSearch(arr, mid + 1, right, findVal);
        }
        return mid;
    }
}
