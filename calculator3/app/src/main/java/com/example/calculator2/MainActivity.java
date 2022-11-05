package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    TextView textView1, textView2, textView_op, textView_res;
    com.example.calculator2.Calculator calculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textview_num1);
        textView_res = (TextView) findViewById(R.id.textview_res);

        Button t_C = (Button) findViewById(R.id.C);
        Button t_sqrt = (Button) findViewById(R.id.sqrt);
        Button t_surplus = (Button) findViewById(R.id.surplus);
        Button t_add = (Button) findViewById(R.id.add);

        Button t_num7 = (Button) findViewById(R.id.num7);
        Button t_num8 = (Button) findViewById(R.id.num8);
        Button t_num9 = (Button) findViewById(R.id.num9);
        Button t_sub = (Button) findViewById(R.id.sub);

        Button t_num4 = (Button) findViewById(R.id.num4);
        Button t_num5 = (Button) findViewById(R.id.num5);
        Button t_num6 = (Button) findViewById(R.id.num6);
        Button t_multi = (Button) findViewById(R.id.multi);

        Button t_num1 = (Button) findViewById(R.id.num1);
        Button t_num2 = (Button) findViewById(R.id.num2);
        Button t_num3 = (Button) findViewById(R.id.num3);
        Button t_div = (Button) findViewById(R.id.div);

        Button t_AC = (Button) findViewById(R.id.AC);
        Button t_num0 = (Button) findViewById(R.id.num0);
        Button t_dot = (Button) findViewById(R.id.dot);
        Button t_equal = (Button) findViewById(R.id.equal);

        Button t_leftbr = (Button) findViewById(R.id.leftbr);
        Button t_rightbr = (Button) findViewById(R.id.rightbr);
        Button t_minus = (Button) findViewById(R.id.minus);
        Button t_pow = (Button) findViewById(R.id.pow);


        calculator = new Calculator();
        Toast t = Toast.makeText(getApplicationContext(), "错误输入", Toast.LENGTH_LONG);

        View.OnClickListener buttonlistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String show = ((Button) view).getText().toString();
                calculator.process(show);
                textView1.setText(calculator.getStr());
                String str= calculator.getStr();
                textView_res.setText(calculator.getSres());

                String res = calculator.getSres();
                if (res.equals("ERROR")) {
                    t.show();
                    calculator.clear();
                    textView1.setText(calculator.getStr());
                    textView_res.setText(calculator.getSres());
                }

            }
        };
        t_num0.setOnClickListener(buttonlistener);
        t_num1.setOnClickListener(buttonlistener);
        t_num2.setOnClickListener(buttonlistener);
        t_num3.setOnClickListener(buttonlistener);
        t_num4.setOnClickListener(buttonlistener);
        t_num5.setOnClickListener(buttonlistener);
        t_num6.setOnClickListener(buttonlistener);
        t_num7.setOnClickListener(buttonlistener);
        t_num8.setOnClickListener(buttonlistener);
        t_num9.setOnClickListener(buttonlistener);

        t_C.setOnClickListener(buttonlistener);
        t_sqrt.setOnClickListener(buttonlistener);
        t_surplus.setOnClickListener(buttonlistener);
        t_AC.setOnClickListener(buttonlistener);

        t_add.setOnClickListener(buttonlistener);
        t_sub.setOnClickListener(buttonlistener);
        t_multi.setOnClickListener(buttonlistener);
        t_div.setOnClickListener(buttonlistener);

        t_dot.setOnClickListener(buttonlistener);
        t_equal.setOnClickListener(buttonlistener);

        t_leftbr.setOnClickListener(buttonlistener);
        t_rightbr.setOnClickListener(buttonlistener);
        t_minus.setOnClickListener(buttonlistener);
        t_pow.setOnClickListener(buttonlistener);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_normal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean  onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Hexcal:
                Intent intent1 = new Intent();
                intent1.setClass(getApplicationContext(), HexcalActivity.class);
                startActivity(intent1);
                break;
            case R.id.ratecal:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), Rate.class);
                startActivity(intent2);
                break;
            case R.id.unitcal:
                Intent intent3 = new Intent();
                intent3.setClass(getApplicationContext(), Unitconversion.class);
                startActivity(intent3);
                break;
            case R.id.loancal:
                Intent intent4 = new Intent();
                intent4.setClass(getApplicationContext(), LoanActivity.class);
                startActivity(intent4);
                break;
            case R.id.complexcal:
                Intent intent5 = new Intent();
                intent5.setClass(getApplicationContext(), ComplexActivity.class);
                startActivity(intent5);
                break;

            default:break;


        }
        return true;
    }
}









