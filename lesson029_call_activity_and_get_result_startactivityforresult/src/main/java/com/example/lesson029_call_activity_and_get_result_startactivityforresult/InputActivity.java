package com.example.lesson029_call_activity_and_get_result_startactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        name = (EditText)findViewById(R.id.etName);
        button = (Button)findViewById(R.id.btnOK);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("name",name.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
