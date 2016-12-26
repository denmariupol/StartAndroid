package com.example.lesson013_simple_menu;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("menu1");
        menu.add("menu2");
        menu.add("menu3");
        menu.add("menu4");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
