package com.example.lesson012_logs_popups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tView;
    Button okButton;
    Button cancelButton;
    private static final String TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "найдем View-элементы");
        tView = (TextView) findViewById(R.id.tvOut);
        okButton = (Button) findViewById(R.id.btnOk);
        cancelButton = (Button) findViewById(R.id.btnCancel);
        Log.d(TAG, "присваиваем обработчик кнопкам");
        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "по id определяем кнопку, вызвавшую этот обработчик");
        switch (v.getId()) {
            case R.id.btnOk:
                tView.setText("Нажата кнопка ОК");
                Toast.makeText(this,"Нажата кнопка ОК",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCancel:
                tView.setText("Нажата кнопка Cancel");
                Toast.makeText(this,"Нажата кнопка Cancel",Toast.LENGTH_SHORT).show();
        }
    }
}
