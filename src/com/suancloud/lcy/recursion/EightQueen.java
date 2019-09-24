package com.suancloud.lcy.recursion;

import java.util.Date;

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
     */

    // 定义一个max，表示棋盘上最多有多少个皇后
    int max = 8;
    // 定义数组array，保存皇后放置的位置结果，比如arr = {0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    // 定义一个变量计算正解次数
    int count = 0;
    // 定义一个变量计算冲突次数
    int judgeCount = 0;
    // 二维数组模拟棋盘
    int[][] ch = new int[8][8];

    // 编写一个放置皇后的方法
    // 特别注意：每执行一次递归，进入到check中都有 for (int i=0; i<max; i++)
    public void check(int n){
        int[][] chess = ch.clone();
        if (n == max){  // 表示下了一次棋
            prinf(chess);   // 将摆法打印出来
            return;
        }

        // 在当前行的每一列位置放入皇后，并判断是否冲突
        for (int i=0; i<max; i++){
            for (int j=0; j<max; j++){
                chess[n][j] = 0;
            }

            chess[n][i] = 1;
            if (isSafety(n, i, chess)){
                // 接着放n+1个皇后，即开始递归
                check(n + 1);
            }

        }
    }

    public void check2(int n){
        if (n == max){  // 表示下了一次棋
            int[][] chess = new int[max][max];
            for (int i=0; i<array.length; i++){
                chess[i][array[i]] = 1;
            }
            prinf(chess);   // 将摆法打印出来
            return;
        }

        for (int i=0; i<max; i++){
            // 先把当前这个皇后n，放到该行的第1列
            array[n] = i;
            // 判断当前行放置第n个皇后到第i列时，是否与前面的棋子冲突
            if (!isJudge(n)){
                // 接着放n+1个皇后，即开始递归
                check2(n + 1);
            }
            // 如果冲突，就继续执行array[n] = i;即将第n个皇后放置当前列的下一列的位置上
        }
    }

    /**
     * 编写一个判断第n颗棋子摆放位置与前面n-1颗棋子摆放位置是否冲突的方法
     *
     * 步骤：
     *      1、遍历参数n 颗棋子
     *      2、检查第n 颗棋子摆法位置与前面n - 1 颗棋子位置是否冲突
     *      3、返回检查结果
     */
    public boolean isJudge(int n){
        // 与前面n-1个棋子摆放位置是否冲突的方法(每一行只有一颗棋子)
        for (int i=0; i<n; i++){
            /**
             * 冲突：任意两个皇后不能处于同一行/列，同一斜线上
             *      1、同一行：遍历中i 的每一个值都是一行
             *      2、同一列：array[n] == array[i](数组上的每个数字都代表着其下标为行数，数值为列数)
             *      3、同一斜线：n - i(行数差) == array[n] - array[i](列数差)->形成斜线
             */
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                judgeCount ++;
                return true;
            }
        }
        return false;
    }

    /**
     * 注解：判断即将摆放皇后位置是否与之前摆放的皇后位置起冲突
     *
     * 冲突：任意两个皇后不能处于同一行/列，同一斜线上, 棋子坐标——temp(x, y)
     *      1、同一行：遍历中i 的每一个值都是一行
     *      2、同一列：temp(x-n, y){int n>=0}
     *      3、同一斜线：
     *          a、左上斜线：temp(x-n, y-n){int n>=0}
     *          b、右上斜线：temp(x-n, y+n){int n>=0}
     *
     * 步骤：chess——棋子
     *      1、定义一个辅助变量step，用来标识当前行数
     *      2、遍历二维数组的row 行数据(因为每一行只能放一颗棋子)
     *      3、根据chess(row, col)查找棋盘与之产生冲突的坐标线上是否存在其他棋子
     *          a、中上线：在chess(row, col)之前行数据同一列上的坐标线chess(row-n, col){int n>=0}
     *          b、左上线：在chess(row, col)行和列划分左上部分坐标中的坐标线temp(x-n, y-n){int n>=0}
     *          c、右上线：在chess(row, col)行和列划分右上部分坐标中的坐标线temp(x-n, y+n){int n>=0}
     * @param row 行坐标
     * @param col 列坐标
     * @param chess
     * @return
     */
    public boolean isSafety(int row, int col, int[][] chess){
        int step = 1;   // 因为第一颗棋子没有其他棋子与之比较冲突，所以从第二颗棋子才开始判断
        while (true){
            if (row  < step){   // 保证遍历行数在棋盘范围
                break;
            }
            // 列冲突：列固定，行变化
            if (chess[row-step][col] == 1){
                judgeCount ++;
                return false;
            }
            // 左上线：在chess(row, col)行和列划分左上部分坐标中的坐标线temp(x-n, y-n){int n>=0}，(col - step) >= 0 用于保证在棋盘内
            if ((col - step) >= 0 && chess[row-step][col-step] == 1){
                judgeCount ++;
                return false;
            }
            // 右上线：在chess(row, col)行和列划分右上部分坐标中的坐标线temp(x-n, y+n){int n>=0}，(col + step) < max 用于保证在棋盘内
            if ((col + step) < max && chess[row-step][col+step] == 1){
                judgeCount ++;
                return false;
            }
            step ++;
        }
        return true;
    }

    // 设计一个输出棋盘中皇后摆放位置的方法
    public void prinf(int[][] chess){
        System.out.println("这是第"+count+"次正解");

        for (int[] c:chess){
            for (int num:c){
                System.out.print(num+"  ");
            }
            System.out.println();
        }
        count ++;
    }




    public static void main(String[] args){
        EightQueen queen = new EightQueen();
        Date start = new Date();
        queen.check(0);
        Date end = new Date();
        System.out.println("正解种类：" + queen.count);
        System.out.println("冲突次数：" + queen.judgeCount);
        System.out.println("耗时："+(end.getTime() - start.getTime())+" ms");
    }
}
