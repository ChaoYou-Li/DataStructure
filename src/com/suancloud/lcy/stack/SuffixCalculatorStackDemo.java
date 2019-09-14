package com.suancloud.lcy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/13 0013 21:25
 * Content：这是为了演示后缀计算表达式的实现类
 */
public class SuffixCalculatorStackDemo {

    /**
     * 完成一个逆波兰表达式计算器：
     *      1、输入一个逆波兰表达式(后缀表达式)，使用栈(Stack),计算其结果
     *      2、支持小括号和多位整数运算，因为这里我们主要讲的是数据结构，因此计算器进行简陋化，只支持对整数的计算。
     *      3、思路：
     *      4、代买完成
     */
    public static void main(String[] args){
        /**
         * 先定义一个逆波兰表达式:
         *      (4 + 3) * 5 - 6 = 4 3 + 5 * 6 -
         *
         * 说明为了方便，逆波兰表达式的数字和符号使用空格隔开
         */
        String expression = "4 3 + 5 * 6 -";
        List<String> list = getListData(expression);
        System.out.println("list="+list);
        int result = calculate(list);
        System.out.println("运算结果："+result);
    }

    // 设计一个方法：将一个逆波兰表达式，依次将数据和运算符放入到ArrayList 中
    public static List<String> getListData(String suffixExpression){
        // 将后缀表达式(suffixExpression)分割成字符串数组
        String[] arr = suffixExpression.split(" ");
        // 遍历字符串数组，把元素依次放入列表中
        List<String> list = new ArrayList<>();
        for (String data:arr){
            list.add(data);
        }
        return list;
    }


    /**
     * g、使用栈完成(后缀)表达式的计算思路
     * 	    ①从左到右扫描表达式，遇到数字时，将数字压入堆栈，
     * 	    ②遇到运算符时，弹出栈顶的两个数
     * 	    ③用运算符对它们进行相应的运算(次顶元素和栈顶元素)，并将结果入栈；
     * 	    ④重复上面的步骤直到表达式最右端，栈中的值就是表达式的结果
     */
    public static int calculate(List<String> list){
        int result = 0;
        // 创建一个栈集合
        Stack<String> stack = new Stack<>();
        // ①从左到右扫描表达式，遇到数字时，将数字压入堆栈，
        for (String str:list){
            // 使用正则表达式匹配数字
            if (str.matches("\\d+")){   // 匹配的是多位数
                stack.push(str);    // 入栈
            } else {    // ②遇到运算符时，弹出栈顶的两个数
                // ③用运算符对它们进行相应的运算(次顶元素和栈顶元素)，并将结果入栈；
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                if ("*".equals(str)){
                    result = num1 * num2;
                } else if ("/".equals(str)){
                    result = num2 / num1;
                } else if ("+".equals(str)){
                    result = num1 + num2;
                } else if ("-".equals(str)){
                    result = num2 - num1;
                } else {
                    throw new RuntimeException("表达式的运算符有误");
                }
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}


