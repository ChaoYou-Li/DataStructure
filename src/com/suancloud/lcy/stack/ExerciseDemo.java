package com.suancloud.lcy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/14 0014 18:48
 * Content：这是用栈实现逆波兰表达式计算器的练习题
 */
public class ExerciseDemo {

    public static void main(String[] args){
        String expression = "(4+3)*5-6";
//        String expression = "1+((2+3)*4)-5";
        List<String> list = getListData(expression);
        System.out.println("list="+list);
        List<String> list1 = middleToSuffix(list);
        System.out.println("后缀表达式："+list1);
        System.out.println("expression="+SuffixCalculatorStackDemo.calculate(list1));
    }

    // 设计一个把字符串表达式转成List 列表的方法
    public static List<String> getListData(String expression){
        // 创建一个List 列表
        List<String> list = new ArrayList<>();
        int i = 0;  // 辅助指针用于遍历中缀表达式字符串
        String str = "";    // 用于多位数的拼接
        char c = ' ';   // 辅助指针指向的当前字符
        // 开始遍历字符串表达式
        do {
            c = expression.charAt(i);
            if (c < 48 || c > 57){  // 非数字范围
                list.add("" + c);
                i ++;
            } else {    // 就是数字范围了
                str = "";   // 每次拼接多位数时必须清空
                while (i < expression.length() && (c = expression.charAt(i)) >= 48
                        && (c = expression.charAt(i)) <= 57){  // 查看下一位字符是否为数字范围

                    str += c;  // 拼接操作
                    i ++;
                }
                list.add(str);
            }
        } while (i < expression.length());
        return list;
    }


    /**
     * 实现：中缀表达式转后缀表达式
     * 步骤：
     *      1、初始化两个栈：运算符栈s1 和存储中间结果的栈s2
     *      2、从左到右扫描中缀表达式
     *      3、遇到数字，将其压入s2
     *      4、遇到运算符，比较其与s1 栈顶运算符的优先级：
     *          a、如果s1 为空，或栈顶运算符为左括号"("，则直接将此运算符入栈
     *          b、否则，若优先级比栈顶运算符高，也将其压入s1
     *          c、否则，将s1 栈顶的运算符弹出并压入到s2 中，再次转到(4-a)与s1 中新的栈顶运算符相比较
     *      5、遇到括号时：
     *          a、如果是左括号"("，则直接压入s1
     *          b、如果是右括号")"，则依次弹出s1 栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     *      6、重复步骤2-5，直到表达式的最右边
     *      7、将s1 中剩余的运算符依次弹出并压入s2
     *      8、依次弹出s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * 实例：(4 + 3) * 5 - 6 = 4 3 + 5 * 6 -
     */
    public static List<String> middleToSuffix(List<String> list){
        // 1、初始化两个栈：运算符栈s1 和存储中间结果的栈s2
        Stack<String> s1 = new Stack<>();   // 运算符栈
        Stack<String> s2 = new Stack<>();   // 结果栈

        // 2、从左到右扫描中缀表达式
        for (String l:list){
            // 3、遇到数字，将其压入s2
            if (l.matches("\\d+")){
                s2.push(l);
            } else if (isOper(l)){
                // 再次转到(4-a)与s1 中新的栈顶运算符相比较
                while (true){
                    // 4、遇到运算符，比较其与s1 栈顶运算符的优先级：
                    if (s1.empty() || "(".equals(s1.peek())){
                        // a、如果s1 为空，或栈顶运算符为左括号"("，则直接将此运算符压入s1
                        s1.push(l);
                        break;
                    } else if (priority(l) > priority(s1.peek())){
                        // b、否则，若优先级比栈顶运算符高，也将其压入s1
                        s1.push(l);
                        break;
                    } else {
                        // c、否则，将s1 栈顶的运算符弹出并压入到s2 中
                        s2.push(s1.pop());
                    }
                }
            } else {
                // 5、遇到括号时：
                if ("(".equals(l)){
                    // a、如果是左括号"("，则直接压入s1
                    s1.push(l);
                }
                if (")".equals(l)){
                    // b、如果是右括号")"，则依次弹出s1 栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                    while (!s1.empty() && !"(".equals(s1.peek())){
                        // 则依次弹出s1 栈顶的运算符，并压入s2
                        s2.push(s1.pop());
                    }
                    s1.pop();
                }
            }
        }

        // 7、将s1 中剩余的运算符依次弹出并压入s2
        while (!s1.empty()){
            s2.push(s1.pop());
        }

        // 8、依次弹出s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
        List<String> list1 = new ArrayList<>();
        while (!s2.empty()){
            s1.push(s2.pop());
        }
        while (!s1.empty()){
            list1.add(s1.pop());
        }
        return list1;
    }


    // 设计一个判断运算符优先级的方法
    public static int priority(String oper){
        int num = 0;
        switch (oper){
            case "*":
                num = 2;
                break;
            case "/":
                num = 2;
                break;
            case "+":
                num = 1;
                break;
            case "-":
                num = 1;
                break;
            default:
                System.out.printf("【%d】不是运算符\n", oper);
                break;
        }
        return num;
    }

    // 设计一个判断字符串是否为运算符
    public static boolean isOper(String c){
        return "*".equals(c) || "/".equals(c) || "+".equals(c) || "-".equals(c);
    }
}

