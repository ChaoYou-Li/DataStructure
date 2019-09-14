package com.suancloud.lcy.stack;

import java.util.Scanner;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/12 0012 23:30
 * Content：这是使用数组来模拟栈的实现类
 */
public class ArrayStackDemo {
    public static void main(String[] args){
        // 初始化模拟栈
        ArrayStack stack = new ArrayStack(5);
        System.out.println("测试使用模拟：");
        String key = ""; // 用于接收用户输入的标识字符
        Scanner scanner = new Scanner(System.in);   // 创建一个用户输出窗
        boolean loop = true;
        // 输出一个选择菜单
        while (loop){
            System.out.println("show:显示栈数据");
            System.out.println("exit:退出程序");
            System.out.println("push:入栈一个数据");
            System.out.println("pop:出栈一个数据");
            System.out.println("请输入你的操作");
            key = scanner.next();   // 接收用户的输入
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入入栈数据：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int val = stack.pop();
                        System.out.println("出栈的数据是："+val);
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
 * e、思路：
 *      ①使用数组来模拟栈
 *      ②定义一个top 来表示栈顶，初始化为-1
 *      ③入栈操作，当有数据入栈时，top++；stack[top] = data
 *      ④出栈操作，当有数据出栈时，int value = stack[top]；top--；return value
 */

// ①使用数组来模拟栈
class ArrayStack{
    private int maxSize;    // 栈的最大容量
    private int[] stack;  // 模拟栈的数组
    private int top = -1;    // 模拟栈顶指针

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈
    public void push(int data){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        top ++;
        stack[top] = data;
    }

    // 出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top --;
        return value;
    }

    // 遍历栈的元素，需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈为空");
        }
        for (int i=top; i>=0; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}
