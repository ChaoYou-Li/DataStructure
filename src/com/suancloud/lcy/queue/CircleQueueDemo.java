package com.suancloud.lcy.queue;

import java.util.Scanner;

/**
 * @author chaoyou
 * @email lichaoyou@suancioud.cn
 * @date 2019-9-7 16:27
 * @Description 这是一个环形队列的实现类
 *
 * 使用数组模拟环形队列的思路分析
 * 		a、front(初始值0)变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素。
 * 		b、rear(初始值0)变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置。因为希望空出一个空间作为约定。
 * 		c、当队列满时，条件是：(rear + 1) % maxSize == front【满】
 * 		d、当队列为空时的条件是：(rear + 1) == front【空】
 * 		e、当我们这样分析，队列中有效的数据的个数：(rear + maxSeize - front) % maxSize
 * 		f、在上面几个条件下就可以在原来的队列进行改造得到一个环形队列。
 */
public class CircleQueueDemo {
    public static void main(String[] args) {
        // 初始化环形队列的构造器
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


class CircleArray<T> {
    private int maxSize;
    // front(初始值0)变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素。
    private int front;
    // rear(初始值0)变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置。因为希望空出一个空间作为约定。
    private int rear;
    private T[] arr;

    // 创建队列的构造器
    public CircleArray(int maxSize, T[] arr) {
        this.maxSize = maxSize;
        this.arr = arr;
    }

    // 编一个判断队列是否满的方法
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        arr[rear] = data;   // 因为rear 已经是队列最后一个元素的后一个位置，所以不需要再后移一位再赋值
        /**
         * 就相当于数学里面：
         *          rear ++;
         *          rear = rear % maxSix;
         */
        rear = (rear + 1) % maxSize;    // 注意：rear 取模行程环路，赋值完成后再后移一位，为下一次入队做准备。
    }

    // 编写一个数据取出队列的方法
    public T getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取出数据！");
        }
        /**
         * 这里需要分析出front 是指向队列的第一个元素
         *      1、先把front 对应的值保留到一个临时变量
         *      2、将front 后移，考虑取模
         *      3、将临时变量的值返回
         */
        T value = arr[front];
        /**
         * 就相当于数学里面：
         *          front ++;
         *          front = front % maxSix;
         */
        front = (front + 1) % maxSize;
        return value;
    }

    // 编写一个显示队列里面所有数据的方法
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据！");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i=0; i<size(); i++){
            // 避免越界现象
            sb.append(arr[i % maxSize]);
            sb.append(", ");
        }
        // sb.length()-2，因为“，”后面有一个空格
        String str = sb.substring(0, sb.length()-2);
        System.out.println(str + " ]");
    }

    // 编写一个返回环形队列有效数据个数的方法
    public int size(){
        /**
         * 1、当rear == front 时队列均为空
         * 2、就是数学里面的 |rear - front|
         * 3、只要rear != front 就有有效数据
         */
        return (rear + maxSize - front) % maxSize;
    }

    // 编写一个显示队列头数据的方法
    public T peek(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front];  // 因为front本来就是队列的头数据
    }
}