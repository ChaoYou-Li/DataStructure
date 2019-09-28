package com.suancloud.lcy.tree;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/28 0028 23:49
 * Content：树结构的实现类
 */
public class BinaryTreeDemo {
    public static void main(String[] args){
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        BinaryTree tree = new BinaryTree(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        // 测试前序遍历
//        tree.preOrder();    // 1,2,3,5,4
        // 测试中序遍历
//        tree.infixOrder();  // 2,1,5,3,4
        // 测试后序遍历
        tree.postOrder();  // 2,5,4,3,1

    }
}

// 创建一棵树结构类
class BinaryTree{
    private HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历方法
    public void preOrder(){
        if (this.root != null){
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历~~~");
        }
    }

    // 中序遍历方法
    public void infixOrder(){
        if (this.root != null){
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历~~~");
        }
    }

    // 后序遍历方法
    public void postOrder(){
        if (this.root != null){
            root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历~~~");
        }
    }
}


// 先创建一个结点类
class HeroNode{
    private int no; // 英雄编号
    private String name;    // 英雄名字
    private HeroNode left;  // 左子结点
    private HeroNode right;   // 右子结点

    public HeroNode() {
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    /**
     * 注解：设计一个前序遍历二叉树的方法
     *
     * 思想：
     *      ①先访问根结点
     *      ②按照从左到右的顺序先根遍历每一棵子树
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    /**
     * 注解：设计一个中序遍历二叉树的方法
     *
     * 思想：
     *      ①先用中序方法递归遍历左子树
     *      ②访问父结点
     *      ③用中序方法递归遍历右子树
     */
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    /**
     * 注解：设计一个后序遍历二叉树的方法
     *
     * 思想：
     *      ①按照从左到右的顺序先根遍历每一棵子树
     *      ②先访问根结点
     */
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
