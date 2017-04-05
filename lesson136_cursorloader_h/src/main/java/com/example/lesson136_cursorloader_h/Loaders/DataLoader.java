package com.example.lesson136_cursorloader_h.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.lesson136_cursorloader_h.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by den on 2017-03-29.
 */

public class DataLoader extends AsyncTaskLoader<Cursor> {
    private Context context;
    private static final String URL_PATH = "https://www.056.ua/apitest/newstest";
    private URL url;
    private Util util;

    public DataLoader(Context context,Util util) {
        super(context);
        this.context = context;
        this.util = util;
//        forceLoad();
    }



    @Override
    public Cursor loadInBackground() {
        HttpURLConnection connection = null;

        try{
            url = new URL(URL_PATH);
            connection = (HttpURLConnection)url.openConnection();
            int responce = connection.getResponseCode();
            if(responce == HttpURLConnection.HTTP_OK){
                StringBuilder builder = new StringBuilder();
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    while ((line = reader.readLine()) != null)
                        builder.append(line);

                    return util.parseJsonAndGetCursor(new JSONObject(builder.toString()));

                }catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
