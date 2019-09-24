package com.suancloud.lcy.search;

/**
 * @author chaoyou
 * @email lichaoyou@suancioud.cn
 * @date 2019-9-24 19:39
 * @Description
 */
public class SeqSearch {
    public static void main(String[] args){
        int[] arr = {};
    }

    public static int seqSearch(int[] arr, int value){
        for (int i=0; i<arr.length; i++){
            if (value == arr[i]){
                return arr[i];
            }
        }
        return -1;
    }
}
