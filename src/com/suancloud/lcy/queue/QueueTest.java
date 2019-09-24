package com.suancloud.lcy.queue;

import java.util.Scanner;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/21 0021 12:26
 * Content：
 */
public class QueueTest {
    public static void main(String[] args) {
        // 测试普通数组队列
//        ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3, new Integer[3]);
        // 测试环形数组队列
        CircleArray<Integer> queue = new CircleArray<>(4, new Integer[4]);
        char key = ' '; // 用于接收用户输入的标识字符
        Scanner scanner = new Scanner(System.in);   // 创建一个用户输出窗
        boolean loop = true;
        // 输出一个选择菜单
        while (loop){
            System.out.println("s(show):显示队列数据");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):入队一个数据");
            System.out.println("g(get):出队一个数据");
            System.out.println("h(peek):显示队列头的数据");
            key = scanner.next().charAt(0);   // 接收用户的输入
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int val = queue.getQueue();
                        System.out.println("出队的数据是："+val);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int h = queue.peek();
                        System.out.println("队列的头数据："+h);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~~");
    }
}
