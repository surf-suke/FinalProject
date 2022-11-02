package com.example.calculator2;


import  android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Unitconversion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        Button changdu = (Button) findViewById(R.id.chang_du);
        changdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Unitconversion.this, Length.class);
                startActivity(intent);
            }
        });
        Button tiji = (Button) findViewById(R.id.ti_ji);
        tiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Unitconversion.this, Volume.class);
                startActivity(intent);
            }
        });

        Button shijian = (Button) findViewById(R.id.shi_jian);
        shijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Unitconversion.this, Time.class);
                startActivity(intent);
            }
        });
        Button zhongliang = (Button) findViewById(R.id.zhong_liang);
        zhongliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Unitconversion.this, Weight.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_rate, menu);
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

            case R.id.complexcal:
                Intent intent_complex = new Intent();
                intent_complex.setClass(getApplicationContext(), ComplexActivity.class);
                startActivity(intent_complex);
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
