import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class calculator extends JFrame implements ActionListener {
    private final String[] Buttons = {"DEC", "HEX", "OCT", "OFF", "ON/C", "STO", "RCL", "SUM", "(", ")", "SHF", "d", "E", "F","K",
            "1'sC", "A","b", "C","/","OR", "7", "8", "9", "*", "AND","4","5","6","-","XOR","1","2","3","+","CE","0",".","+/-","="};
    private JButton buttons[] = new JButton[Buttons.length];
    private JTextArea result = new JTextArea("0");//结果栏设置为0
    private JLabel label1 = new JLabel("DEC");
    public String a = "";

    public calculator(){
        super("calculator");
        result.setBounds(20,22,520,60);
        result.setAlignmentX(RIGHT_ALIGNMENT);// 文本框内容右对齐
        result.setFont(new Font(null,Font.PLAIN,20));
        result.setEditable(false);// 文本框不允许修改结果
        label1.setBounds(20,83,100,60);
        JPanel jp1 = new JPanel();
        jp1.setBounds(20,22,520,60);
        jp1.setLayout(new GridLayout());
        result.setLineWrap(true);// 激活自动换行功能
        result.setWrapStyleWord(true);// 激活断行不断字功能
        result.setSelectedTextColor(Color.BLACK);
        jp1.add(result);
        this.add(jp1);//将面板添加到总窗体中
        this.add(label1);
        this.setLayout(null);
        // 放置按钮
        int x = 55, y = 150;
        for (int i = 0; i < Buttons.length; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText(Buttons[i]);
            buttons[i].setBounds(x, y, 80, 50);
            if (x < 340) {
                x += 90;
            } else {
                x = 55;
                y += 60;
            }
            this.add(buttons[i]);
        }
        //时间监听器
        for (int i = 0; i < Buttons.length; i++)
        {
            buttons[i].addActionListener(this);
        }
        this.setResizable(false);
        this.setBounds(500, 200, 580, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // 事件处理
    public void actionPerformed(ActionEvent e){
        String label=e.getActionCommand();
        if(Objects.equals(label,"DEC")){
            if (label1.getText().equals("DEC")){
                label1.setText("DEC");
            }
            else {
                label1.setText("DEC");
                Integer n = Integer.parseInt(result.getText(), 16);
                result.setText(Integer.toString(n));
            }
        }
        else if(Objects.equals(label,"HEX")){
            if (label1.getText().equals("HEX")){
                label1.setText("HEX");
            }
            else {
                label1.setText("HEX");
                int n = Integer.parseInt(result.getText());
                result.setText(Integer.toHexString(n));
            }
        }
        else if(Objects.equals(label, "CE")) {//清空
            this.a="";
            result.setText("0");
        }

        else if(Objects.equals(label,"=")) {
            this.a=this.a.replace("+/-","_");
            this.a=this.a.replace("AND","&");
            this.a=this.a.replace("XOR","^");
            this.a=this.a.replace("OR","|");
            String[] s =logic(this.a);
            String result1 = Result(s,label1.getText());
            this.a=result1+"";
            //更新文本框，当前结果在字符串b中，并未删除，下一次输入接着此结果以实现连续运算
            result.setText(this.a);
        }
        else {
            this.a=this.a+label;
            result.setText(this.a);
        }

    }
    //将中缀表达式转换为后缀表达式
    private String[] logic(String str) {
        String s = "";
        char[] opStack = new char[100];// 初始化栈
        String[] postQueue = new String[100];// 后缀表达式字符串数组
        int top = -1, j = 0;// 静态指针top,控制变量j
        for (int i = 0; i < str.length(); i++)// 遍历中缀表达式
        {
            if ("0123456789.AbCdEF_".indexOf(str.charAt(i)) >= 0) // 遇到数字字符的情况直接入队
            {
                s = "";// 作为承接字符，每次开始时都要清空
                for (; i < str.length() && "0123456789.AbCdEF_".indexOf(str.charAt(i)) >= 0; i++) {
                    s = s + str.charAt(i);
                }
                i--;
                postQueue[j] = s;// 数字字符直接加入后缀表达式
                j++;
            }
            else if ("(".indexOf(str.charAt(i)) >= 0) {
                top++;
                opStack[top] = str.charAt(i);// 左括号入栈
            }
            else if (")".indexOf(str.charAt(i)) >= 0) {
                for (;;)// 栈顶元素循环出栈，直到遇到左括号为止
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
            }
            else if ("*/+-&|^".indexOf(str.charAt(i)) >= 0)// 遇到运算符
            {
                if (top == -1)
                {// 若栈为空则直接入栈
                    top++;
                    opStack[top] = str.charAt(i);
                }
                else if ("*/".indexOf(opStack[top]) >= 0)
                {// 当栈顶元素为高优先级运算符时,让栈顶元素出栈进入后缀表达式后,当前运算符再入栈
                    postQueue[j] = opStack[top] + "";
                    j++;
                    opStack[top] = str.charAt(i);
                }
                else
                {
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
    public String Result(String[] str,String label2) {
        String[] Result = new String[100];// 顺序存储的栈，数据类型为字符串
        int Top = -1;// 静态指针Top
        for (int i = 0; str[i] != null; i++) {
            if ("+-*/&|^".indexOf(str[i]) < 0) {  //遇到数字，直接入栈
                Top++;
                Result[Top] = str[i];
            }
            if ("+-*/&|^".indexOf(str[i]) >= 0 && label2.equals("DEC"))// 遇到运算符字符，将栈顶两个元素出栈计算并将结果返回栈顶
            {
                    Result[Top] = Result[Top].replace("_","-");
                    int x, y, n,cha1,cha2;//cha1,cha2为符号位
                    x = Integer.parseInt(Result[Top]);// 顺序出栈两个数字字符串，并转换为int类型
                    Top--;
                    Result[Top] = Result[Top].replace("_","-");
                    y = Integer.parseInt(Result[Top]);
                    Top--;

                if ("*".indexOf(str[i]) >= 0) {
                    n = y * x;
                    Top++;
                    Result[Top] = String.valueOf(n);// 将运算结果重新入栈

                }
                if ("/".indexOf(str[i]) >= 0)
                {
                    if (x == 0)
                    {
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
                if ("&".indexOf(str[i]) >= 0){
                    if (x<0){
                        x=-x;
                        cha2 = 1;
                    }
                    else{
                        cha2 = 0;
                    }
                    if (y<0){
                        y=-y;
                        cha1 = 1;
                    }
                    else{
                        cha1 = 0;
                    }
                    String anotherBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(x),10));	//10进制字符转二进制字符
                    String thisBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(y),10));
                    int anlenth = anotherBinary.length();
                    int thlength = thisBinary.length();
                    int k = 0;
                    int cha=cha1&cha2;
                    String m="";
                    for(int j=0;j<thisBinary.length();j++){
                        if(j>thlength-anlenth-1) {
                            if (thisBinary.charAt(j) == '1' && anotherBinary.charAt(k) == '1') {
                                m += "1";
                            }
                            else {
                                m += "0";
                            }
                            k+=1;
                        }
                        else{
                            m += "0";
                        }
                    }
                    Top++;
                    Integer res = Integer.parseInt(m,2);
                    if(cha == 1){
                        res = -res;
                    }
                    Result[Top] = Integer.toString(res);// 将运算结果重新入栈

                }
                if ("|".indexOf(str[i]) >= 0){
                    if (x<0){
                        x=-x;
                        cha2 = 1;
                    }
                    else{
                        cha2 = 0;
                    }
                    if (y<0){
                        y=-y;
                        cha1 = 1;
                    }
                    else{
                        cha1 = 0;
                    }
                    String anotherBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(x),10));	//10进制字符转二进制字符
                    String thisBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(y),10));
                    String m="";
                    int anlenth = anotherBinary.length();
                    int thlength = thisBinary.length();
                    int k = 0;
                    int cha=cha1|cha2;
                    for(int j=0;j<thisBinary.length();j++){
                        if(j>thlength-anlenth-1) {
                            if (thisBinary.charAt(j) == '1' || anotherBinary.charAt(k) == '1') {
                                m += "1";
                            }
                            else {
                                m += "0";
                            }
                            k+=1;
                        }
                        else{
                            m += thisBinary.charAt(j);
                        }
                    }
                    Top++;
                    Integer res = Integer.parseInt(m,2);
                    if(cha == 1){
                        res = -res;
                    }
                    Result[Top] = Integer.toString(res);// 将运算结果重新入栈
                }
                if ("^".indexOf(str[i]) >= 0){
                    if (x<0){
                        x=-x;
                        cha2 = 1;
                    }
                    else{
                        cha2 = 0;
                    }
                    if (y<0){
                        y=-y;
                        cha1 = 1;
                    }
                    else{
                        cha1 = 0;
                    }
                    String anotherBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(x),10));	//10进制字符转二进制字符
                    String thisBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(y),10));
                    String m="";
                    int anlenth = anotherBinary.length();
                    int thlength = thisBinary.length();
                    int k = 0;
                    int cha = Math.abs(cha1-cha2);
                    for(int j=0;j<thisBinary.length();j++){
                        if(j>thlength-anlenth-1) {
                            if (thisBinary.charAt(j) == anotherBinary.charAt(k)) {
                                m += "0";
                            }
                            else {
                                m += "1";
                            }
                            k+=1;
                        }
                        else{
                            m += thisBinary.charAt(j);
                        }
                    }
                    Top++;
                    Integer res = Integer.parseInt(m,2);
                    if(cha == 1){
                        res = -res;
                    }
                    Result[Top] = Integer.toString(res);// 将运算结果重新入栈
                }
            }
            else if("+-*/&|^".indexOf(str[i]) >= 0 && label2.equals("HEX")){
                int x,y,n,cha1,cha2;
                Result[Top] = Result[Top].replace("_","-");
                x =Integer.parseInt(Result[Top],16);
                Top--;
                Result[Top] = Result[Top].replace("_","-");
                y = Integer.parseInt(Result[Top],16);
                Top--;

                if ("*".indexOf(str[i]) >= 0) {
                    n = y * x;
                    Top++;
                    Result[Top] = Integer.toHexString(n);// 将运算结果重新入栈

                }
                if ("/".indexOf(str[i]) >= 0)
                {
                    if (x == 0)
                    {
                        String s = "error!";
                        return s;
                    } else {
                        n = y / x;
                        Top++;
                        Result[Top] = Integer.toHexString(n);// 将运算结果重新入栈
                    }
                }

                if ("-".indexOf(str[i]) >= 0) {
                    n = y - x;
                    Top++;
                    Result[Top] = Integer.toHexString(n);// 将运算结果重新入栈
                }
                if ("+".indexOf(str[i]) >= 0) {
                    n = y + x;
                    Top++;
                    Result[Top] = Integer.toHexString(n);// 将运算结果重新入栈
                }
                if ("&".indexOf(str[i]) >= 0){
                    String anotherBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(x),10));	//10进制字符转二进制字符
                    String thisBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(y),10));
                    String m="";
                    int anlenth = anotherBinary.length();
                    int thlength = thisBinary.length();
                    int k = 0;
                    for(int j=0;j<thisBinary.length();j++){
                        if(j>thlength-anlenth-1) {
                            if (thisBinary.charAt(j) == '1' && anotherBinary.charAt(k) == '1') {
                                m += "1";
                            }
                            else {
                                m += "0";
                            }
                            k+=1;
                        }
                        else{
                            m += "0";
                        }
                    }
                    Top++;
                    Integer res = Integer.parseInt(m,2);
                    Result[Top] = Integer.toHexString(res);// 将运算结果重新入栈

                }
                if ("|".indexOf(str[i]) >= 0){
                    String anotherBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(x),10));
                    String thisBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(y),10));
                    String m="";
                    int anlenth = anotherBinary.length();
                    int thlength = thisBinary.length();
                    int k = 0;
                    for(int j=0;j<thisBinary.length();j++){
                        if(j>thlength-anlenth-1) {
                            if (thisBinary.charAt(j) == '1' || anotherBinary.charAt(k) == '1') {
                                m += "1";
                            }
                            else {
                                m += "0";
                            }
                            k+=1;
                        }
                        else{
                            m += thisBinary.charAt(j);
                        }
                    }
                    Top++;
                    Integer res = Integer.parseInt(m,2);
                    Result[Top] = Integer.toHexString(res);// 将运算结果重新入栈
                }
                if ("^".indexOf(str[i]) >= 0){
                    String anotherBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(x),10));
                    String thisBinary=Integer.toBinaryString(Integer.valueOf(Integer.toString(y),10));
                    String m="";
                    int anlenth = anotherBinary.length();
                    int thlength = thisBinary.length();
                    int k = 0;
                    for(int j=0;j<thisBinary.length();j++){
                        if(j>thlength-anlenth-1) {
                            if (thisBinary.charAt(j) == anotherBinary.charAt(k)) {
                                m += "0";
                            }
                            else {
                                m += "1";
                            }
                            k+=1;
                        }
                        else{
                            m += thisBinary.charAt(j);
                        }
                    }
                    Top++;
                    Integer res = Integer.parseInt(m,2);
                    Result[Top] = Integer.toHexString(res);// 将运算结果重新入栈
                }
            }
        }
            return Result[Top];// 返回最终结果
    }
    public static void main(String[] args) {
        calculator a = new calculator();
    }
}