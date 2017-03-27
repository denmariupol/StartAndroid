package com.example.lesson136_cursorloader;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

    private static final int CM_DELETE_ID = 1;
    ListView listView;
    DB db;
    SimpleCursorAdapter scAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lvData);
        //open connection to DB
        db = new DB(this);
        db.open();

        //form data for adapter
        String[] from = new String[]{DB.COLUMN_IMG, DB.COLUMN_TXT};
        int[] to = new int[]{R.id.ivImg, R.id.lvData};

        scAdapter = new SimpleCursorAdapter(this, R.layout.item, null, from, to, 0);
        listView.setAdapter(scAdapter);

        //add contect menu to list
        registerForContextMenu(listView);

        //create loader for reading data
        getSupportLoaderManager().initLoader(0, null, this);
    }


    public void onButtonClick(View v) {
        //add rec
        db.addRec("sometext " + (scAdapter.getCount() + 1), R.mipmap.ic_launcher);
        //get new cursor with data
        getSupportLoaderManager().getLoader(0).forceLoad();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // get from context menu item data about list item
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            //get record id and delete apropriate record in DB
            db.delRec(acmi.id);
            //get new cursor
            getSupportLoaderManager().getLoader(0).forceLoad();
            return true;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this, db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    static class MyCursorLoader extends CursorLoader {
        DB db;

        public MyCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getAllData();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cursor;
        }
    }
}

