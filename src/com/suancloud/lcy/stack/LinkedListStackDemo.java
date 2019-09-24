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
        Node node1 = new Node(1, "宋江", "及时雨");
        Node node2 = new Node(2, "吴勇", "智多星");
        Node node3 = new Node(3, "林冲", "豹子头");
        Node node4 = new Node(4, "武松", "行者");

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

/**
 * 链栈的实现类
 * 思想：(链尾为栈底，链头为栈顶)
 *      ①栈空条件：头结点.next == null
 *      ②栈满条件：由于只有内存溢出时才会出现栈满情况，通常不比考虑链栈满情况。
 *      ③进栈操作：参数节点插入到头结点之后
 *          data.next = head.next
 *          head.next = data
 *      ④出栈操作：把头结点.next 节点取出返回
 *          temp = head.next
 *          head.next = temp.next
 *
 * 优点：不存在栈满溢出问题(除非内存溢出)，规定栈的所有操作都是在单链表的表头进行操作(因为给定链栈后，
 *      也拿到了头结点，在其后面出入一个新的节点和删除首节点都十分方便，对应算法复杂度为O(1))
 *
 */
class LinkedListStack{
    // 实例化模拟栈的头结点
    private Node head = new Node();

    public Node getHead() {
        return head;
    }

    // 栈空
    public boolean isEmpty(){
        return head.next == null;
    }

    /**
     * 显示链表的方法
     *
     * 步骤：
     *      1、判断是否栈空
     *      2、利用辅助节点遍历链栈
     */
    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        Node temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println("栈中的数据："+temp);
            temp = temp.next;
        }
    }

    /**
     * ③进栈操作：参数节点插入到头结点之后
     *      data.next = head.next
     *      head.next = data
     */
    public void push(Node node){

        Node temp = head;
        node.next = temp.next;
        temp.next = node;
        System.out.println("入栈数据："+node);
    }

    /**
     * ④出栈操作：把头结点.next 节点取出返回
     *      temp = head.next
     *      head.next = temp.next
     */
    public Node pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        Node temp = head;
        Node value = temp.next;
        temp.next = temp.next.next;
        return value;
    }
}

// 使用链表的形式模拟栈
class Node{
    int no;
    String name;
    String nickName;
    Node next;  // 指向下一个节点的指针

    public Node() {
    }

    // 构造器
    public Node(int no, String name, String nickName) {
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
