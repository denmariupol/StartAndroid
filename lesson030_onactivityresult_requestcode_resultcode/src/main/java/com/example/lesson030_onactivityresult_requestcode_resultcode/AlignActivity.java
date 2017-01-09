package com.example.lesson030_onactivityresult_requestcode_resultcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class AlignActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLeft,btnCenter,btnRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);

        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        btnCenter = (Button)findViewById(R.id.btnCenter);

        btnCenter.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btnLeft:
                intent.putExtra("alignment", Gravity.LEFT);
                break;
            case R.id.btnRight:
                intent.putExtra("alignment", Gravity.RIGHT);
                break;
            case R.id.btnCenter:
                intent.putExtra("alignment", Gravity.CENTER);
                break;
        }
        setResult(RESULT_OK,intent);
        finish();
    }
}
