package com.example.lesson050_simpleadapter_viewbinder_h;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private String name = "NAME";
    private String value = "VALUE";
    private String color = "COLOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        String[] names = {"Monday", "Tuesday", "Wensday", "Thurthday", "Friday"};
        int[] values = {50, 22, 16, -8, 42};
        int[] colors = {Color.BLUE, Color.BLACK, Color.RED, Color.CYAN, Color.GREEN};
        Map<String, Object> map;
        for (int i = 0; i < 5; i++) {
            map = new HashMap<>();
            map.put(name, names[i]);
            map.put(value, values[i]);
            map.put(color, colors[i]);
            data.add(map);
        }

        String[] from = {name, value, color};
        int[] to = {R.id.textView, R.id.valueView, R.id.imageView};
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        adapter.setViewBinder(new MyBinder());
        listView.setAdapter(adapter);
    }


    private class MyBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            switch (view.getId()) {
                case R.id.textView:
                    if (textRepresentation.equals("Monday") || textRepresentation.equals("Tuesday")) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            view.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                            ((TextView) view).setText(textRepresentation);
                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            ((TextView) view).setText(textRepresentation);
                        }
                    }
                    break;
                case R.id.valueView:
                    ((TextView) view).setText(textRepresentation);
                    break;
                case R.id.imageView:
                    ((ImageView) view).setBackgroundColor(Integer.valueOf(textRepresentation));
                    break;
            }

            return true;
        }
    }
}
