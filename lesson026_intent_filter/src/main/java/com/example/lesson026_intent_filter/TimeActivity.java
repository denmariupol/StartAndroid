package com.example.lesson026_intent_filter;

import android.app.Activity;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

/**
 * Created by den on 2017-01-08.
 */

public class TimeActivity extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date(System.currentTimeMillis()));

        TextView tv = (TextView)findViewById(R.id.tvTime);
        tv.setText(time);
    }

}

