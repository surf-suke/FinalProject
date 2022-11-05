package com.example.calculator2;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Time extends AppCompatActivity implements  View.OnClickListener{
    private Button number0;
    private Button number1;
    private Button number2;
    private Button number3;
    private Button number4;
    private Button number5;
    private Button number6;
    private Button number7;
    private Button number8;
    private Button number9;
    private Button dot;
    private Button clean_all;
    private EditText miao_edit;
    private EditText fen_edit;
    private EditText shi_edit;
    private EditText ri_edit;
    private EditText zhou_edit;
    private EditText yue_edit;
    private EditText nian_edit;


    //文本框空标记
    boolean clean=true;
    int time=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        number0=findViewById(R.id.number0);
        number1=findViewById(R.id.number1);
        number2=findViewById(R.id.number2);
        number3=findViewById(R.id.number3);
        number4=findViewById(R.id.number4);
        number5=findViewById(R.id.number5);
        number6=findViewById(R.id.number6);
        number7=findViewById(R.id.number7);
        number8=findViewById(R.id.number8);
        number9=findViewById(R.id.number9);
        dot=findViewById(R.id.dot);
        clean_all=findViewById(R.id.clean_all);
        miao_edit=findViewById(R.id.miao_edit);
        fen_edit=findViewById(R.id.fen_edit);
        shi_edit=findViewById(R.id.shi_edit);
        ri_edit=findViewById(R.id.ri_edit);
        zhou_edit=findViewById(R.id.zhou_edit);
        yue_edit=findViewById(R.id.yue_edit);
        nian_edit=findViewById(R.id.nian_edit);

        //        光标显示但不召出软键盘
        miao_edit.setShowSoftInputOnFocus(false);
        fen_edit.setShowSoftInputOnFocus(false);
        shi_edit.setShowSoftInputOnFocus(false);
        ri_edit.setShowSoftInputOnFocus(false);
        zhou_edit.setShowSoftInputOnFocus(false);
        yue_edit.setShowSoftInputOnFocus(false);
        nian_edit.setShowSoftInputOnFocus(false);

        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        dot.setOnClickListener(this);
        clean_all.setOnClickListener(this);

        miao_edit.setOnClickListener(this);
        fen_edit.setOnClickListener(this);
        shi_edit.setOnClickListener(this);
        ri_edit.setOnClickListener(this);
        zhou_edit.setOnClickListener(this);
        yue_edit.setOnClickListener(this);
        nian_edit.setOnClickListener(this);




        miao_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                time=1;

                return false;
            }
        });
        fen_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                time=2;

                return false;
            }
        });
        shi_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                time=3;

                return false;
            }
        });
        ri_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                time=4;

                return false;
            }
        });
        zhou_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                time=5;

                return false;
            }
        });
        yue_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                time=6;

                return false;
            }
        });
        nian_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                time=7;

                return false;
            }
        });




    }


    @Override
    public void onClick(View view) {
        String str="";
        double value=0.0;
        DecimalFormat decimalFormat = new DecimalFormat();
        switch (view.getId()){
            case R.id.number0:
            case R.id.number1:
            case R.id.number2:
            case R.id.number3:
            case R.id.number4:
            case R.id.number5:
            case R.id.number6:
            case R.id.number7:
            case R.id.number8:
            case R.id.number9:
            case R.id.dot:
                if (clean) {
                    clean = false;
                }
                try {
                    if(time==1){
                        str=miao_edit.getText().toString();
                        miao_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(miao_edit.getText().toString());
                        fen_edit.setText(Double.toString(value/60));
                        shi_edit.setText(Double.toString(value/3600));
                        ri_edit.setText(Double.toString(value/60/60/24));
                        zhou_edit.setText(Double.toString(value/60/60/24/7));
                        yue_edit.setText(Double.toString(value/60/60/24/30));
                        nian_edit.setText(Double.toString(value/60/60/24/365));
                        miao_edit.setSelection(miao_edit.getText().length());
                        miao_edit.getSelectionEnd();
                        break;
                    }
                    if(time==2){
                        str=fen_edit.getText().toString();
                        fen_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(fen_edit.getText().toString());
                        miao_edit.setText(Double.toString(value*60));
                        shi_edit.setText(Double.toString(value/60));
                        ri_edit.setText(Double.toString(value/60/24));
                        zhou_edit.setText(Double.toString(value/60/24/7));
                        yue_edit.setText(Double.toString(value/60/24/30));
                        nian_edit.setText(Double.toString(value/60/24/365));
                        fen_edit.setSelection(fen_edit.getText().length());
                        fen_edit.getSelectionEnd();
                        break;
                    }
                    if(time==3){
                        str=shi_edit.getText().toString();
                        shi_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(shi_edit.getText().toString());
                        fen_edit.setText(Double.toString(value*60*60));
                        miao_edit.setText(Double.toString(value*60));
                        ri_edit.setText(Double.toString(value/24));
                        zhou_edit.setText(Double.toString(value/24/7));
                        yue_edit.setText(Double.toString(value/24/30));
                        nian_edit.setText(Double.toString(value/24/365));
                        shi_edit.setSelection(shi_edit.getText().length());
                        shi_edit.getSelectionEnd();
                        break;
                    }
                    if(time==4){
                        str=ri_edit.getText().toString();
                        ri_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(ri_edit.getText().toString());
                        fen_edit.setText(Double.toString(value*60*24));
                        shi_edit.setText(Double.toString(value*24));
                        miao_edit.setText(Double.toString(value*60*60*24));
                        zhou_edit.setText(Double.toString(value/7));
                        yue_edit.setText(Double.toString(value/30));
                        nian_edit.setText(Double.toString(value/365));
                        ri_edit.setSelection(ri_edit.getText().length());
                        ri_edit.getSelectionEnd();
                        break;
                    }
                    if(time==5){
                        str=zhou_edit.getText().toString();
                        zhou_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(zhou_edit.getText().toString());
                        fen_edit.setText(Double.toString(value*60*24*7));
                        shi_edit.setText(Double.toString(value*24*7));
                        ri_edit.setText(Double.toString(value*7));
                        miao_edit.setText(Double.toString(value*60*60*24*7));
                        yue_edit.setText(Double.toString(value*7/30));
                        nian_edit.setText(Double.toString(value*7/365));
                        zhou_edit.setSelection(zhou_edit.getText().length());
                        zhou_edit.getSelectionEnd();
                        break;
                    }
                    if(time==6){
                        str=yue_edit.getText().toString();
                        yue_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(yue_edit.getText().toString());
                        fen_edit.setText(Double.toString(value*60*24*30));
                        shi_edit.setText(Double.toString(value*24*30));
                        ri_edit.setText(Double.toString(value*30));
                        zhou_edit.setText(Double.toString(value/7*30));
                        miao_edit.setText(Double.toString(value*60*60*24*30));
                        nian_edit.setText(Double.toString(value*30/365));
                        yue_edit.setSelection(yue_edit.getText().length());
                        yue_edit.getSelectionEnd();
                        break;
                    }
                    if(time==7){
                        str=nian_edit.getText().toString();
                        nian_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(nian_edit.getText().toString());
                        fen_edit.setText(Double.toString(value*365*24*60));
                        shi_edit.setText(Double.toString(value*365*24));
                        ri_edit.setText(Double.toString(value*365));
                        zhou_edit.setText(Double.toString(value*365/7));
                        yue_edit.setText(Double.toString(value*365/30));
                        miao_edit.setText(Double.toString(value*60*60*24*365));
                        nian_edit.setSelection(nian_edit.getText().length());
                        nian_edit.getSelectionEnd();
                        break;
                    }
                    break;

                }catch (Exception e){

                }

            case R.id.clean_all:
                if (!clean) {
                    miao_edit.setText("");
                    fen_edit.setText("");
                    shi_edit.setText("");
                    ri_edit.setText("");
                    zhou_edit.setText("");
                    yue_edit.setText("");
                    nian_edit.setText("");
                    clean = true;
                    break;
                }
                break;

        }

    }
}
