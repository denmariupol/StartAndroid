package com.example.lesson101_contentprovider_creation;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity  implements LoaderCallbacks<Cursor> {

    final Uri CONTACT_URI = Uri.parse("com.example.lesson101_contentprovider_creation.AddressBook/contacts");
    final String CONTACT_NAME = "name";
    final String CONTACT_EMAIL = "email";
    private static final int LOADER_ID = 0;
    private SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(CONTACT_URI,null,null,null,null);
        Log.d("!@!",String.valueOf(cursor.getCount()));

        String []from = {"name","email"};
        int []to = {android.R.id.text1,android.R.id.text2};
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to, 0);
        ListView listView = (ListView)findViewById(R.id.lvContact);
        listView.setAdapter(adapter);

//        getLoaderManager().initLoader(LOADER_ID,null,this);
    }


    public void onClickInsert(View v){
        ContentValues cv = new ContentValues();
        cv.put(CONTACT_NAME,"INSERT NAME");
        cv.put(CONTACT_EMAIL,"INSERT EMAIL");
        Uri newUri = getContentResolver().insert(CONTACT_URI,cv);
    }


    public void onClickUpdate(View v){
        ContentValues cv = new ContentValues();
        cv.put(CONTACT_NAME,"UPDATE NAME");
        cv.put(CONTACT_EMAIL,"UPDATE EMAIL");
        Uri uri = ContentUris.withAppendedId(CONTACT_URI,2);
        int cnt = getContentResolver().update(uri,cv,null,null);
    }


    public void onClickDelete(View v){
        Uri uri = ContentUris.withAppendedId(CONTACT_URI,3);
        int cnt = getContentResolver().delete(uri,null,null);
    }


    public void onClickError(View v){
        Uri uri = Uri.parse("content://ru.startandroid.providers.AdressBook/phones");
        try {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        } catch (Exception ex) {

        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id == LOADER_ID)
            return new Loader<Cursor>(this);
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }
}
