package com.example.lesson136_cursorloader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by den on 2017-03-27.
 */

public class DB {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_TXT = "txt";

    private static final String DB_CREATE =
                    "create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_IMG + " integer, " +
                    COLUMN_TXT + " text" +
                    ");";
    private final Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;


    public DB(Context context){
        this.context = context;
    }


    //open connection
    public void open(){
        dbHelper = new DBHelper(context,DB_NAME,null,DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }


    //close connection
    public void close(){
        if(dbHelper != null)
            dbHelper.close();
    }


    //get all data from DB_TABLE
    public Cursor getAllData(){
        return db.query(DB_TABLE,null,null,null,null,null,null);
    }


    //add record to DB_TABLE
    public void addRec(String txt,int img){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TXT,txt);
        cv.put(COLUMN_IMG,img);
        db.insert(DB_TABLE,null,cv);
    }


    //del record from DB_TABLE
    public void delRec(long id){
        db.delete(DB_TABLE,COLUMN_ID + " = " + id,null);
    }

    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);

            ContentValues cv = new ContentValues();
            for (int i = 1; i < 3; i++) {
                cv.put(COLUMN_TXT,"sometext "+i);
                cv.put(COLUMN_IMG,R.mipmap.ic_launcher);
                db.insert(DB_TABLE,null,cv);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
