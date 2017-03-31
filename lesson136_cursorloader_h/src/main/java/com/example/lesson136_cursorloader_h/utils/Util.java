package com.example.lesson136_cursorloader_h.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;

import com.example.lesson136_cursorloader_h.DB.DB;
import com.example.lesson136_cursorloader_h.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by den on 2017-03-29.
 */

public class Util {
    private DB db;
    private Context context;

    public Util(Context context,DB db) {
        this.context = context;
        this.db = db;

    }


    public Cursor parseJsonAndGetCursor(JSONObject jsonObject) {
        byte [] bmpArray = null;
        Cursor cursor;
//        db.drop();

        try {
            JSONArray array = jsonObject.getJSONArray("response");

            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                String title = item.getString("title");
                String photo = item.getString("photo");

                Bitmap bmp = getBitmapFromUrl(photo);
                if(bmp != null)
                    bmpArray = getBytesFromBitmap(bmp);

                db.add(title,bmpArray);
            }
            return db.read();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    // convert from bitmap to byte array
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


    // convert from byte array to bitmap
    public Bitmap getImageFromBytes(byte[] image) {
        if(image != null)
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
    }


    public Bitmap getBitmapFromUrl(String url) {
        Bitmap image = null;
        try {
            image = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
