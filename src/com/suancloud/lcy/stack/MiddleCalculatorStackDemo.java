package com.suancloud.lcy.stack;

/**
 * Author: chaoyou
 * Email：1277618785@qq.com
 * CSDN：https://blog.csdn.net/qq_41910568
 * Date: 2019/9/13 0013 12:45
 * Content：这是用栈实现中缀表达式计算的实现类
 */
public class MiddleCalculatorStackDemo {
    /**
     * f、使用栈完成表达式的计算思路
     *      ①先创建两个栈：数栈(存放数字)、符号栈(存放运算符)
     *      ②通过一个index值(索引),来遍历我们的表达式
     *      ③如果我们发现扫描到的是一个数字就直接入数栈
     *      ④如果扫描到的是符号，就要进行运算符优先级比较：
     *          1）、如果当前运算符的优先级小于等于栈中的运算符，就需要从数栈中弹出两个数字，
     *               再从符号栈中pop出一个符号，进行运算得到结果再push数栈，然后将当前运算符push符号栈。
     *          2）、如果当前运算符的优先级大于栈中的运算符，就直接push符号栈。
     *      ⑤当表达式扫描完毕，就有顺序地从数栈和符号栈中pop出数字和运算符，并进行运算
     *      ⑥最后在数栈只有一个数字，就是表达式的结果
     */

    public static void main(String[] args){
        // 设计一个需要运算的表达式
        String expression = "406+6*2-6/2";

        // ①先创建两个栈：数栈(存放数字)、符号栈(存放运算符)
        ArrayStack2 numStack = new ArrayStack2(5);
        ArrayStack2 operStack = new ArrayStack2(5);

        // ②通过一个index值(索引),来遍历我们的表达式
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        char oper = 0;   // 用来存放运算符
        String keepNum = "";
        int result = 0;
        char ch = ' ';  // 用于存放每一次扫描expression 的结果

        // 开始遍历expression
        while (true){
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断是否为符号
            if (numStack.isOper(ch)){
                if (!operStack.isEmpty()){ // 符号栈不为空
                    // ④如果扫描到的是符号，就要进行运算符优先级比较：
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        /**
                         * 1）、如果当前运算符的优先级小于等于栈中的运算符，就需要从数栈中弹出两个数字，
                         *      再从符号栈中pop出一个符号，进行运算得到结果再push数栈，然后将当前运算符push符号栈。
                         */
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char) operStack.pop();  // 注意：取出来的符号要转码
                        result = operStack.cal(num1, num2, oper);
                        numStack.push(result);
                    }
                    // 2）、如果当前运算符的优先级大于栈中的运算符，就直接push符号栈。
                    operStack.push(ch);
                } else {    // 符号栈为空
                    operStack.push(ch);
                }
            } else {
                // ③如果我们发现扫描到的是一个数字，则要再扫后一位判断是否为多位数字
                keepNum += ch;  // 多位数字拼接
                if (index == expression.length() -1){   // 是否为最后一位
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 则要再扫后一位判断是否为多位数字
                    if (numStack.isOper(expression.charAt(index + 1))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index ++;
            if (index >= expression.length()){
                break;
            }
        }

        // ⑤当表达式扫描完毕，就有顺序地从数栈和符号栈中pop出数字和运算符，并进行运算
        while (true){
            if (operStack.isEmpty()){   // 符号栈为空证明运算结束
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char) operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        //⑥最后在数栈只有一个数字，就是表达式的结果
        System.out.println(expression+" = "+result);
    }

}


// ①使用数组来模拟栈
class ArrayStack2{
    private int maxSize;    // 栈的最大容量
    private int[] stack;  // 模拟栈的数组
    private int top = -1;    // 模拟栈顶指针

    // 构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    public int getTop() {
        return top;
    }

    // 栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈
    public void push(int data){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        top ++;
        stack[top] = data;
    }

    // 查看栈顶元素
    public int peek(){
        return stack[top];
    }

    // 出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top --;
        return value;
    }

    // 遍历栈的元素，需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈为空");
        }
        for (int i=top; i>=0; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 判断符号是否为运算符
    public boolean isOper(int oper){
        if (oper == '*' || oper == '/' || oper == '+' || oper == '-'){
            return true;
        }
        return false;
    }

    // 设计运算符的优先级
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-'){
            return 0;
        } else {
            return -1;
        }
    }

    // 计算出结果返回
    public int cal(int num1, int num2, int oper){
        if (!isOper(oper)){
            throw new RuntimeException("输入的参数有误");
        }
        int result = 0;
        // 注意：如果是做除法、减法运算就是，把后出来的数字操作前出来的数字
        switch (oper){
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            default:
                break;
        }
        return result;
    }

}
