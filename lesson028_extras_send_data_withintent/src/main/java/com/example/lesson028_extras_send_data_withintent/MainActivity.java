package com.example.lesson028_extras_send_data_withintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText firstName,lastName;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText)findViewById(R.id.etFName);
        lastName = (EditText)findViewById(R.id.etLName);
        submitBtn = (Button)findViewById(R.id.btnSubmit);

        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ViewActivity.class);
        intent.putExtra("firstName",firstName.getText().toString());
        intent.putExtra("lastName",lastName.getText().toString());
        startActivity(intent);
    }
}
