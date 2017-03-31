package com.example.lesson048_h;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private String name = "NAME";
    private String check = "CHECK";
    private String pic = "PIC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.list);
        Boolean[] checkList = {true,true,false,false,true};
        int picture = R.mipmap.ic_launcher;

        Map<String,Object> map;
        ArrayList<Map<String,Object>> array = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            map = new HashMap<>();
            map.put(name,"sometext"+i);
            map.put(check,checkList[i]);
            map.put(pic,picture);
            array.add(map);
        }

        String [] from = {name,check,pic};
        int[] to = {R.id.listItem,R.id.checkBox,R.id.imageView};
        SimpleAdapter adapter = new SimpleAdapter(this,array,R.layout.item,from,to);
        listView.setAdapter(adapter);
    }
}
