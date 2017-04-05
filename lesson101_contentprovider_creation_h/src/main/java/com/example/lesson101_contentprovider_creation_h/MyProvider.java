package com.example.lesson101_contentprovider_creation_h;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;



/**
 * Created by den on 2017-03-31.
 */

public class MyProvider extends ContentProvider{
    private final String DB_NAME = "my";
    private int DB_VERSION = 1;

    private static final String AUTHORITY = "com.example.lesson101_contentprovider_creation_h";
    private static final String PATH = "table";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    public static final String DB_TABLE = "MYTABLE";
    public static final String ID = "_id";
    public static final String TEXT = "text";

    private final String CREATE_DB = "CREATE TABLE " + DB_TABLE + " ("
            + ID + " integer primary key autoincrement, "
            + TEXT + " text);";

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private static final int TABLE = 1;
    private static final int TABLE_ID = 2;

    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + PATH;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, PATH, TABLE);
        uriMatcher.addURI(AUTHORITY, PATH + "/#", TABLE_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());

        return true;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        database = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case TABLE:
                sortOrder = ID;
                break;
            case TABLE_ID:
                String id = uri.getLastPathSegment();
                sortOrder = TEXT;
                break;
        }
        Cursor cursor = database.query(DB_TABLE,projection,selection,selectionArgs,null,null,sortOrder);
        Log.d("!!!","query -> "+uri.toString());
        cursor.setNotificationUri(getContext().getContentResolver(),CONTENT_URI);
        return cursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE:

                break;
            case TABLE_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        database = dbHelper.getWritableDatabase();
        Log.d("!!!","insert -> "+uri.toString());
        if(uriMatcher.match(uri) != TABLE)
            throw new IllegalArgumentException("Wrong URI: "+uri);

        long id = database.insert(DB_TABLE,null,values);
        Uri resultUri = ContentUris.withAppendedId(CONTENT_URI,id);
        Log.d("!!!","insert after -> "+resultUri.toString());
        getContext().getContentResolver().notifyChange(resultUri,null);
        return resultUri;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        database = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case TABLE:
                break;
            case TABLE_ID:
                String id  = uri.getLastPathSegment();
                selection = ID + " = " + id;
                break;
        }
        int c =  database.delete(DB_TABLE,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return c;
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        database = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case TABLE:
                break;
            case TABLE_ID:
                String id = uri.getLastPathSegment();
                selection = ID + " = " +id;
                Log.d("!!!",id);
                break;
        }
        int c =  database.update(DB_TABLE,values,selection,null);
        getContext().getContentResolver().notifyChange(uri, null);
        return c;
    }





    private class DBHelper extends SQLiteOpenHelper{


        public DBHelper(Context context) {
            super(context,DB_NAME,null,DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            updateDB(db,0,DB_VERSION);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


        private void updateDB(SQLiteDatabase db, int oldVersion, int newVersion){
            if(oldVersion < 1)
                db.execSQL(CREATE_DB);
        }
    }



}
