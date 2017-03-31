package com.example.lesson135_loadermanager_h;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by den on 2017-03-28.
 */

public class RandomLoader extends AsyncTaskLoader<String> {
    Bundle bundle;
    public RandomLoader(Context context, Bundle bundle) {
        super(context);
        this.bundle = bundle;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d("!!!","onStartLoading");

    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d("!!!","onStopLoading");

    }


    @Override
    public String loadInBackground() {
        try {
            TimeUnit.SECONDS.sleep(3);
            Log.d("!!!", "loadInBackground");
            return getRnd();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String getRnd(){
        Random random = new Random();
        return  String.valueOf(random.nextFloat());
    }

}
