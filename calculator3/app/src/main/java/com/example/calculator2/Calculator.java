package com.example.calculator2;

import static java.math.BigDecimal.ROUND_HALF_UP;

import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {
    String str,res="ERROR";

    public static BigDecimal sqrt(BigDecimal value, int scale){
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;
    }

    public Calculator() {
        clear();
    }



    public void process(String show) {
        if (!show.equals("=")) {
            if(show.equals("AC"))
            {
                str="";
                res="";
            }
            else if(show.equals("←"))
            {
                if(str.length()==0)
                    str = "";
                else
                {
                    str = str.substring(0, str.length() - 1);
                    res = "";
                }
            }
            else if(show.equals("—"))
            {
                show="—";
                str+=show;
            }
            else
            {
                str += show;
            }
        }

        else
        {
            int temp=0;
            int leftbrNum=0,rightbrNum=0;
            for(int i=0;i<str.length();i++)
            {
                if ("0123456789".indexOf(str.charAt(i)) >= 0)
                    temp=1;
            }
            for(int i=0;i<str.length();i++)
            {
                if ("(".indexOf(str.charAt(i)) >= 0)
                    leftbrNum++;
                if (")".indexOf(str.charAt(i)) >= 0)
                    rightbrNum++;
            }
            if(temp==0||(leftbrNum!=rightbrNum))
            {
                res="ERROR";
            }

            else
            {
                String[] postq = logic(str);
                res = Result(postq);
            }
        }
    }


    public String getStr() {
        return str;
    }

    public String getSres() {

//        if(res.contains(".")&&!res.contains("E")){
//            int index = res.indexOf(".");
//            if(res.substring(index+1,res.length()).length()>10)
//                res=res.substring(0,index+6);
//        }

        return res;

    }


    public void clear() {
        str = "";
        res="";
    }


    //将中缀表达式转换为后缀表达式
    private String[] logic(String str) {
        String s = "";
        char[] opStack = new char[100];// 初始化栈
        String[] postQueue = new String[100];// 后缀表达式字符串数组
        int top = -1, j = 0;// 静态指针top,控制变量j
        for (int i = 0; i < str.length(); i++)// 遍历中缀表达式
        {
            if ("0123456789.AbCdEF".indexOf(str.charAt(i)) >= 0) // 遇到数字字符的情况直接入队
            {
                s = "";// 作为承接字符，每次开始时都要清空
                for (; i < str.length() && "0123456789.AbCdEF".indexOf(str.charAt(i)) >= 0; i++) {
                    s = s + str.charAt(i);
                }
                i--;
                postQueue[j] = s;// 数字字符直接加入后缀表达式
                j++;
            } else if ("(".indexOf(str.charAt(i)) >= 0) {
                top++;
                opStack[top] = str.charAt(i);// 左括号入栈
            } else if (")".indexOf(str.charAt(i)) >= 0) {
                for (; ; )// 栈顶元素循环出栈，直到遇到左括号为止
                {
                    if (opStack[top] != '(') {// 栈顶元素不是左括号
                        postQueue[j] = opStack[top] + "";// 栈顶元素出栈
                        j++;
                        top--;
                    } else { // 找到栈顶元素是左括号
                        top--;// 删除栈顶左括号
                        break;
                    }
                }
            } else if ("*÷+-%√²—".indexOf(str.charAt(i)) >= 0)// 遇到运算符
            {
                if (top == -1) {// 若栈为空则直接入栈
                    top++;
                    opStack[top] = str.charAt(i);
                } else if ("*÷√²".indexOf(opStack[top]) >= 0&&"—".indexOf(str.charAt(i)) < 0) {// 当栈顶元素为高优先级运算符时,让栈顶元素出栈进入后缀表达式后,当前运算符再入栈
                    postQueue[j] = opStack[top] + "";
                    j++;
                    opStack[top] = str.charAt(i);
                }
                else if ("—".indexOf(opStack[top]) >= 0) {
                    postQueue[j] = opStack[top] + "";
                    j++;
                    opStack[top] = str.charAt(i);
                }
                else if("+-".indexOf(opStack[top])>=0&&"+-".indexOf(str.charAt(i))>=0)
                {
                    postQueue[j] = opStack[top] + "";
                    j++;
                    opStack[top] = str.charAt(i);
                }
                else {
                    top++;
                    opStack[top] = str.charAt(i);// 当前元素入栈
                }
            }
        }
        while (top != -1) {// 遍历结束后将栈中剩余元素依次出栈进入后缀表达式
            postQueue[j] = opStack[top] + "";
            j++;
            top--;
        }
        return postQueue;
    }

    // 十进制计算后缀表达式，并返回最终结果
    public String Result(String[] str) {
        String[] Result = new String[100];// 顺序存储的栈，数据类型为字符串
        int Top = -1;// 静态指针Top

        for (int i = 0; str[i] != null; i++) {     //判断数字中是否出现多个小数点的异常输入
            int dotNum=0;
            if ("+-*÷%√²—".indexOf(str[i]) < 0) {
                for(int j=0;j<str[i].length();j++)
                {
                    if(".".indexOf(str[i].charAt(j))>=0) {
                        dotNum++;
                        if(".".indexOf(str[i].charAt(0))>=0)
                            return "ERROR";
                    }
                }
                if(dotNum>1)
                    return "ERROR";

            }
        }

        for (int i = 0; str[i] != null; i++) {     //若数字以0开头则自动去掉开头的0
            int zeroNum=0;
            if ("+-*÷%√²—".indexOf(str[i]) < 0) {
                for(int j=0;j<str[i].length();j++)
                {
                    if("0".indexOf(str[i].charAt(j))>=0) {
                        zeroNum++;
                    }

                    else
                    {
                        if(!str[i].contains(".")) {
                            str[i] = str[i].substring(zeroNum, str[i].length());
                        }
                        else{
                            str[i] = str[i].substring(zeroNum, str[i].length());
                        }
                        break;
                    }
                }


            }
        }


        for (int i = 0; str[i] != null; i++) {
            if ("+-*÷%√²—".indexOf(str[i]) < 0) {  //遇到数字，直接入栈
                Top++;
                Result[Top] = str[i];

            }
            else if("√²—".indexOf(str[i]) >= 0)//遇到平方和开方只出一个元素进行计算
            {

                BigDecimal bigx = new BigDecimal(Result[Top]);
                BigDecimal bigz = new BigDecimal("0");
                BigDecimal bign = new BigDecimal("0");
                Top--;

                if("—".indexOf(str[i])>=0)
                {
                    bign=bigz.subtract(bigx);
                    Top++;
                    Result[Top]=String.valueOf(bign);
                }

                if("²".indexOf(str[i]) >= 0)
                {

                    bign=bigx.multiply(bigx);
                    Top++;
                    Result[Top]=String.valueOf(bign);

                }
                if("√".indexOf(str[i]) >= 0)
                {
                    if(bigx.compareTo(bigz)==-1)
                    {
                        return "ERROR";
                    }
                    else
                    {
                        if(str[i+1]!=null&&"²".indexOf(str[i+1]) >= 0)
                        {
                            bign=bigx;
                            Top++;
                            Result[Top]=String.valueOf(bign);
                            i++;

                        }
                        else
                        {
                            bign = sqrt(bigx,2);
                            Top++;

                            Result[Top] = String.valueOf(bign);
                        }
                    }
                }




            }
            else if ("+-*÷%".indexOf(str[i]) >= 0)// 遇到运算符字符，将栈顶两个元素出栈计算并将结果返回栈顶
            {

                BigDecimal bigz = new BigDecimal(0);
                BigDecimal bigx = new BigDecimal(Result[Top]);
                double x = Double.parseDouble(Result[Top]);
                Top--;
                BigDecimal bigy = new BigDecimal(Result[Top]);
                double y = Double.parseDouble(Result[Top]);
                Top--;
                BigDecimal bign= new BigDecimal("0");

                double n=0;

                if ("*".indexOf(str[i]) >= 0) {
                    bign = bigy.multiply(bigx);
                    Top++;
                    Result[Top] = String.valueOf(bign);// 将运算结果重新入栈

                }
                if ("÷".indexOf(str[i]) >= 0) {
//                    if (bigx.compareTo(bigz)==0) {
//                        String s = "error!";
//                        return s;
//                    } else {
//                        bign = bigy.divide(bigx,5,BigDecimal.ROUND_HALF_UP);
//                        Top++;
//
//
//                        Result[Top] = String.valueOf(bign);// 将运算结果重新入栈
//                    }
                    if (x == 0) {
                        String s = "error!";
                        return s;
                    } else {
                        n = y / x;
                        Top++;
                        Result[Top] = String.valueOf(n);// 将运算结果重新入栈
                    }
                }

                if ("-".indexOf(str[i]) >= 0) {
                    bign = bigy.subtract(bigx);
                    Top++;
                    Result[Top] = String.valueOf(bign);// 将运算结果重新入栈
                }
                if ("+".indexOf(str[i]) >= 0) {
                    bign = bigy.add(bigx);
                    Top++;
                    Result[Top] = String.valueOf(bign);// 将运算结果重新入栈
                }

                if ("%".indexOf(str[i]) >= 0) {
                    BigDecimal[] results = bigy.divideAndRemainder(bigx);
                    bign = results[1];
                    Top++;
                    Result[Top] = String.valueOf(bign);// 将运算结果重新入栈
                }




            }



        }
        return Result[Top];
    }
}

