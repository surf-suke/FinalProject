package com.example.calculator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoanActivity extends AppCompatActivity {
    String mortgage;        //贷款金额（万元）
    String rate;            //贷款年利率（%）
    String time;            //贷款时间（年）
    String aheadTime;       //第几年全部还款

    EditText mortgageEdit;
    EditText timeEdit;
    EditText interestEdit;
    EditText aheadTimeEdit;
    Button calButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        mortgageEdit = (EditText)findViewById(R.id.mortgageEditText);
        timeEdit = (EditText)findViewById(R.id.timeEditText);
        interestEdit = (EditText)findViewById(R.id.interestEditText);
        aheadTimeEdit = (EditText)findViewById(R.id.aheadTimeEditText);
        calButton = (Button)findViewById(R.id.calButton);

        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mortgage = mortgageEdit.getText().toString();
                rate = interestEdit.getText().toString();
                time = timeEdit.getText().toString();
                aheadTime = aheadTimeEdit.getText().toString();

                if (mortgage.length() == 0 || rate.length() == 0|| time.length() == 0 || aheadTime.length() == 0) {    //没有输入数值则提示
                    Toast.makeText(LoanActivity.this, "请输入数值", Toast.LENGTH_SHORT).show();
                }else {

                    Intent intent = new Intent();
                    intent.setClass(LoanActivity.this, ResultActivity.class);      //跳转到课程列表
                    Bundle bundle = new Bundle();
                    bundle.putString("mortgage", mortgage);
                    bundle.putString("rate", rate);
                    bundle.putString("time", time);
                    bundle.putString("aheadTime", aheadTime);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_loan, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean  onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Hexcal:
                Intent intent1 = new Intent();
                intent1.setClass(getApplicationContext(), HexcalActivity.class);
                startActivity(intent1);
                break;
            case R.id.normalcal:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.ratecal:
                Intent intent3 = new Intent();
                intent3.setClass(getApplicationContext(), Rate.class);
                startActivity(intent3);
                break;
            case R.id.unitcal:
                Intent intent4 = new Intent();
                intent4.setClass(getApplicationContext(), Unitconversion.class);
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
