package com.example.lesson008_working_with_code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView myView = (TextView) findViewById(R.id.textView);
        myView.setText("new text");

        Button myBtn = (Button)findViewById(R.id.button);
        myBtn.setText("!!!!!!");
        myBtn.setEnabled(false);

        CheckBox myChk = (CheckBox) findViewById(R.id.checkBox);
        myChk.setChecked(true);
    }
}
