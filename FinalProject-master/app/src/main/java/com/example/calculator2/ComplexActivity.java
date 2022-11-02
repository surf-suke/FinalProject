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

public class ComplexActivity extends AppCompatActivity {
    TextView rp1, ip1, ip2, rp2,textView_res,textView_operation;
    com.example.calculator2.ComplexCalculator calculator;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex);


        textView_res = (TextView) findViewById(R.id.textView_res);
        textView_operation = (TextView) findViewById(R.id.textView_operation);
        rp1=(TextView) findViewById(R.id.textView_rp1);
        ip1=(TextView) findViewById(R.id.textView_ip1);
        rp2=(TextView) findViewById(R.id.textView_rp2);
        ip2=(TextView) findViewById(R.id.textView_ip2);
        rp1.setText("0");
        ip1.setText("0");
        rp2.setText("0");
        ip2.setText("0");

        Button t_add = (Button) findViewById(R.id.button_plus);
        Button t_sub = (Button) findViewById(R.id.button_sub);
        Button t_multi = (Button) findViewById(R.id.button_multi);
        Button t_div = (Button) findViewById(R.id.button_div);




        calculator = new ComplexCalculator();
        Toast t = Toast.makeText(getApplicationContext(), "错误输入", Toast.LENGTH_LONG);

        View.OnClickListener buttonlistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r1=rp1.getText().toString();
                String r2=rp2.getText().toString();
                String i1=ip1.getText().toString();
                String i2=ip2.getText().toString();

                String operation = ((Button) view).getText().toString();
                textView_operation.setText(operation);

                calculator.process(r1,r2,i1,i2,operation);
                textView_res.setText(calculator.getSres());

                String res = calculator.getSres();


            }
        };

        t_add.setOnClickListener(buttonlistener);
        t_sub.setOnClickListener(buttonlistener);
        t_multi.setOnClickListener(buttonlistener);
        t_div.setOnClickListener(buttonlistener);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_complex, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean  onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normalcal:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
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
            default:break;
        }
        return true;
    }
}
