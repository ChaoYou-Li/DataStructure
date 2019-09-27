package com.suancloud.lcy.search;

import java.util.Arrays;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/25 0025 23:25
 * Content：这是斐波那契查找算法
 */
public class FibonacciSearch {
    public static void main(String[] args){
        int[] arr = {1, 8, 10, 89, 100, 1234};
        System.out.println("index = "+fibonacciSearch(arr, 89));
    }

    /**
     * 注解：采用非递归方式生成斐波那契数组
     */
    public static int[] fib(int maxSize){
        int[] arr = new int[maxSize];
        arr[0] = 1;
        arr[1] = 1;
        for (int i=2; i<arr.length; i++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr;
    }

    /**
     * 注解：采用非递归方式编写斐波那契查找算法
     */
    public static int fibonacciSearch(int[] arr, int key){
        int low = 0;    // 左边界
        int high = arr.length - 1;   // 右边界
        int k = 0;  // 斐波那契分割指针f(k)
        int mid = 0;
        int[] f = fib(20);  // 斐波那契数组
        // 获取斐波那契分割数值的下标
        while (high > f[k] - 1){
            k ++;
        }
        // 因为f[k] 可能大于 arr.length，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        // 多余部分可用0 补充，实际上是用arr 数组的最后元素填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 举例：temp = {1, 8, 10, 89, 100, 1234, 0, 0} => temp = {1, 8, 10, 89, 100, 1234, 1234, 1234}
        for (int i=high+1; i<temp.length; i++){
            temp[i] = arr[high];
        }

        // 使用while 来循环处理，找到我们的需求key
        while (low <= high){
            /**
             * 原理：f[k] = f[k-1] + f[k-2](斐波那契原理)
             *      f[k] - 1 = (f[k-1] -1) + (f[k-2] - 1) + 1;
             *      mid = low + f[k-1] - 1;
             */
            // 初始化切割位置
            mid = low + f[k-1] - 1; // 相当于：当前数组长度 * 0.618
            // 大于切割位置，说明在切割位置的后面
            if (key > temp[mid]){
                // 重新设定右边界
                low = mid + 1;
                /**
                 * 原因：新的查找数组arr 的范围(mid, f[k-2] - 1)之间
                 *      且：f[k-2] -1 = (f[k-3]-1) + (f[k-4]-1)
                 *      所以：mid = mid + f[k-3]-1 = mid + f[k-1-2] -1
                 */
                k -= 2;
            } else if (key < temp[mid]){
                // 重新设置左边界
                high = mid - 1;
                /**
                 * 原因：新的查找数组arr 的范围(low, f[k-1] - 1)之间
                 *      且：f[k-1] = (f[k-2]-1) + (f[k-3]-1) + 1
                 *      所以：mid = low + f[k-2] -1 = f[k-1-1] -1
                 */
                k --;
            } else {
                if (mid <= high){   // 返回当前mid
                    return mid;
                } else {    // 因为超出high 部分使用arr[high]填充的
                    return high;
                }
            }
        }
        return -1;  // 没有找到
    }
}
