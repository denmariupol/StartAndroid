package com.example.lesson051_simpleadapter_add_remove_h;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private String value = "value";
    private String id = "id";
    Map<String,Object> map;
    ArrayList<Map<String,Object>> data = new ArrayList<>();
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listView);


        for (int i = 0; i < 5; i++) {
            map = new HashMap<>();
            map.put(value,value+i);
            map.put(id,i);
            data.add(map);
        }


        String[]from = {value};
        int[] to ={R.id.textView};
        adapter = new MyAdapter(this,data);
//        adapter.setViewBinder(new MyBinder());
        listView.setAdapter(adapter);
    }


    public void clickItemButton(View v){


    }


    public void addClick(View v){
        map = new HashMap<>();
        map.put(value,value);
        data.add(map);
        adapter.notifyDataSetChanged();
    }


    private class MyAdapter extends BaseAdapter{
        Context context;
        ArrayList<Map<String, Object>> data;


        public MyAdapter(Context context, ArrayList<Map<String, Object>> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view  = getLayoutInflater().inflate(R.layout.item, parent, false);
            TextView textView = (TextView)view.findViewById(R.id.textView);
            textView.setText(data.get(position).get(value)+"");
            Button delButton = (Button) view.findViewById(R.id.deleteButton);
            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.remove(position);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}
