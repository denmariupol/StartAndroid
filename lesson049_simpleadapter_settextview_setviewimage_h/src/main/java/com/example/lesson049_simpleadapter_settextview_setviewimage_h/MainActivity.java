package com.example.lesson049_simpleadapter_settextview_setviewimage_h;

import android.app.Activity;
import android.content.Context;
import android.content.Loader;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends Activity {

    private String name = "NAME";
    private String number = "NUMBER";
    private String color = "COLOR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.list);

        ArrayList<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> map;
        int[] values = {-5,5,2,0,-1};
        for (int i = 0; i < 5; i++) {
            map = new HashMap<String, Object>();
            map.put(name,"name"+i);
            map.put(number,i);
            map.put(color,values[i]);
            data.add(map);
        }

        String[] from = {name,number,color};
        int[] to = {R.id.nameItem,R.id.numberItem,R.id.imageItem};

        MyAdapter adapter = new MyAdapter(this,data,R.layout.item,from,to);
        listView.setAdapter(adapter);
    }


    private class MyAdapter extends SimpleAdapter{

        /**
         * Constructor
         *
         * @param context  The context where the View associated with this SimpleAdapter is running
         * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
         *                 Maps contain the data for each row, and should include all the entries specified in
         *                 "from"
         * @param resource Resource identifier of a view layout that defines the views for this list
         *                 item. The layout file should include at least those named views defined in "to"
         * @param from     A list of column names that will be added to the Map associated with each
         *                 item.
         * @param to       The views that should display column in the "from" parameter. These should all be
         *                 TextViews. The first N views in this list are given the values of the first N columns
         */
        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public void setViewText(TextView v, String text) {
            super.setViewText(v, text);
            if(v.getId() == R.id.numberItem){
                if(Integer.valueOf(text) > 2) {
                    v.setTextColor(Color.GREEN);
                    Log.d("!!!",Integer.valueOf(text)+"");
                }else
                    v.setTextColor(Color.RED);
            }
        }

        @Override
        public void setViewImage(ImageView v, int value) {
            super.setViewImage(v, value);
            if(v.getId() == R.id.imageItem && value > 0) {
                v.setBackgroundColor(Color.GREEN);

            }else
                v.setBackgroundColor(Color.RED);
        }
    }
}
