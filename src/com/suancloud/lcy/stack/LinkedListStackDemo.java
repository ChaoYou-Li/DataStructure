package com.suancloud.lcy.stack;


import java.util.LinkedList;
import java.util.Scanner;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/13 0013 10:51
 * Content：
 */
public class LinkedListStackDemo {
    public static void main(String[] args){
        // 初始化模拟栈的操作对象
        LinkedListStack stack = new LinkedListStack();
        NodeStack node1 = new NodeStack(1, "宋江", "及时雨");
        NodeStack node2 = new NodeStack(2, "吴勇", "智多星");
        NodeStack node3 = new NodeStack(3, "林冲", "豹子头");
        NodeStack node4 = new NodeStack(4, "武松", "行者");

        // 测试入栈操作
        System.out.println("测试入栈操作");
        stack.push(node1);
        stack.push(node2);
        stack.push(node3);
        stack.push(node4);
        // 测试显示栈元素操作
        System.out.println("测试显示栈元素操作");
        stack.list();
        // 测试出栈操作
        System.out.println("测试出栈操作");
        System.out.println("出栈数据："+stack.pop());
        System.out.println("出栈数据："+stack.pop());
        System.out.println("出栈数据："+stack.pop());
        System.out.println("出栈数据："+stack.pop());
//        stack.list();
        stack.push(node3);
        stack.push(node4);
        stack.list();


    }
}

// 构建模拟栈的操作类
class LinkedListStack{
    // 实例化模拟栈的头结点
    private NodeStack head = new NodeStack(5, 0);

    public NodeStack getHead() {
        return head;
    }

    // 栈满
    public boolean isFull(){
        return head.top == head.maxSize - 1;
    }

    // 栈空
    public boolean isEmpty(){
        return head.top == -1;
    }

    // 显示链表的方法
    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        NodeStack temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println("栈中的数据："+temp);
            temp = temp.next;
        }
    }

    // 入栈
    public void push(NodeStack node){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        NodeStack temp = head;
        head.top ++;
        for (int i=0; i<head.top; i++){
            temp = temp.next;
        }
        temp.next = node;
        System.out.println("入栈数据："+node);
    }

    // 出栈
    public NodeStack pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        NodeStack temp = head;
        for (int i=0; i<head.top; i++){
            temp = temp.next;
        }
        NodeStack value = temp.next;
//        temp.next = temp.next.next;
        head.top --;
        return value;
    }
}

// 使用链表的形式模拟栈
class NodeStack{
    int top = -1;
    int maxSize;
    int no;
    String name;
    String nickName;
    NodeStack next;  // 指向下一个节点的指针

    // 构造器

    public NodeStack(int maxSize, int no) {
        this.maxSize = maxSize;
        this.no = no;
    }

    public NodeStack(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    // 重写一个toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
