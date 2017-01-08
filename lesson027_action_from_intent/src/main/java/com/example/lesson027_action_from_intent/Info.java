package com.example.lesson027_action_from_intent;

import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        // получаем Intent, который вызывал это Activity
        Intent intent = getIntent();
        //читаем из него action
        String action = intent.getAction();

        String format = "",textInfo = "";

        // в зависимости от action заполняем переменные
        switch (action){
            case "showTime":
                format = "HH:mm:ss";
                textInfo = "Time: ";
                break;
            case "showDate":
                format = "dd.MM.yyyy";
                textInfo = "Date: ";
                break;
        }
        // в зависимости от содержимого переменной format
        // получаем дату или время в переменную datetime
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateTime = sdf.format(new Date(System.currentTimeMillis()));

        TextView tv = (TextView)findViewById(R.id.tvInfo);
        tv.setText(dateTime);
    }
}
