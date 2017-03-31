package com.example.lesson041_layoutinflater_list_h;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linLayout = (LinearLayout)findViewById(R.id.linLay);

        for (int i = 0; i < 5; i++) {
            View v = getLayoutInflater().inflate(R.layout.item,null);
            linLayout.addView(v);
        }
    }
}
