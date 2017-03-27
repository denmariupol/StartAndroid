package com.example.lesson101_contentprovider_creation;

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
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by den on 2017-03-18.
 */

public class MyContactsProvider extends ContentProvider {
    //DB constants
    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;
    static final String CONTACT_TABLE = "contacts";
    static final String CONTACT_ID = "_id";
    static final String CONTACT_NAME = "name";
    static final String CONTACT_EMAIL = "email";

    // /creation db
    static final String DB_CREATE = "create table " + CONTACT_TABLE + "("
            + CONTACT_ID + " integer primary key autoincrement, "
            + CONTACT_NAME + " text, " + CONTACT_EMAIL + " text" + ");";

    //Uri
    //authority
    static final String AUTHORITY = "com.example.lesson101_contentprovider_creation.AddressBook";
    //path
    static final String CONTACT_PATH = "contacts";
    //common Uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTACT_PATH);

    //Data types
    //set of strings
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + CONTACT_PATH;
    //one string
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + CONTACT_PATH;

    //Uri matcher
    //common uri
    static final int URI_CONTACTS = 1;
    //Uri with matched ID
    static final int URI_CONTACTS_ID = 2;
    //creation of UriMatcher
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,CONTACT_PATH,URI_CONTACTS);
        uriMatcher.addURI(AUTHORITY,CONTACT_PATH + "/#",URI_CONTACTS_ID);
    }
    DBHelper dbHelper;
    SQLiteDatabase db;
    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //check uri
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS: //common uri
                //if sort isn't specified set own by name
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = CONTACT_NAME + " ASC";
                }
                break;
            case URI_CONTACTS_ID: //Uri with ID
                String id = uri.getLastPathSegment();
                //adding ID to condition of selection
                if(TextUtils.isEmpty(selection))
                    selection = CONTACT_ID + " = " + id;
                else
                    selection = selection + " AND " + CONTACT_ID + " = " +id;
                break;
            default:
                throw new IllegalArgumentException("Wrong Uri: "+uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(CONTACT_TABLE,projection,selection,selectionArgs,null,null,sortOrder);
        // ask to ContentResolver notify this cursor about data changes in CONTACT_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(),CONTACT_CONTENT_URI);
        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) != URI_CONTACTS)
            throw new IllegalArgumentException("Wrong URI: " + uri);
        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(CONTACT_TABLE,null,values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI,rowID);
        //notify ContentResolver resultUri data has changed
        getContext().getContentResolver().notifyChange(resultUri,null);
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection))
                    selection = CONTACT_ID + " = " + id;
                else
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: "+ uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(CONTACT_TABLE,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return cnt;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection))
                    selection = CONTACT_ID + " = " + id;
                else
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: "+ uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(CONTACT_TABLE,values,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return cnt;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                return CONTACT_CONTENT_TYPE;
            case URI_CONTACTS_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            ContentValues cv = new ContentValues();
            for (int i = 1; i < 3; i++) {
                cv.put(CONTACT_NAME,"name " + i);
                cv.put(CONTACT_EMAIL,"email " + i);
                db.insert(CONTACT_TABLE,null,cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
