package com.suancloud.lcy.recursion;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/15 0015 11:28
 * Content：这是迷宫找出路问题的实现类
 */
public class MiGong {
    /**
     * 12、递归调用机制的讲解
     *      a、概念：递归就是方法自己调用自己，每次调用时传入不同的变量，
     *                  递归有助于编程者解决复杂问题，同时可以让代码变得简洁，但是会增加计算的时间复杂度。
     *      b、应用场景：迷宫问题(回溯)，递归(recursion)
     *      c、调用规则：
     *          ①当程序每递归执行一次时，就会开辟一个独立的空间(栈)
     *          ②每个空间的数据(局部变量)都是独立的，不会互相影响
     *          ③如果方法中使用的是引用类型(数组)，就会共享该引用类型的数据
     *          ④递归必须向退出递归的条件逼近，否则就是无限递归(死递归)，出现StackOverflowError
     *          ⑤当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，
     *            同时当方法执行完毕或者返回时，该方法也就执行完毕。
     *          ⑥每次开辟的空间都是嵌套在当前递归执行的空间内
     *          ⑦所有空间(栈)的执行顺序是由内到外(每个空间被执行完都会被销毁)直到最外层。
     *      d、可解决问题：
     *          ①八皇后问题、汉诺塔、阶乘问题、迷宫问题、球和篮子问题(Google编程大赛)
     *          ②各种算法中也会使用到递归，比如快速排序、归并排序、二分查找、分治算法等
     *          ③将用栈解决问题—>递归代码比较简洁
     *
     * 迷宫问题总结：
     *      1、小球得到的路径和程序员设置的找路策略有关，找路的上下左右的顺序有关
     *      2、在得到小球路径时，可以先使用(下右上坐)，再改成(上右下左)看看路径是不是有变化
     *      3、测试回溯现象
     *      4、思考：如何求出最短路径？
     */

    public static void main(String[] args){
        /**
         * 实现：创建迷宫地图
         *
         * 步骤：
         *      1、创建一个二维数组
         *      2、把二维数组的四周设置墙(设置为0)
         *      3、在迷宫中设置挡板
         */
        // 1、创建一个二维数组
        int[][] map = new int[8][8];
        // 2、把二维数组的四周设置墙(设置为0)
        for (int i=0; i<map.length; i++){
            // 设计左右墙
            map[i][0] = 1;
            map[i][map[i].length-1] = 1;
        }
        for (int j=0; j<map[0].length; j++){
            // 设计上下墙
            map[0][j] = 1;
            map[map.length-1][j] = 1;
        }
        // 3、在迷宫中设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        // 展示迷宫地图
        System.out.println("展示迷宫地图：");
        for (int i=0; i<map.length; i++){
            for (int j=0; j<map[i].length; j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }

        // 使用递归回溯给小球找路
        setWay(map, 1, 1);
        System.out.println("小球找到路后标识的地图：");
        for (int i=0; i<map.length; i++){
            for (int j=0; j<map[i].length; j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }
    }


    /**
     * 实现：使用递归回溯来给小球在迷宫中找出路
     *
     * 步骤：
     *      1、map 表示地图
     *      2、i、j 表示从地图的哪个位置开始出发(1, 1)
     *      3、如果小球能到map[map.length -2][map[map.length -2].length -2]
     *      4、约定策略：当map[i][j] 为0 表示该点没有走过，1 表示墙，2 表示通路可以走，
     *         3 表示走过，走不通(这个策略是能否在迷宫中找到出路或者找到路径的长短的关键)
     *      5、在走迷宫时，需要确定一个策略(方法) 下 —> 右 -> 上 -> 左，如果该点走不通，再回溯
     */
    public static boolean setWay(int[][] map, int i, int j){
        boolean flag = false;
        if (map[map.length -2][map[map.length -2].length -2] == 2){
            // 通路已经找到了
            flag = true;
        } else { // 还在找路
            if (map[i][j] == 0){    // 当前点还没有走过
                // 下 —> 右 -> 上 -> 左，如果该点走不通，再回溯
                map[i][j] = 2;  // 假设当前可以走通
                if (setWay(map, i + 1, j)){
                    // 往下走得通
                    return true;
                } else if (setWay(map, i, j + 1)){
                    // 往右走得通
                    return true;
                } else if (setWay(map, i - 1, j)){
                    // 往上走得通
                    return true;
                } else if (setWay(map, i, j - 1)){
                    // 往左走得通
                    return true;
                } else {
                    // 该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {    // 当前点已经走过了
                flag = false;
            }
        }
        return flag;
    }
}
