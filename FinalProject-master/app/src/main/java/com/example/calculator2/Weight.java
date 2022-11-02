package com.example.calculator2;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Weight extends AppCompatActivity implements  View.OnClickListener{
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
    private EditText ke_edit;
    private EditText haoke_edit;
    private EditText qianke_edit;
    private EditText dun_edit;
    private EditText jin_edit;
    private EditText gongjin_edit;


    //文本框空标记
    boolean clean=true;
    int weight=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
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
        ke_edit=findViewById(R.id.ke_edit);
        haoke_edit=findViewById(R.id.haoke_edit);
        qianke_edit=findViewById(R.id.qianke_edit);
        dun_edit=findViewById(R.id.dun_edit);
        jin_edit=findViewById(R.id.jin_edit);
        gongjin_edit=findViewById(R.id.gongjin_edit);

        //        光标显示但不召出软键盘
        ke_edit.setShowSoftInputOnFocus(false);
        haoke_edit.setShowSoftInputOnFocus(false);
        qianke_edit.setShowSoftInputOnFocus(false);
        dun_edit.setShowSoftInputOnFocus(false);
        jin_edit.setShowSoftInputOnFocus(false);
        gongjin_edit.setShowSoftInputOnFocus(false);

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

        ke_edit.setOnClickListener(this);
        haoke_edit.setOnClickListener(this);
        qianke_edit.setOnClickListener(this);
        dun_edit.setOnClickListener(this);
        jin_edit.setOnClickListener(this);
        gongjin_edit.setOnClickListener(this);




        ke_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                weight=1;

                return false;
            }
        });
        haoke_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                weight=2;

                return false;
            }
        });
        qianke_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                weight=3;

                return false;
            }
        });
        dun_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                weight=4;

                return false;
            }
        });
        jin_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                weight=5;

                return false;
            }
        });
        gongjin_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                weight=6;

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
                    if(weight==1){
                        str=ke_edit.getText().toString();
                        ke_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(ke_edit.getText().toString());
                        haoke_edit.setText(Double.toString(value*1000));
                        qianke_edit.setText(Double.toString(value/1000));
                        dun_edit.setText(Double.toString(value/1000/1000));
                        jin_edit.setText(Double.toString(value/500));
                        gongjin_edit.setText(Double.toString(value/1000));
                        ke_edit.setSelection(ke_edit.getText().length());
                        ke_edit.getSelectionEnd();
                        break;
                    }
                    if(weight==2){
                        str=haoke_edit.getText().toString();
                        haoke_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(haoke_edit.getText().toString());
                        ke_edit.setText(Double.toString(value/1000));
                        qianke_edit.setText(Double.toString(value/1000/1000));
                        dun_edit.setText(Double.toString(value/1000/1000/1000));
                        jin_edit.setText(Double.toString(value/1000/500));
                        gongjin_edit.setText(Double.toString(value/1000/1000));
                        haoke_edit.setSelection(haoke_edit.getText().length());
                        haoke_edit.getSelectionEnd();
                        break;
                    }
                    if(weight==3){
                        str=qianke_edit.getText().toString();
                        qianke_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(qianke_edit.getText().toString());
                        haoke_edit.setText(Double.toString(value*1000*1000));
                        ke_edit.setText(Double.toString(value*1000));
                        dun_edit.setText(Double.toString(value/1000));
                        jin_edit.setText(Double.toString(value*2));
                        gongjin_edit.setText(Double.toString(value));
                        qianke_edit.setSelection(qianke_edit.getText().length());
                        qianke_edit.getSelectionEnd();
                        break;
                    }
                    if(weight==4){
                        str=dun_edit.getText().toString();
                        dun_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(dun_edit.getText().toString());
                        haoke_edit.setText(Double.toString(value*1000*1000*1000));
                        qianke_edit.setText(Double.toString(value*1000));
                        ke_edit.setText(Double.toString(value*1000*1000));
                        jin_edit.setText(Double.toString(value*1000*2));
                        gongjin_edit.setText(Double.toString(value*1000));
                        dun_edit.setSelection(dun_edit.getText().length());
                        dun_edit.getSelectionEnd();
                        break;
                    }
                    if(weight==5){
                        str=jin_edit.getText().toString();
                        jin_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(jin_edit.getText().toString());
                        ke_edit.setText(Double.toString(value*500));
                        qianke_edit.setText(Double.toString(value/2));
                        dun_edit.setText(Double.toString(value/2/1000));
                        haoke_edit.setText(Double.toString(value*500*1000));
                        gongjin_edit.setText(Double.toString(value/2));
                        jin_edit.setSelection(jin_edit.getText().length());
                        jin_edit.getSelectionEnd();
                        break;
                    }
                    if(weight==6){
                        str=gongjin_edit.getText().toString();
                        gongjin_edit.setText(str + ((Button) view).getText().toString() + "");
                        value = Double.parseDouble(gongjin_edit.getText().toString());
                        haoke_edit.setText(Double.toString(value*1000*1000));
                        ke_edit.setText(Double.toString(value*1000));
                        dun_edit.setText(Double.toString(value/1000));
                        jin_edit.setText(Double.toString(value*2));
                        qianke_edit.setText(Double.toString(value));
                        gongjin_edit.setSelection(gongjin_edit.getText().length());
                        gongjin_edit.getSelectionEnd();
                        break;
                    }
                    break;

                }catch (Exception e){

                }

            case R.id.clean_all:
                if (!clean) {
                    ke_edit.setText("");
                    haoke_edit.setText("");
                    qianke_edit.setText("");
                    dun_edit.setText("");
                    jin_edit.setText("");
                    gongjin_edit.setText("");
                    clean = true;
                    break;
                }
                break;

        }

    }
}
