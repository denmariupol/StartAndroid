package com.example.lesson040_layoutinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        public View inflate (int resource, ViewGroup root, boolean attachToRoot)

        resource - ID layout-файла, который будет использован для создания View. Например - R.layout.main
        root – родительский ViewGroup-элемент для создаваемого View. LayoutParams от этого ViewGroup присваиваются создаваемому View.
        attachToRoot – присоединять ли создаваемый View к root. Если true, то root становится родителем создаваемого View.
        Т.е. это равносильно команде root.addView(View).Если false – то создаваемый View просто получает LayoutParams от root,
        но его дочерним элементом не становится

         */
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.text,null,false);
        LayoutParams layoutParams = view.getLayoutParams();

//        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linLayout);
//        linearLayout.addView(view);

        Log.d(LOG_TAG, "Class of view: " + view.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is null: " + (layoutParams == null));
        Log.d(LOG_TAG, "Text of view: " + ((TextView) view).getText());

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = layoutInflater.inflate(R.layout.text, relLayout, true);
        LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.getClass().toString());
//        Log.d(LOG_TAG, "Text of view2: " + ((TextView) view2).getText());
    }
}
