package com.example.lesson026_intent_filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button dateBtn,timeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateBtn = (Button)findViewById(R.id.btnDate);
        timeBtn = (Button)findViewById(R.id.btnTime);

        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btnDate:
                intent = new Intent("showDate");
                break;

            case R.id.btnTime:
                intent = new Intent("showTime");
                break;
        }
        startActivity(intent);
    }
}
