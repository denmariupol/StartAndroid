package com.example.lesson010_optimize_button_events;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    TextView tView;
    Button okButton;
    Button cancelButton;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//
//        tView = (TextView)findViewById(R.id.tvOut);
//        okButton = (Button)findViewById(R.id.btnOk);
//        cancelButton = (Button)findViewById(R.id.btnCancel);
//
//        OnClickListener clickListener = new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.btnOk:
//                        tView.setText("Нажата кнопка ОК");
//                        break;
//                    case R.id.btnCancel:
//                        tView.setText("Нажата кнопка Cancel");
//                        break;
//                }
//            }
//        };
//
//        okButton.setOnClickListener(clickListener);
//        cancelButton.setOnClickListener(clickListener);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);

        tView = (TextView) findViewById(R.id.tvOut);
        okButton = (Button) findViewById(R.id.btnOk);
        cancelButton = (Button) findViewById(R.id.btnCancel);

        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                tView.setText("Нажата кнопка ОК");
                break;
            case R.id.btnCancel:
                tView.setText("Нажата кнопка Cancel");
                break;
        }
    }
    public void ClickButton(View v){
        tView.setText("нажата кнопка!!!");
    }
}
