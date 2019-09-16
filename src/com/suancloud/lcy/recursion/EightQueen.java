package com.suancloud.lcy.recursion;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/15 0015 13:31
 * Content：这是八皇后不冲突摆法的实现类
 */
public class EightQueen {
    /**
     * 八皇后问题思路分析：冲突(任意两个皇后不能处于同一行，同一斜线上)
     *      1、第一个皇后先放第一行第一列
     *      2、第二个皇后放在第二行第一列、然后判断是否冲突，如果冲突，继续放在第二列、第三列、依次把所有都放完，找到一个不冲突的位置
     *      3、继续第三个皇后，还是第一列、第二列.....知道第八个皇后也能放在一个不冲突的位置，算是找到了一种正确解法
     *      4、当得到一个正解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部找到。
     *      5、然后回头继续第一个皇后放第二列，后面继续循环执行1,2,3,4 的步骤
     *
     * 说明：理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法用一个一维数组即可解决问题。arr[8] = {0, 4, 7, 5, 2, 6, 1, 3}
     *      // 对应arr 下标表示第几行。即第几个皇后，arr[i] = val, val表示第i+1个皇后，放在第i+1行的第val+1列
     *
     */

    // 定义一个max，表示棋盘上最多有多少个皇后
    int max = 8;
    // 定义数组array，保存皇后放置的位置结果，比如arr = {0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    // 定义一个变量计算正解次数
    int count = 0;
    // 定义一个变量计算冲突次数
    int judgeCount = 0;

    // 编写一个放置皇后的方法
    // 特别注意：每执行一次递归，进入到check中都有 for (int i=0; i<max; i++)
    public void check(int n){
        if (n == max){  // 表示下了一次棋
            prinf();   // 将摆法打印出来
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i=0; i<max; i++){
            // 先把当前这个皇后n，放到该行的第1列
            array[n] = i;
            // 判断当前行放置第n个皇后到第i列时，是否与前面的棋子冲突
            if (!isJudge(n)){
                // 接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            // 如果冲突，就继续执行array[n] = i;即将第n个皇后放置当前列的下一列的位置上
        }


    }

    // 编写一个判断n摆放位置与前面n-1个棋子摆放位置是否冲突的方法
    public boolean isJudge(int n){
        // 与前面n-1个棋子摆放位置是否冲突的方法
        for (int i=0; i<n; i++){
            // 冲突(任意两个皇后不能处于同一行/列，同一斜线上)
            // Math.abs(n - i) == Math.abs(array[n] - array[i])：
            //      n-i(行数差) == array[n] - array[i](列数差)->形成斜线
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                judgeCount ++;
                return true;
            }
        }
        return false;
    }

    // 设计一个输出棋盘中皇后摆放位置的方法
    public void prinf(){
        for (int arr:array){
            System.out.print(arr + " ");
        }
        System.out.println();
        count ++;
    }




    public static void main(String[] args){
        EightQueen queen = new EightQueen();
        queen.check(0);
        System.out.println("正解种类：" + queen.count);
        System.out.println("冲突次数：" + queen.judgeCount);
    }
}
