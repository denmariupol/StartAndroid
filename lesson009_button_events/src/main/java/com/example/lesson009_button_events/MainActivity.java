package com.example.lesson009_button_events;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tView;
    Button okButton;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tView = (TextView)findViewById(R.id.tvOut);
        okButton = (Button)findViewById(R.id.btnOk);
        cancelButton = (Button)findViewById(R.id.btnCancel);

        OnClickListener oclBtnOk = new OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText("Нажата кнопка ОК");
            }
        };
        okButton.setOnClickListener(oclBtnOk);

        OnClickListener oclBtnCancel = new OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText("Нажата кнопка Cancel");
            }
        };
        cancelButton.setOnClickListener(oclBtnCancel);
    }
}
