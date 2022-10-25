package com.example.calculator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HexcalActivity extends AppCompatActivity {
    TextView textView1, textView2, textView_op, textView_res;
    com.example.calculator2.HexCalculator calculator;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.hexcal);


        textView1 = (TextView) findViewById(R.id.textview_num1);
        textView_res = (TextView) findViewById(R.id.textview_res);

        Button t_C = (Button) findViewById(R.id.C);
        Button t_E = (Button) findViewById(R.id.E);
        Button t_F = (Button) findViewById(R.id.F);
        Button t_add = (Button) findViewById(R.id.add);
        Button t_minus = (Button) findViewById(R.id.minus);

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

        Button t_A = (Button) findViewById(R.id.A);
        Button t_B = (Button) findViewById(R.id.B);
        Button t_HC = (Button) findViewById(R.id.HC);
        Button t_D = (Button) findViewById(R.id.D);


        calculator = new HexCalculator();
        Toast t = Toast.makeText(getApplicationContext(), "错误输入", Toast.LENGTH_LONG);

        View.OnClickListener buttonlistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t_add.setEnabled(true);
                t_sub.setEnabled(true);
                t_multi.setEnabled(true);
                t_div.setEnabled(true);
                t_dot.setEnabled(true);
                t_minus.setEnabled(true);
                t_equal.setEnabled(true);

                t_num0.setEnabled(true);
                t_num1.setEnabled(true);
                t_num2.setEnabled(true);
                t_num3.setEnabled(true);
                t_num4.setEnabled(true);
                t_num5.setEnabled(true);
                t_num6.setEnabled(true);
                t_num7.setEnabled(true);
                t_num8.setEnabled(true);
                t_num9.setEnabled(true);
                t_A.setEnabled(true);
                t_B.setEnabled(true);
                t_C.setEnabled(true);
                t_D.setEnabled(true);
                t_E.setEnabled(true);
                t_F.setEnabled(true);
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

                if(str.length()!=0&&"*÷+-%".indexOf(str.charAt(str.length()-1))>=0)
                {
                    t_add.setEnabled(false);
                    t_sub.setEnabled(false);
                    t_multi.setEnabled(false);
                    t_div.setEnabled(false);
                    t_dot.setEnabled(false);
                    t_equal.setEnabled(false);
                }
                if(str.length()!=0&&"—".indexOf(str.charAt(str.length()-1))>=0)
                {
                    t_add.setEnabled(false);
                    t_sub.setEnabled(false);
                    t_multi.setEnabled(false);
                    t_div.setEnabled(false);
                    t_dot.setEnabled(false);
                    t_equal.setEnabled(false);


                }
                if(str.length()!=0&&".".indexOf(str.charAt(str.length()-1))>=0)
                {
                    t_add.setEnabled(false);
                    t_sub.setEnabled(false);
                    t_multi.setEnabled(false);
                    t_div.setEnabled(false);
                    t_dot.setEnabled(false);
                    t_minus.setEnabled(false);
                    t_equal.setEnabled(false);


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
        t_AC.setOnClickListener(buttonlistener);
        t_add.setOnClickListener(buttonlistener);
        t_sub.setOnClickListener(buttonlistener);
        t_multi.setOnClickListener(buttonlistener);
        t_div.setOnClickListener(buttonlistener);
        t_dot.setOnClickListener(buttonlistener);
        t_equal.setOnClickListener(buttonlistener);
        t_minus.setOnClickListener(buttonlistener);

        t_A.setOnClickListener(buttonlistener);
        t_B.setOnClickListener(buttonlistener);
        t_HC.setOnClickListener(buttonlistener);
        t_D.setOnClickListener(buttonlistener);
        t_E.setOnClickListener(buttonlistener);
        t_F.setOnClickListener(buttonlistener);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_hex, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean  onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normalcal:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
        return true;
    }
}
