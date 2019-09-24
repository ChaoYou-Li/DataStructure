package com.suancloud.lcy.linkedlist;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/12 0012 21:08
 * Content：
 */
public class Josephu {
    public static void main(String[] args){
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        System.out.println("测试环形链表的添加功能");
        linkedList.addBoy(10);
        linkedList.list();
        System.out.println("测试出圈小孩编号");
        linkedList.countBoy(1, 2, 10);
    }
}

/**
 * 单向链表实现：用一个不带头结点的循环链表来处理josephu 问题
 * 	    ①先构成一个有n个节点的单循环链表
 * 	    ②然后由k节点开始计数，记到m时，对应节点从链表中删除
 * 	    ③然后再从被删除节点的下一个节点又从1开始计数到m又删除
 * 	    ④依次循环直到节点从链表中全部删除算法结束
 */
class CircleSingleLinkedList{

    // ①先创建第一个节点，让first指向该节点，并形成环形
    Boy first = null;

    /**
     * 构建单向的环形链表思路
     *      ①先创建第一个节点，让first指向该节点，并形成环形
     *      ②后面当我们每创建一个新的节点，就把该节点加入到已有的环形链表中即可
     */
    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("小孩数量太少了");
            return;
        }
        Boy curBoy = null;  // 辅助节点，临时存放最后一个小孩
        for (int i=1; i<=nums; i++){
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1){
                // 让first指向该节点，并形成环形
                first = boy;
                first.setNext(first);
                curBoy = first; // 让curBoy 指向第一个小孩
            } else {
                curBoy.setNext(boy);    // 让前一个小孩的next 指向新加入的小孩
                boy.setNext(first);    // 让新加入小孩的next 指向first 小孩，再次成环型
                curBoy = boy;   // 辅助指针指向新加入小孩
            }
        }
    }


    /**
     * 遍历环形链表
     *      ①先让一个辅助指针(变量)，指向first节点
     *      ②然后通过一个while循环遍历该环形链表即可 curBoy.next == first
     */
    public void list(){
        // 先判断链表是否为空
        if (first == null){
            System.out.println("环形链表为空");
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号【%d】\n", curBoy.getNo());
            // 判断链表是否遍历完
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


    /**
     * 约瑟夫问题：
     *      设编号为1,2,3....n个人围坐成一圈，约定编号为k(1<=k<=n)的人开始报数，数到m的那个人出列，
     * 	    它的下一位又从1开始数，数到m的那个人又出列，依次类推，直到所有人都出列位置，由此产生的一个出队编号的序列。
     * 	        如：
     * 	            n = 10  总数10人
     * 	            k = 1 从1号开始数数
     * 	            m = 3   每次数到3的人出队
     *
     * 思路：
     *      1、首先创建一个辅助指针(变量)helper，指向着first 的前一个节点
     *      2、事先应该让helper 指向环形链表的最后一个节点
     *      3、小孩报数前，先把first和helper 移动k - 1 次
     *      4、小孩开始循环报数出队
     *      5、当小孩报数时，让first 和 helper 指针同时的移动 m-1 次。
     *      6、这时就可以将first 指向的小孩节点出圈
     *          first = first.next
     *          helper.next = first(这样原来first 指向的节点就没有任何引用，就会被回收了)
     *
     * @param startNo   k
     * @param count    m
     * @param nums    n
     */
    public void countBoy(int startNo, int count, int nums){
        if (first == null || count < 1 || startNo > nums){
            System.out.println("参数输入有误！");
            return;
        }

        // 1、需求创建一个辅助指针(变量)helper
        Boy helper = first;
        // 2、事先应该让helper 指向环形链表的最后一个节点
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        // 3、小孩报数前，先把first和helper 移动k - 1 次
        for (int i=0; i<startNo-1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }

        // 4、小孩开始循环报数出队
        while (true){
            // 判断链表是否只有一个节点了
            if (helper == first){
                break;
            }
            // 5、当小孩报数时，让first 和 helper 指针同时的移动 m-1 次。
            for (int i=0; i<count-1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("出队的小孩编号【%d】\n", first.getNo());
            // 6、这时就可以将first 指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("出队的小孩编号【%d】\n", first.getNo());
    }


}


// 创建一个Boy 类，表示一个节点
class Boy{
    private int no; // 编号
    private Boy next;   // 指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
