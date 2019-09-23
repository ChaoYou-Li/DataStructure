package com.suancloud.lcy.linkedlist;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/11 0011 22:11
 * Content：这是双向链表的练习类
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args){
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "吴勇", "智多星");
        HeroNode2 node3 = new HeroNode2(3, "林冲", "豹子头");
        HeroNode2 node4 = new HeroNode2(4, "武松", "行者");
        HeroNode2 node5 = new HeroNode2(4, "李逵", "黑旋风");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.addNode(node1);
//        doubleLinkedList.addNode(node4);
//        doubleLinkedList.addNode(node2);
//        doubleLinkedList.addNode(node3);
//        System.out.println("测试双向链表的添加功能");
//        doubleLinkedList.list();
//        System.out.println("测试双向链表的更新功能");
//        doubleLinkedList.updateNode(node5);
//        doubleLinkedList.list();
//        System.out.println("测试双向链表的更新功能");
//        doubleLinkedList.deleteNode(3);
//        doubleLinkedList.list();
        System.out.println("测试双向链表的顺序添加功能");
        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node3);
        doubleLinkedList.list();

    }
}


/**
 * b、分析：
 *      1、遍历方式和单向链表一样，只是可以向前查找也可以向后查找
 *      2、添加(默认添加到双向链表的最后)
 * 		    ①先找到双向节点的最后一个节点
 * 		    ②temp.next = newNode
 * 		    ③newNode.pre = temp
 *      3、修改思路和原来的单向链表一样
 *      4、删除
 * 		    ①因为是双向链表，因此我们可以实现直接自我删除某个节点
 * 		    ②直接找到要删除的这个节点，比如temp
 * 		    ③temp.pre.next = temp.next
 * 		    ④temp.next.pre = temp.next(注意：若删除的是最后一个节点会报空指针异常)
 */
class DoubleLinkedList{
    HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    // 1、遍历方式和单向链表一样，只是可以向前查找也可以向后查找
    public void list(){
        HeroNode2 temp = head.next; //直接指向有效节点
        if (temp == null){
            throw new RuntimeException("链表为空");
        }
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 2、添加(默认添加到双向链表的最后)
     *      ①先找到双向节点的最后一个节点
     *      ②temp.next = newNode
     *      ③newNode.pre = temp
     */
    public void addNode(HeroNode2 node){
        HeroNode2 temp = head;
        while (true){
            if (temp.next == null){ // ①先找到双向节点的最后一个节点
                break;
            }
            temp = temp.next;
        }
        // ②temp.next = newNode
       temp.next = node;
        // ③newNode.pre = temp
        node.pre = temp;
    }


    // 3、修改思路和原来的单向链表一样
    public void updateNode(HeroNode2 node){
        if (head.next == null){
            throw new RuntimeException("链表为空");
        }
        HeroNode2 temp = head.next;
        boolean flag = false;   // 判别修改节点是否存在
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == node.no){
                flag = true;    // 存在要修改的节点
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = node.name;
            temp.nickName = node.nickName;
        }
        System.out.println("要修改的节点不存在");

    }


    /**
     * 4、删除
     *      ①因为是双向链表，因此我们可以实现直接自我删除某个节点
     *      ②直接找到要删除的这个节点，比如temp
     *      ③temp.pre.next = temp.next
     *      ④temp.next.pre = temp.pre(注意：若删除的是最后一个节点会报空指针异常)
     */
    public void deleteNode(int no){
        if (head.next == null){
            throw new RuntimeException("链表为空");
        }
        HeroNode2 temp = head.next;
        boolean flag = false;   // 判别修改节点是否存在
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;    // 存在要修改的节点
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }
    }


    // 按人物编号大小的顺序添加到链表
    public void addByOrder(HeroNode2 node){

        HeroNode2 temp = head;
        boolean flag = false; // 判别链表中是否已存在该节点
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > node.no){
                break;
            } else if (temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("链表中已存在该节点");
            return;
        }
        node.next = temp.next;
        temp.next = node;
        node.pre = temp;
        temp.pre = node;
    }

}



// 定义一个HeroNode，每个HeroNode 对象就是一个节点
class HeroNode2 {
    int no;
    String name;
    String nickName;
    HeroNode2 pre;   // 指向前一个节点的指针
    HeroNode2 next;  // 指向下一个节点的指针

    // 构造器

    public HeroNode2(int no, String name, String nickName) {
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
