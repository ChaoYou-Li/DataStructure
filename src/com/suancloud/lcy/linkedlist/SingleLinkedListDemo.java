package com.suancloud.lcy.linkedlist;

import java.util.Stack;
import java.util.prefs.BackingStoreException;

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
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "吴勇", "智多星");
        HeroNode node3 = new HeroNode(3, "林冲", "豹子头");
        HeroNode node4 = new HeroNode(4, "武松", "行者");
        HeroNode node5 = new HeroNode(5, "鲁智深", "花和尚");
        HeroNode node6 = new HeroNode(6, "张顺", "浪里白条");
//        HeroNode node5 = new HeroNode(4, "花荣", "小李广");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node1);
//        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node5);
        singleLinkedList.addByOrder2(node6);
        singleLinkedList.addByOrder2(node2);
        singleLinkedList.addByOrder2(node4);

        System.out.println("这是链表1");
        singleLinkedList.list();
        System.out.println("这是链表2");
        singleLinkedList.list2();

//        System.out.println("编号查询结果："+singleLinkedList.get(1));
//        System.out.println("更新节点结果："+singleLinkedList.update(node5));
//        singleLinkedList.list();
//        singleLinkedList.delete(3);
//        singleLinkedList.list();

        // 查看是否排序了
//        singleLinkedList.addByOrder(node4);
//        singleLinkedList.addByOrder(node2);
//        singleLinkedList.addByOrder(node1);
//        singleLinkedList.addByOrder(node3);
//        singleLinkedList.addByOrder(node4);
//        singleLinkedList.list();
//        System.out.println("删除了："+singleLinkedList.delete(4));
//        singleLinkedList.list();
        ExerciseTest exerciseTest = new ExerciseTest();
//        System.out.println("链表的有效个数："+exerciseTest.getLength(singleLinkedList.getHeed()));
//        System.out.printf("倒数第【%d】个的节点是："+exerciseTest.getLastIndexNode(singleLinkedList.getHeed(), 1), 1);
        // 测试一下反转方法
//        exerciseTest.inversion(singleLinkedList.getHeed());

        // 测试链表从尾部到头部打印
//        System.out.println("测试链表从尾部到头部打印");
//        exerciseTest.insersePrintf(listkedList2.getHeed());

        // 测试合并两个链表并且排序
        System.out.println("测试合并两个链表并且排序");
        exerciseTest.combineNode(singleLinkedList.getHeed(), singleLinkedList.getHead2());
        singleLinkedList.list();
    }
}

// 定义一个SingleLinkedList 来管理我们的HeroNode 节点
class SingleLinkedList {
    // 先初始化一个头结点，头结点不能动，不存放具体数据
    // 先初始化一个头结点，头结点不能动，不存放具体数据
    private HeroNode heed = new HeroNode(0, "", "");
    private HeroNode head2 = new HeroNode(0, "", "");

    public HeroNode getHeed() {
        return heed;
    }

    public HeroNode getHead2() {
        return head2;
    }

    /**
     * 添加节点到单向链表
     *
     * 思路：当不考虑编号顺序的时候
     *      1、找到当前链表的最后节点
     *      2、将最后节点的next 指向新的节点
     *      3、参数HeroNode 为新节点
     */
    public void addNode(HeroNode heroNode){

        // 1、找到当前链表的最后节点(只有最后节点的next 才为null)
        HeroNode temp = heed;
        while (true){
            if (temp.next == null){     // 表示已找到最后节点
                break;
            }
            // 当前节点不是最后节点，就往后移动一个位置接着找直到把最后节点找到（全链查询效率低下————链表的缺点）
            temp = temp.next;
        }

        // 2、将最后节点的next 指向新的节点
        temp.next = heroNode;
    }


    /**
     * 注解：显示【遍历】链表的方法
     *
     * 步骤：
     *      1、根据头结点判断链表是否为空
     *      2、遍历链表依次打印每一个节点信息
     */
    public void list(){
        // 用一个辅助节点帮助遍历
        HeroNode temp = heed.next;

        // 先判断链表是否为空
        if (temp == null){
            System.out.println("链表为空");
            return;
        }
        // 链表不为空就开始遍历
        while (true){
            // 先判断是否还存在下一个节点
            if (temp == null){
                break;
            }
            // 打印控制台
            System.out.println(temp);
            // 把节点位置往后移
            temp = temp.next;
        }
    }


    /**
     * 注解：根据节点编号查询链表是否存在该节点
     *
     * 步骤：
     *      1、根据头结点判断链表是否为空
     *      2、遍历链表拿到每一个节点对比参数no
     *      3、返回该节点
     */
    public HeroNode get(int no){
        // 用一个辅助节点帮助遍历
        HeroNode temp = heed.next;
        // 先判断链表是否为空
        if (temp == null){
            throw new RuntimeException("链表为空");
        }
        // 链表不为空就开始遍历
        while (true){
            // 先判断是否还存在下一个节点
            if (temp.no == no){
                return temp;
            }
            // 把节点位置往后移
            temp = temp.next;
        }
    }


    /**
     * 注解：根据节点编号先查询链表是否存在该节点，再对该节点进行更新
     *
     * 步骤：
     *      1、根据头结点判断链表是否为空
     *      2、遍历链表拿到每一个节点对比参数节点的no
     *      3、判断链表中是否存在该节点
     *      4、修改链表中节点信息
     *      5、返回修改后的节点
     */
    public HeroNode update(HeroNode heroNode){
        // 1、根据头结点判断链表是否为空
        if (heed.next == null){
            throw new RuntimeException("链表为空");
        }

        // 2、遍历链表拿到每一个节点对比参数节点的no
        HeroNode temp = heed.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;  // 遍历完成
            }
            // 拿到每一个节点对比参数节点的no
            if (temp.no == heroNode.no){
                flag = true;    // 找到要修改节点
                break;
            }
            temp = temp.next;
        }

        // 3、判断链表中是否存在该节点
        if (!flag){
            System.out.println("链表不存在此此节点");
            return null;
        }

        // 4、修改链表中节点信息
        temp.name = heroNode.name;
        temp.nickName = heroNode.nickName;

        // 5、返回修改后的节点
        return temp;
    }

    /**
     * 注解：根据节点编号先查询链表是否存在该节点，再对节点进行删除
     *
     * 思路：
     *      1、head 节点不能动，因此我们需要一个temp 辅助节点找到待删除的前一个节点
     *      2、把断口接上
     *          跳过删除节点：node.next = node.next.next
     *
     * 步骤：
     *      1、根据头结点判断链表是否为空
     *      2、遍历链表每一个节点.next对比参数no
     *      3、判断链表中是否存在该节点
     *      4、把当前节点.next == 当前节点.next.next
     *      5、返回删除的节点
     */
    public HeroNode delete(int no){
        // 1、根据头结点判断链表是否为空
        if (heed.next == null){
            throw new RuntimeException("链表为空");
        }

        // 2、遍历链表每一个节点.next对比参数no
        HeroNode temp = heed;   // 用一个辅助节点帮助遍历
        boolean flag = false;    // 标识是否存在要删除的节点
        // 链表不为空就开始遍历
        while (true){
            // 先判断链表是否存在下一个节点
            if (temp.next == null){
                break;
            }
            // 每一个节点.next对比参数no
            if (temp.next.no == no){    // 上一个节点找到
                flag = true;    // 目标节点存在
                break;
            }
            temp = temp.next;
        }
        // 3、判断链表中是否存在该节点
        if (!flag){
            System.out.printf("不存在要删除的【%d】\n", no);
            return null;
        }
        // 4、把当前节点.next == 当前节点.next.next
        temp.next = temp.next.next;     // 直接跳过要删除的节点连接下一个节点
        // 5、返回删除的节点
        return temp.next;
    }


    /**
     * 注解：需要按照编号顺序添加
     *      1、首先找到新添加的节点位置，是通过辅助变量(指针)，通过遍历来找到
     *      2、新节点.next = temp.next
     *      3、将temp.next = 新节点
     *
     * 需求：
     *      1、根据排名将英雄插入到指定的位置
     *      2、如果已经存在这个排名，则提示添加失败
     *
     * 步骤：
     *      1、根据头结点判断链表是否为空
     *      2、遍历链表每一个节点，寻找插入位置，以及判断节点是否已存在
     *      3、判断节点是否已存在
     *      4、把参数节点插入找到的位置
     *              新节点.next = temp.next
     *              将temp.next = 新节点
     */
    public void addByOrder(HeroNode node){
        // 1、根据头结点判断链表是否为空
        if (heed.next == null){
            heed.next = node;
            return;
        }

        // 2、遍历链表每一个节点，寻找插入位置，以及判断节点是否已存在
        HeroNode temp = heed;
        boolean flag = false;   // 判断当前英雄的编号在链表中是否已经存在
        while (true){
            if (temp.next == null){  // 说明temp已经是最后节点了
                break;
            }
            // 以及判断节点是否已存在
            if (temp.next.no == node.no){
                flag = true;    // 表明当前英雄的编号在链表中已经存在
                break;
            }
            // 寻找插入位置
            if (temp.next.no > node.no){    // 表明添加节点的位置找到了
                break;
            }
            temp = temp.next;   // 链表指针往后移动
        }
        // 3、判断节点是否已存在
        if (flag){
            System.out.println("当前英雄的编号【"+node.no+"】在链表中已经存在了");
            return;
        }
        // 4、把参数节点插入找到的位置
        node.next = temp.next;  // 新节点.next = temp.next
        temp.next = node;   // 将temp.next = 新节点
    }


    // 用来测试两个链表拼接
    public void addByOrder2(HeroNode node){
        // 1、首先找到新添加的节点位置，是通过辅助变量(指针)，通过遍历来找到
        HeroNode temp = head2;
        boolean flag = false;   // 判断当前英雄的编号在链表中是否已经存在
        while (true){
            if (temp.next == null){  // 说明temp已经是最后节点了
                break;
            }
            if (temp.next.no > node.no){    // 表明添加节点的位置找到了
                break;
            } else if (temp.next.no == node.no){
                flag = true;    // 表明当前英雄的编号在链表中已经存在
                break;
            }
            temp = temp.next;   // 链表指针往后移动
        }
        //
        if (flag){
            System.out.println("当前英雄的编号【"+node.no+"】在链表中已经存在了");
            return;
        }
        // 2、新节点.next = temp.next
        node.next = temp.next;
        // 3、将temp.next = 新节点
        temp.next = node;

    }
    public void list2(){
        // 用一个辅助节点帮助遍历
        HeroNode temp = head2.next;

        // 先判断链表是否为空
        if (temp == null){
            System.out.println("链表为空");
            return;
        }
        // 链表不为空就开始遍历
        while (true){
            // 先判断是否还存在下一个节点
            if (temp == null){
                break;
            }
            // 打印控制台
            System.out.println(temp);
            // 把节点位置往后移
            temp = temp.next;
        }
    }

}



/**
 * 单链表常见的练习题
 *      1、求单链表中有效节点的个数
 *      2、查找单链表中的倒数第k个节点【新浪面试题】
 *      3、单链表的反转【腾讯面试题】
 *      4、从头到尾打印单链表【百度，要求方式1：反向遍历，2：Stack栈】
 *      5、合并两个有序的单链表，合并之后的链表依然有序【课后练习】
 */
class ExerciseTest{

    /**
     * 需求：求单链表中有效节点的个数(不算head)
     *
     * 步骤：
     *      1、先判断是否为空链表
     *      2、利用辅助变量把头结点排除，初始化计数辅助变量
     *      3、遍历链表每一个节点，计数变量自增加1
     *      4、返回计数变量
     */
    public int getLength(HeroNode head){
        // 先判断是否为空链表
        if (head.next == null){
            return 0;
        }
        HeroNode temp = head.next;  // 把头结点排除
        int length = 0;
        while (true){
            if (temp == null){  // 判断链表是否还存在下一个节点
                break;
            }
            length ++;
            temp = temp.next;   // 遍历链表
        }
        return length;
    }


    /**
     * 2、查找单链表中的倒数第k个节点【新浪面试题】
     *
     * 思路：
     *      1、编写一个方法接受head 节点，同时接收一个index
     *      2、index 表示是倒数第index 个节点
     *      3、先把链表从头到尾遍历一遍得到链表的size();
     *      4、得到size() 后，从第一个开始遍历到(size - index) 个，就可以得到结果
     *      5、如果找到就返回，否则就返回null
     *
     * 步骤：
     *      1、根据头结点判断链表是否为空
     *      2、遍历链表得到有效节点个数size
     *      3、判断index(k)范围是否超出size
     *      4、排序头结点遍历链表到第(size - index)个节点
     *      5、返回遍历到的节点
     */
    public HeroNode getLastIndexNode(HeroNode head, int index){
        // 先判断链表是否为空
        if (head.next == null){
            return null;
        }
        // 先把链表从头到尾遍历一遍得到链表的size();
        int size = getLength(head);
        if (index < 0 || index > size){ // 校验index 范围
            return null;
        }
        // 得到size() 后，从第一个有效节点开始遍历到(size - index) 个，就可以得到结果
        HeroNode temp = head.next;
        for (int i=0; i<(size - index); i++){
            temp = temp.next;
        }
        return temp;
    }


    /**
     * 3、单链表的反转【腾讯面试题】
     *
     * 思路：
     *      1、判断链表是否为空
     *      2、创建一个辅助的头结点作为首尾交换的媒介，一个next指针，指向当前节点.next
     *      3、遍历链表的每一个有效节点
     *      4、把next指向当前节点.next
     *      5、把辅助头结点.next 指向当前节点
     *      6、把当前节点指向辅助头结点
     *      7、把链表头结点指向辅助头结点.next
     *
     * 总结：3、4 的效果就是把后面的节点插到前面来
     */
    public void inversion(HeroNode head){
        // 1、判断链表是否为空
        if (head.next == null){
            throw new RuntimeException("链表为空!!!!");
        }
        // 2、创建一个辅助的头结点作为首尾交换的媒介，一个next指针，指向当前节点.next
        HeroNode node = new HeroNode(0, "", "");    // 作为首尾交换的媒介
        HeroNode next = null;   // 指向前节点.next(保证链表不会断)

        // 3、遍历链表的每一个有效节点
        HeroNode temp = head.next;
        while (temp != null){
            // 4、把next指向当前节点.next
            next = temp.next;
            // 5、把辅助头结点.next 指向当前节点
            temp.next = node.next;
            // 6、把当前节点指向辅助头结点
            node.next = temp;

            temp = next;    // 往后移动
        }
        // 7、把链表头结点指向辅助头结点.next
        head.next = node.next;
    }


    /**
     * 4、从尾到头打印单链表【百度，要求方式1：反向遍历，2：Stack栈】
     *
     * 思路：
     *      1、判断链表是否为空
     *      2、创建一个Stack栈集合
     *      3、把链表遍历按顺序把节点放入到栈中
     *      4、再遍历栈，取出数据即可
     */
    public void insersePrintf(HeroNode head){
        // 1、判断链表是否为空
        if (head.next == null){
            throw new RuntimeException("链表为空");
        }
        // 2、创建一个Stack栈集合
        Stack<HeroNode> stack = new Stack<>();

        // 3、遍历链表把每一个有效节点依照顺序放入栈中
        HeroNode temp = head.next;
        while (true){
            if (temp == null){ // 遍历完成
                break;
            }
            stack.add(temp);    // 节点入栈
            temp = temp.next;   // 往后移动节点指针
        }

        // 4、再遍历栈，取出数据即可
        while (!stack.empty()){
            System.out.println(stack.pop());    // 节点出栈
        }
    }


    /**
     * 5、合并两个有序的单链表，合并之后的链表依然有序【课后练习】
     *
     * 思路：拼接的效果是把第一个参数Linked1的每个节点往第二个参数Linked2中适当位置插入
     *      1、定义一个传递两个要拼接链表为参数的方法
     *      2、判断参数链表是否为空
     *      3、遍历Linked1，拿出其的每一个节点作为插入Linked2的变量节点
     *          a、先定义一个辅助节点temp：存放Linked1的当前节点
     *          b、定义一个辅助节点next：指向temp.next(因为后面移动链表指针的时候会丢失当前节点的next域)
     *          c、移动next 指向temp.next
     *      4、遍历Linked2，用变量节点比较链表2的每一个节点.no，找到插入位
     *          a、当前Linked2当前节点.next.no > temp.no，即链表2当前节点与其.next 之间就是插入位
     *          b、当前链表2当前节点.next.no == temp.no，即链表2已存在链表1的当前节点
     *      5、temp.next = 链表2当前节点.next
     *      6、将链表2当前节点.next = temp
     *      7、将temp = next(往后移动)
     *      8、遍历链表2
     */
    public void combineNode(HeroNode head1, HeroNode head2){ // 1、定义一个传递两个要拼接链表为参数的方法
        // 2、判断参数链表是否为空
        if (head1 == null || head2 == null){
            throw new RuntimeException("链表为空");
        }

        /**
         * 3、遍历Linked1，拿出其的每一个节点作为插入Linked2的变量节点
         *      a、定义一个辅助节点temp：存放Linked1的当前节点
         *      b、定义一个辅助节点next：指向temp.next(因为后面移动链表指针的时候会丢失当前节点的next域)
         *      c、移动next 指向temp.next
         */
        HeroNode temp = head1.next;
        HeroNode next = null;
        while (true){
            if (temp == null){
                break;
            }
            // c、移动next 指向temp.next
            next = temp.next;
            /**
             * 4、遍历Linked2，用变量节点比较链表2的每一个节点.no，找到插入位
             *      a、当前Linked2当前节点.next.no > temp.no，即链表2当前节点与其.next 之间就是插入位
             *      b、当前链表2当前节点.next.no == temp.no，即链表2已存在链表1的当前节点
             */
            HeroNode cur = head2;
            boolean flag = false;   // 判断当前英雄的编号在链表中是否已经存在
            while (true){
                if (cur.next == null){  // 说明temp已经是最后节点了
                    break;
                }
                if (cur.next.no > temp.no){    // 表明添加节点temp的位置找到了
                    break;
                } else if (cur.next.no == temp.no){
                    flag = true;    // 表明当前英雄的编号在链表中已经存在
                    break;
                }
                cur = cur.next;   // 链表指针往后移动
            }
            //
            if (flag){
                System.out.printf("节点【%d】已存在\n", temp.no);
            } else {
                // temp.next = 链表2当前节点.next
                temp.next = cur.next;
                // 6、将链表2当前节点.next = temp
                cur.next = temp;
            }
            // 7、将temp = next(往后移动)
            temp = next;
        }
        // 8、遍历链表2
        while (true){
            if (head2.next == null){
                break;
            }
            System.out.println(head2.next);
            head2.next = head2.next.next;
        }
    }

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
                '}';
    }
}




