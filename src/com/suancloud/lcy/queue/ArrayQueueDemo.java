package com.suancloud.lcy.queue;

import java.util.Scanner;

/**
 * @author chaoyou
 * @email lichaoyou@suancioud.cn
 * @date 2019-9-7 14:23
 * @Description 这是用数组来模拟队列的实现类
 *
 *
 * 6、队列：是一个有序列表，可以用数组或是链表来实现
 * 		①规则：遵循先入先出的原则。即，先存入对列的数据，要先被取出，后存入队列的数据要被后取出。
 * 		②结构：因为队列的输出输入分别是从前后端来处理，因此需要两个变量front(队首)、rear(队尾)，
 * 			   分别记录队列前后端的下标，front会随着数据输出而变化，而rear则是随着数据的输入而变化。
 * 		③front：当数据出队的时候自增1，数据入队不变化
 * 		④rear：当数据入队的时候自增1，数据出队不变化
 *
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 初始化数组队列
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3, new Integer[3]);
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

/**
 * 使用数组模拟一个队列编写一个ArrayQueue 类
 *      1、应用场景：银行排队案例
 *      2、问题分析并优化
 *          ①目前数组使用一次就不能再使用，没有达到复用的效果
 *          ②将这个数组使用算法，改进成一个环形数组 %（取模方式）
 *
 * @param <T> 泛型
 */
class ArrayQueue<T> {
    private int maxSize;
    private int front;
    private int rear;
    private T[] arr;

    // 创建队列的构造器
    public ArrayQueue(int maxSize, T[] arr) {
        this.maxSize = maxSize;
        this.front = -1;    // 指向队列头部，分析出front是指向队列头的前一个位置。
        this.rear = -1;     // 指向队列尾部，指向队列尾的数据(即就是队列的最后一个数据)
        this.arr = arr;
    }

    // 编一个判断队列是否满的方法
    public boolean isFull(){
        if (maxSize == 0){
            return true;
        }
        return rear == maxSize - 1;
    }

    // 编一个判断队列是否为空的方法
    public boolean isEmpty(){
        if (front == rear){
            return true;
        }
        return false;
    }

    // 编写一个数据进入队列的方法
    public void addQueue(T data){
        if (isFull()){
            System.out.println("队列已满，不能再加入数据！");
            return;
        }
        rear ++;    // 表明有数据要入队
        arr[rear] = data;

    }

    // 编写一个数据取出队列的方法
    public T getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取出数据！");
        }
        front ++;
        return arr[front];
    }

    // 编写一个显示队列里面所有数据的方法
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据！");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i=0; i<arr.length; i++){
            sb.append(arr[i]);
            sb.append(", ");
        }
        // sb.length()-2，因为“，”后面有一个空格
        String str = sb.substring(0, sb.length()-2);
        System.out.println(str + " ]");
    }

    // 编写一个显示队列头数据的方法
    public T peek(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front+1];
    }

}
