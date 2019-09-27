package com.suancloud.lcy.search;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/26 0026 22:16
 * Content：
 */
public class HashTabDemo {
    public static void main(String[] args){
        HashTab<String, Employee> table = new HashTab<>(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("put：   添加员工");
            System.out.println("get：   查找员工");
            System.out.println("list：  展示员工");
            System.out.println("remove：删除员工");
            System.out.println("exit：  退出系统");
            key = scanner.next();
            switch (key){
                case "put":
                    System.out.println("请输入ID");
                    int id = scanner.nextInt();
                    System.out.println("请输入name");
                    String name = scanner.next();
                    System.out.println("请输入age");
                    int age = scanner.nextInt();
                    Employee employee = new Employee(id, name, age);
                    System.out.println("请输入标记key");
                    String key1 = scanner.next();
                    table.put(key1, employee);
                    break;
                case "get":
                    System.out.println("请输入查找的key标记");
                    String k = scanner.next();
                    table.get(k);
                    break;
                case "list":
                    table.list();
                    break;
                case "remove":
                    System.out.println("请输入删除的key标记");
                    String ke = scanner.next();
                    table.remove(ke);
                    break;
                case "exit":
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 注解：创建一个HashTab 管理多条链表
 */
class HashTab<E, T>{
    private int size;
    private EmpLinkedList<E, T>[] empLinkedLists;

    // 构造器
    public HashTab(int size) {
        // 初始化tab 大小
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i=0; i<size; i++){
            empLinkedLists[i] = new EmpLinkedList<E, T>();
        }
    }

    /**
     * 设置一个散列函数用来计算key的hash
     */
    public int hashFun(E key){
        int num = key.hashCode();
        return num % size;
    }

    /**
     * 编写一个添加数据的方法
     */
    public void put(E key, T obj){
        int empLinkedListNo = hashFun(key);
        empLinkedLists[empLinkedListNo].add(key, obj);
    }

    /**
     * 编写一个遍历tab的方法
     */
    public void list(){
        for (int i=0; i<size; i++){
            empLinkedLists[i].list(i);
        }
    }

    /**
     * 编写一个根据key 查找节点的方法
     */
    public void get(E key){
        int empLinkedListNo = hashFun(key);
        Object data = empLinkedLists[empLinkedListNo].get(key);
        System.out.println("查找数据："+data.toString());
    }

    /**
     * 编写一个移除节点的方法
     */
    public int remove(E key){
        int empLinkedListNo = hashFun(key);
        return empLinkedLists[empLinkedListNo].remove(key);
    }
}

/**
 * 注解：编写一个以EmpNode为节点的链表
 */
class EmpLinkedList<E, T>{
    EmpNode head = null;

    /**
     * 设置链表的普通添加方法
     */
    public void add(E key, T object){
        EmpNode<E, T> node = new EmpNode<>();
        node.setKey(key);
        node.setData(object);
        // 先判断链表为空
        if (head == null){
            // 直接插入到head后面
            head = node;
            return;
        }
        // 链表不为空，利用辅助节点遍历链表到最后节点
        EmpNode temp = head;
        boolean flag = false;
        while (true){
            // 已找到最后节点
            if (null == temp.getNext()){
                break;
            }
            // 当前key已经存在
            if (temp.getKey().equals(key)){
                flag = true;
            }
            temp = temp.getNext();
        }
        if (flag){
            // key存在，可直接覆盖data域
            temp.setData(object);
        } else {
            // key不存在，新建节点
            temp.setNext(node);
        }
    }

    /**
     * 设置查看链表节点的方法
     */
    public void list(int no){
        // 先判断链表为空
        if (head == null){
            System.out.println("链表"+(no+1)+"为空~~~~");
            return;
        }
        // 链表不为空，利用辅助节点遍历链表到最后节点
        EmpNode temp = head;
        StringBuffer buffer = new StringBuffer();
        buffer.append("链表"+(no+1)+"数据：");
        while (true){
            buffer.append(temp.getData().toString());
            buffer.append("=>");
            if (null == temp.getNext()){
                buffer.append(temp.getData().toString());
                break;
            }
            temp = temp.getNext();
        }
        System.out.println(buffer.toString());
    }

    /**
     * 注解：设置获取节点信息的方法
     */
    public Object get(E key){
        // 先判断链表为空
        if (head == null) {
            System.out.println("链表为空~~~~~");
            return null;
        }
        // 不为空，遍历链表找到目标节点
        EmpNode temp = head;
        boolean flag = false;
        while (true){
            // 找到目标节点
            if (temp.getKey().equals(key)){
                flag = true;
                break;
            }
            // 已找到最后节点
            if (temp.getNext() == null){
                break;
            }
            temp = temp.getNext();
        }
        if (flag){
            // 返回节点的data域
            return temp.getData();
        }
        return null;
    }


    /**
     * 注解：设置一个移除链表节点的方法
     */
    public int remove(E key){
        // 先判断链表为空
        if (head == null) {
            System.out.println("链表为空~~~~~");
            return 0;
        }
        // 不为空，遍历链表找到目标节点
        EmpNode temp = new EmpNode();
        temp.setNext(head);
        boolean flag = false;
        while (true){
            if (temp.getNext().getNext() == null){
                break;
            }
            // 找到目标节点
            if (temp.getNext().getKey().equals(key)){
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag){
            temp.setNext(temp.getNext().getNext());
            return 1;
        }
        return 0;
    }
}


/**
 * 注解：编写一个存放员工对象的节点
 */
class EmpNode<E, T>{
    // 创建一个头指针head，直接指向第一个Emp
    private T data;
    private E key;
    private EmpNode next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public E getKey() {
        return key;
    }

    public void setKey(E key) {
        this.key = key;
    }

    public EmpNode getNext() {
        return next;
    }

    public void setNext(EmpNode next) {
        this.next = next;
    }
}

/**
 * 注解：构建员工的实体类
 */
class Employee{
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    // 初始化员工信息
    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}