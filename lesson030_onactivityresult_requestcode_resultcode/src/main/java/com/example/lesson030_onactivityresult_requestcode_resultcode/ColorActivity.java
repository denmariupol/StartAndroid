package com.example.lesson030_onactivityresult_requestcode_resultcode;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRed,btnGreen,btnBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        btnBlue = (Button)findViewById(R.id.btnBlue);
        btnRed = (Button)findViewById(R.id.btnRed);
        btnGreen = (Button)findViewById(R.id.btnGreen);

        btnBlue.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btnBlue:
                intent.putExtra("color", Color.BLUE);
                break;
            case R.id.btnGreen:
                intent.putExtra("color", Color.GREEN);
                break;
            case R.id.btnRed:
                intent.putExtra("color", Color.RED);
                break;
        }
        setResult(RESULT_OK,intent);
        finish();
    }
}
