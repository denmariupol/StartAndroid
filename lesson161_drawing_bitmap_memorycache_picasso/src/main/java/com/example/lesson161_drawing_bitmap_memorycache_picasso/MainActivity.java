package com.example.lesson161_drawing_bitmap_memorycache_picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);

        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"/");
        File[] files = dir.listFiles();

        if(files != null){
            ImageAdapter adapter = new ImageAdapter(this,files);
            listView.setAdapter(adapter);
        }
    }

    static class ImageAdapter extends ArrayAdapter<File>{
        LayoutInflater inflater;
        Picasso picasso;
        public ImageAdapter(Context context,File[] files) {
            super(context, R.layout.list_item,files);
            inflater = LayoutInflater.from(context);
            picasso = Picasso.with(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if(view == null)
                view = inflater.inflate(R.layout.list_item,parent,false);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            picasso.load(getItem(position)).resizeDimen(R.dimen.image_size, R.dimen.image_size).centerInside().into(imageView);
            return view;
        }


    }
}
