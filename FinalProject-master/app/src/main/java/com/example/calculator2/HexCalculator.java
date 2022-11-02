package com.example.calculator2;

import java.util.ArrayList;
import java.util.Collections;

public class HexCalculator {
    String str,res="ERROR";

    public HexCalculator() {
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
            for(int i=0;i<str.length();i++)
            {
                if ("0123456789ABCDEF".indexOf(str.charAt(i)) >= 0)
                    temp=1;
            }
            if(temp==0)
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


        return res;

    }

    public void clear() {
        str = "";
        res="";
    }

    //十六进制转十进制
    public  String HexToDec(String s) {
        int len = s.length();
        double d = 0;
        if (s.contains(".")) {
            int temp = s.indexOf('.');          //标记小数点位置
            for (int i = temp + 1; i < len; i++) {
                if ((int) s.charAt(i) >= 65 && (int) s.charAt(i) <= 70)
                    d += ((int) s.charAt(i) - 55) * Math.pow(16, temp - i);
                else
                    d += ((int) s.charAt(i) - (int) '0') * Math.pow(16, temp - i);
            }
            for (int i = 0; i < temp; i++) {
                if ((int) s.charAt(i) >= 65 && (int) s.charAt(i) <= 70)
                    d += ((int) s.charAt(i) - 55) * Math.pow(16, temp - 1 - i);
                else
                    d += ((int) s.charAt(i) - (int) '0') * Math.pow(16, temp - 1 - i);
            }
        } else {

            for (int i = 0; i < len; i++) {
                if ((int) s.charAt(i) >= 65 && (int) s.charAt(i) <= 70)
                    d += ((int) s.charAt(i) - 55) *Math.pow(16, len - 1 - i);
                else
                    d += ((int) s.charAt(i) - (int) '0') * Math.pow(16, len - 1 - i);
            }

        }
        return Double.toString(d);
    }

    //十进制转十六进制
    public  String DecToHex(String str)
    {
        int IfMinus=0;
        if(str.contains("-")) {
            str = str.substring(1, str.length());
            IfMinus=1;
        }
        int x=(int)Double.parseDouble(str);
        double y=Double.parseDouble(str)-x;
        double q=y;
        ArrayList<String> a=new ArrayList<String>();        //处理整数部分
        while(x != 0)
        {
            if(x%16 < 10)
                a.add(Integer.toString(x%16));
            else    //如果余数大于10，将其转换成字母
            {
                char[] temp = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
                a.add(String.valueOf(temp[x%16-10]));
            }
            x=x/16;
        }
        Collections.reverse(a);  //将数组逆置
        String[] array1 = (String[])a.toArray(new String[a.size()]);
        String r1=String.join("",array1);

        ArrayList<String> b = new ArrayList<String>();        //处理小数部分
        int t=0;
        while(Math.round(y) != y && t<=10) {

            y*=16;
            int z=(int)y;
            if(z < 10)
                b.add(Integer.toString(z));
            else    //如果整数部分大于10，将其转换成字母
            {
                char[] temp = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
                b.add(String.valueOf(temp[z-10]));
            }
            y=y-z;
            t++;
        }
        Collections.reverse(b);  //将数组逆置
        String[] array2 = (String[])b.toArray(new String[b.size()]);
        String r2=String.join("",b);
        String result;
        if(q!=0) {
            result = r1 + "." + r2;
        }
        else
            result = r1 + r2;
        if(IfMinus==0)
            return result;
        else
            return "-"+result;

    }


    private String[] logic(String str) {
        String s = "";
        char[] opStack = new char[100];// 初始化栈
        String[] postQueue = new String[100];// 后缀表达式字符串数组
        int top = -1, j = 0;// 静态指针top,控制变量j
        for (int i = 0; i < str.length(); i++)// 遍历中缀表达式
        {
            if ("0123456789.ABCDEF".indexOf(str.charAt(i)) >= 0) // 遇到数字字符的情况直接入队
            {
                s = "";// 作为承接字符，每次开始时都要清空
                for (; i < str.length() && "0123456789.ABCDEF".indexOf(str.charAt(i)) >= 0; i++) {
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
            } else if ("*÷+-—".indexOf(str.charAt(i)) >= 0)// 遇到运算符
            {
                if (top == -1) {// 若栈为空则直接入栈
                    top++;
                    opStack[top] = str.charAt(i);
                } else if ("*÷".indexOf(opStack[top]) >= 0&&"—".indexOf(str.charAt(i)) < 0) {// 当栈顶元素为高优先级运算符时,让栈顶元素出栈进入后缀表达式后,当前运算符再入栈
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

//        for (int i = 0; str[i] != null; i++) {     //判断数字中是否出现多个小数点的异常输入
//            int dotNum=0;
//            if ("+-*÷%√²—".indexOf(str[i]) < 0) {
//                for(int j=0;j<str[i].length();j++)
//                {
//                    if(".".indexOf(str[i].charAt(j))>=0) {
//                        dotNum++;
//                        if(".".indexOf(str[i].charAt(0))>=0)
//                            return "ERROR";
//                    }
//                }
//                if(dotNum>1)
//                    return "ERROR";
//
//            }
//        }

//        for (int i = 0; str[i] != null; i++) {     //若数字以0开头则自动去掉开头的0
//            int zeroNum=0;
//            if ("+-*÷%√²—".indexOf(str[i]) < 0) {
//                for(int j=0;j<str[i].length();j++)
//                {
//                    if("0".indexOf(str[i].charAt(j))>=0) {
//                        zeroNum++;
//                    }
//
//                    else
//                    {
//                        if(!str[i].contains(".")) {
//                            str[i] = str[i].substring(zeroNum, str[i].length());
//                        }
//                        else{
//                            str[i] = str[i].substring(zeroNum-1, str[i].length());
//                        }
//                        break;
//                    }
//                }


//            }
//        }


        for (int i = 0; str[i] != null; i++) {
            if ("+-*÷—".indexOf(str[i]) < 0) {
                str[i]=HexToDec(str[i]);
                Top++;
                Result[Top] = str[i];

            }

            else if("—".indexOf(str[i]) >= 0)
            {
                double x=0,n;
                x = Double.parseDouble(Result[Top]);
                Top--;

                if("—".indexOf(str[i])>=0)
                {
                    n=0-x;
                    Top++;
                    Result[Top]=String.valueOf(n);
                }




            }
            else if ("+-*÷".indexOf(str[i]) >= 0)// 遇到运算符字符，将栈顶两个元素出栈计算并将结果返回栈顶
            {
                double x=0, y=0, n;


                x = Double.parseDouble(Result[Top]);
                Top--;
                y = Double.parseDouble(Result[Top]);
                Top--;

                if ("*".indexOf(str[i]) >= 0) {
                    n = y * x;
                    Top++;
                    Result[Top] = String.valueOf(n);// 将运算结果重新入栈

                }
                if ("÷".indexOf(str[i]) >= 0) {
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
                    n = y - x;
                    Top++;
                    Result[Top] = String.valueOf(n);// 将运算结果重新入栈
                }
                if ("+".indexOf(str[i]) >= 0) {
                    n = y + x;
                    Top++;
                    Result[Top] = String.valueOf(n);// 将运算结果重新入栈
                }






            }



        }
        return DecToHex(Result[Top]);
    }

}
