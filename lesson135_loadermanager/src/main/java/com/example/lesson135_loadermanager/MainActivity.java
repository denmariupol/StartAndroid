package com.example.lesson135_loadermanager;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.app.LoaderManager.LoaderCallbacks;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements LoaderCallbacks<String>{
    final String LOG_TAG = "myLogs";
    static final int LOADER_TIME_ID = 22;
    TextView textView;
    RadioGroup radioGroup;
    static int lastCheckedId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.tvTime);
        radioGroup = (RadioGroup)findViewById(R.id.rgTimeFormat);

        Bundle bundle = new Bundle();
        bundle.putString(null,getTimeFormat());
        getLoaderManager().initLoader(LOADER_TIME_ID,bundle,this);
        lastCheckedId = radioGroup.getCheckedRadioButtonId();
    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;

        if(id == LOADER_TIME_ID){
            loader = new TimeLoader(this,args);
            Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d(LOG_TAG, "onLoadFinished for loader " + loader.hashCode() + ", result = " + data);
        textView.setText(data);
    }


    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(LOG_TAG, "onLoaderReset for loader " + loader.hashCode());
    }


    public void getTimeClick(View v){
        Loader<String> loader = null;

        int id = radioGroup.getCheckedRadioButtonId();
        if(id == lastCheckedId){
            loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        }else{
            Bundle bundle = new Bundle();
            bundle.putString(TimeLoader.ARGS_TIME_FORMAT,getTimeFormat());
            loader = getLoaderManager().restartLoader(LOADER_TIME_ID,bundle,this);
            lastCheckedId = id;
        }
        loader.forceLoad();
    }


    String getTimeFormat(){
        String result = TimeLoader.TIME_FORMAT_SHORT;
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.rdShort:
                result = TimeLoader.TIME_FORMAT_SHORT;
                break;
            case R.id.rdLong:
                result = TimeLoader.TIME_FORMAT_LONG;
                break;
        }
        return result;
    }


    public void observerClick(View v){
        Log.d(LOG_TAG, "observerClick");
        Loader<String> loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        final ContentObserver observer = loader.new ForceLoadContentObserver();
        v.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                observer.dispatchChange(false,null);
            }
        },5000);
    }
}
