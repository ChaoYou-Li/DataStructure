package com.suancloud.lcy.linkedlist;

/**
 * @author chaoyou
 * @email lichaoyou@suancioud.cn
 * @date 2019-9-7 18:28
 * @Description 这是用来实现链表数据结构的实现类
 *
 * a、链表是以节点的方式来存储
 * 		b、每个节点包含data域，next域：指向下一个节点
 * 		c、链表的各个节点不一定是连续的存储
 * 		d、链表分带头节点的链表和没有头结点的链表，根据实际需求来确定
 * 		e、业务：把水浒传里的人物采用链表的方式展现出来
 * 		f、节点结构
 * 			class HeroNode{
 * 				int no;
 * 				String name;
 * 				String nickName;
 * 				HeroNode next;
 *         }
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

    }
}

// 定义一个SingleLinkedList 来管理我们的HeroNode 节点
class SingleLinkedList {
    // 先初始化一个头结点，头结点不能动
    private HeroNode heed = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     *
     * 思路：当不考虑编号顺序的时候
     *      1、找到当前链表的最后节点
     *      2、将最后节点的next 指向新的节点
     */




}

// 定义一个HeroNode，每个HeroNode 对象就是一个节点
class HeroNode {
    int no;
    String name;
    String nickName;
    HeroNode next;  // 指向下一个节点的指针

    // 构造器

    public HeroNode(int no, String name, String nickName) {
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
                ", next=" + next +
                '}';
    }
}



