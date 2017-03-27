package com.example.lesson135_loadermanager;

import android.content.Context;
import android.content.Loader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by den on 2017-03-19.
 */

public class TimeLoader extends Loader<String> {
    final String LOG_TAG = "myLogs";
    final int PAUSE = 5;
    public static final String ARGS_TIME_FORMAT = "166";
    public static final String TIME_FORMAT_SHORT = "h:mm:ss a";
    public static final String TIME_FORMAT_LONG = "yyyy.MM.dd G 'at' HH:mm:ss";

    GetTimeTask getTimeTask;
    String format;

    public TimeLoader(Context context, Bundle args) {
        super(context);
        Log.d(LOG_TAG, hashCode() + " create TimeLoader");
        if(args != null)
            format = args.getString(ARGS_TIME_FORMAT);
        if(TextUtils.isEmpty(format))
            format = TIME_FORMAT_SHORT;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(LOG_TAG, hashCode() + " onStartLoading");
        if(takeContentChanged())
            forceLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.d(LOG_TAG, hashCode() + " onForceLoad");
        if(getTimeTask != null)
            getTimeTask.cancel(true);
        getTimeTask = new GetTimeTask();
        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,format);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(LOG_TAG, hashCode() + " onStopLoading");
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.d(LOG_TAG, hashCode() + " onAbandon");
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.d(LOG_TAG, hashCode() + " onReset");
    }

    void getResultFromTask(String result){
        deliverResult(result);
    }
    class GetTimeTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_TAG, TimeLoader.this.hashCode() + " doInBackground");
//            Log.d(LOG_TAG,params[0].toString());
            try {
                TimeUnit.SECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(params[0], Locale.getDefault());
            return sdf.format(new Date());
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(LOG_TAG, TimeLoader.this.hashCode() + " onPostExecute " + s);
            getResultFromTask(s);
        }
    }
}
