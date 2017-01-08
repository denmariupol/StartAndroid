package com.example.lesson024_activity_lifecycle_2_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final String TAG = "States";
    Button btnActTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActTwo = (Button)findViewById(R.id.btnActTwo);
        btnActTwo.setOnClickListener(this);

        Log.d(TAG,"Main Activity: onCreate()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"Main Activity: onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"Main Activity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy()");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
