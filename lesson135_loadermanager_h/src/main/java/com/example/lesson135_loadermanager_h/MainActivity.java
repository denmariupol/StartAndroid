package com.example.lesson135_loadermanager_h;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements LoaderCallbacks<String> {

    RandomLoader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().initLoader(0,null,MainActivity.this).forceLoad();
            }
        });
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.d("!!!","onCreateLoader");
        loader = new RandomLoader(this,args);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d("!!!","onLoadFinished -> "+data);

        TextView textView = (TextView)findViewById(R.id.text);
        textView.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d("!!!","onLoaderReset");
    }
}
