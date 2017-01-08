package com.example.lesson026_intent_filter;

import android.app.Activity;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by den on 2017-01-08.
 */

public class DateActivity extends Activity {
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(new Date(System.currentTimeMillis()));

        TextView tv = (TextView)findViewById(R.id.tvDate);
        tv.setText(date);
    }
}
