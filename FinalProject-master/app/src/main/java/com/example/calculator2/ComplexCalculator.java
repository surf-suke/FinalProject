package com.example.calculator2;

import java.math.BigDecimal;

import javax.xml.transform.Result;

public class ComplexCalculator {


    String res="ERROR";
    public int IsEmpty(String str)
    {
        if(str.equals(""))
            return 0;
        else
            return 1;
    }
    public int DotAtHead(String str)
    {
        if(str.charAt(0)=='.')
            return 0;
        else
            return 1;
    }

    public void process(String r1,String r2,String i1,String i2,String op) {

        if(IsEmpty(r1)==0)
            r1="0";
        if(IsEmpty(r2)==0)
            r2="0";
        if(IsEmpty(i1)==0)
            i1="0";
        if(IsEmpty(i2)==0)
            i2="0";

        if(DotAtHead(r1)==0)
            r1="0"+r1;
        if(DotAtHead(r2)==0)
            r2="0"+r2;
        if(DotAtHead(i1)==0)
            i1="0"+i1;
        if(DotAtHead(i2)==0)
            i2="0"+i2;



        if(op.equals("+"))
        {
            BigDecimal r_1 = new BigDecimal(r1);
            BigDecimal r_2= new BigDecimal(r2);
            BigDecimal i_1 = new BigDecimal(i1);
            BigDecimal i_2= new BigDecimal(i2);
            BigDecimal bigr = new BigDecimal("0");
            BigDecimal bigi = new BigDecimal("0");
            BigDecimal bigz = new BigDecimal("0");
            bigr = r_1.add(r_2);
            bigi = i_1.add(i_2);

            if(String.valueOf(bigr)=="0"&&String.valueOf(bigi)=="0")
                res="0";
            else if(String.valueOf(bigi)=="0")
                res=String.valueOf(bigr);
            else if(String.valueOf(bigr)=="0")
                res=String.valueOf(bigi)+"i";
            else
                res=String.valueOf(bigr)+"+"+String.valueOf(bigi)+"i";

        }
        if(op.equals("-"))
        {
            BigDecimal r_1 = new BigDecimal(r1);
            BigDecimal r_2= new BigDecimal(r2);
            BigDecimal i_1 = new BigDecimal(i1);
            BigDecimal i_2= new BigDecimal(i2);
            BigDecimal bigr = new BigDecimal("0");
            BigDecimal bigi = new BigDecimal("0");
            BigDecimal bigz = new BigDecimal("0");
            bigr = r_1.subtract(r_2);
            bigi = i_1.subtract(i_2);
            if(String.valueOf(bigr)=="0"&&String.valueOf(bigi)=="0")
                res="0";
            else if(String.valueOf(bigi)=="0")
                res=String.valueOf(bigr);
            else if(String.valueOf(bigr)=="0")
                res=String.valueOf(bigi)+"i";
            else
                res=String.valueOf(bigr)+"+"+String.valueOf(bigi)+"i";

        }
        if(op.equals("*"))
        {
            BigDecimal r_1 = new BigDecimal(r1);
            BigDecimal r_2= new BigDecimal(r2);
            BigDecimal i_1 = new BigDecimal(i1);
            BigDecimal i_2= new BigDecimal(i2);
            BigDecimal bigr = new BigDecimal("0");
            BigDecimal bigi = new BigDecimal("0");
            BigDecimal bigz = new BigDecimal("0");
            bigr = (r_1.multiply(r_2)).subtract(i_1.multiply(i_2));
            bigi = (r_1.multiply(i_2)).add(r_2.multiply(i_1));
            if(String.valueOf(bigr)=="0"&&String.valueOf(bigi)=="0")
                res="0";
            else if(String.valueOf(bigi)=="0")
                res=String.valueOf(bigr);
            else if(String.valueOf(bigr)=="0")
                res=String.valueOf(bigi)+"i";
            else
                res=String.valueOf(bigr)+"+"+String.valueOf(bigi)+"i";

        }
        if(op.equals("/"))
        {
            BigDecimal numerator_r = new BigDecimal("0");  //分子
            BigDecimal numerator_i = new BigDecimal("0");  //分子
            BigDecimal denominator = new BigDecimal("1");  //分母

            BigDecimal bigz = new BigDecimal("0");

            BigDecimal r_1 = new BigDecimal(r1);
            BigDecimal r_2= new BigDecimal(r2);
            BigDecimal i_1 = new BigDecimal(i1);
            BigDecimal i_2= new BigDecimal(i2);
            BigDecimal bigr = new BigDecimal("0");
            BigDecimal bigi = new BigDecimal("0");
            numerator_r = (r_1.multiply(r_2)).add(i_1.multiply(i_2));
            numerator_i = (r_1.multiply(bigz.subtract(i_2))).add(r_2.multiply(i_1));
            denominator = (r_2.multiply(r_2)).add(i_2.multiply(i_2));
            if(String.valueOf(denominator)=="0")
            {
                res="ERROR";
                return;
            }
            bigr=numerator_r.divide(denominator);
            bigi=numerator_i.divide(denominator);
            if(String.valueOf(bigr)=="0"&&String.valueOf(bigi)=="0")
                res="0";
            else if(String.valueOf(bigi)=="0")
                res=String.valueOf(bigr);
            else if(String.valueOf(bigr)=="0")
                res=String.valueOf(bigi)+"i";
            else
                res=String.valueOf(bigr)+"+"+String.valueOf(bigi)+"i";

        }

    }

    public String getSres() {

        return res;

    }
}
