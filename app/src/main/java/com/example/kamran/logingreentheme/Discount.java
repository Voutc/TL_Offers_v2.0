package com.example.kamran.logingreentheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Discount extends AppCompatActivity {
    EditText productnametxt,pricetxt,discounttxt,infotxt;
    Button publishbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        productnametxt = (EditText) findViewById(R.id.productname);
        pricetxt = (EditText) findViewById(R.id.price);
        discounttxt = (EditText) findViewById(R.id.discount);
        infotxt = (EditText) findViewById(R.id.info);
        publishbtn = (Button) findViewById(R.id.publish);
    }
}
