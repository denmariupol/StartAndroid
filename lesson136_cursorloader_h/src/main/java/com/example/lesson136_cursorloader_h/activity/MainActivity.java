package com.example.lesson136_cursorloader_h.activity;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.lesson136_cursorloader_h.DB.DB;
import com.example.lesson136_cursorloader_h.Loaders.DataLoader;
import com.example.lesson136_cursorloader_h.R;
import com.example.lesson136_cursorloader_h.utils.Util;
import com.facebook.stetho.Stetho;

import java.util.Random;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor> {
    private final int LOADER_ID = 1;
    private Util util;
    private DB db;
    private SimpleCursorAdapter scAdapter,updatedAdapter;
    private ListView listView;
    private Cursor cursor;
    private int colId;
    private int colName;
    private int colImage;
    String[]from;
    int[] to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stethoInit();

        db = new DB(this);
        db.open();
        util = new Util(this,db);

        listView = (ListView)findViewById(R.id.listActivity);
        cursor = db.read();

        colId = cursor.getColumnIndex(DB.ID);
        colName = cursor.getColumnIndex(DB.COLUMN_TITLE);
        colImage = cursor.getColumnIndex(DB.COLUMN_IMAGE);

        if (cursor.moveToFirst()){
            do{
                String data = cursor.getString(cursor.getColumnIndex(DB.COLUMN_TITLE));
                Log.d("!!!",data);
                // do what ever you want here
            }while(cursor.moveToNext());
        }

//        Log.d("cursor", String.valueOf(cursor.getCount()));
        from = new String[]{DB.COLUMN_TITLE,DB.COLUMN_IMAGE};
        to = new int[]{R.id.textView,R.id.imageView};

        scAdapter = new MyCursorAdapter(this,R.layout.item,cursor,from,to,0);
        listView.setAdapter(scAdapter);

        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DataLoader(this,util);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursor.moveToLast();
        data.moveToLast();
        int cursorLastPosition = cursor.getInt(colId);
        int dataLastPosition = data.getInt(colId);

        data.moveToPosition(cursorLastPosition+1);

        updatedAdapter = new MyCursorAdapter(this,R.layout.item,data,from,to,0);
        listView.setAdapter(updatedAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();

    }

    private void stethoInit() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }


    public void addToDB(View v){
        Random random = new Random();
        db.add("some title"+random.nextFloat() ,null);
        cursor = db.read();
        Cursor newCursor = db.read();
        scAdapter.changeCursor(newCursor);
        scAdapter.notifyDataSetChanged();
    }
    private class MyCursorAdapter extends SimpleCursorAdapter{
        private int layout;
        public MyCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }


        @Override
        public View newView(Context context, final Cursor cursor, final ViewGroup parent) {
            View view = getLayoutInflater().inflate(layout,parent,false);
            return view;
        }


        @Override
        public void bindView(final View view, Context context, Cursor cursor) {
            final View v = view;

            String title = cursor.getString(colName);

            TextView textView = (TextView)view.findViewById(R.id.textView);
            textView.setText(title);

            byte[] imageArray = cursor.getBlob(colImage);
            Bitmap image = util.getImageFromBytes(imageArray);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            imageView.setImageBitmap(image);

            Button delButton = (Button)view.findViewById(R.id.delButton);

            final int id = cursor.getInt(colId);

            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.delete(id);
                    Cursor newCursor = db.read();
                    changeCursor(newCursor);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            super.registerDataSetObserver(observer);
        }
    }
}

