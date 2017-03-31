package com.example.lesson136_cursorloader_h.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by den on 2017-03-29.
 */

public class DB {

    private Context context;
    private final String DB_NAME = "db";
    private final int DB_VERSION = 1;

    public static final String DB_TABLE = "my";

    public static final String ID = "_id";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_IMAGE = "IMAGE";

    private final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +
                                        " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                COLUMN_TITLE + " TEXT," +
                                                COLUMN_IMAGE + " BLOB);";

    private DBHelper dbHlper;
    private SQLiteDatabase sqLiteDatabase;

    public DB(Context context) {
        this.context = context;

    }


    public void open(){
        dbHlper = new DBHelper(context, DB_NAME,null,DB_VERSION);
        sqLiteDatabase = dbHlper.getWritableDatabase();

    }


    public void close(){
        if(dbHlper != null)
            dbHlper.close();
    }


    public Cursor read(){
        return sqLiteDatabase.query(DB_TABLE,null,null,null,null,null,null);
    }


    public void add(String title,byte[] image){
        if(isUnique(sqLiteDatabase,title)) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TITLE, title);
            cv.put(COLUMN_IMAGE, image);
            sqLiteDatabase.insert(DB_TABLE, null, cv);
        }
    }

    public void drop(){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
    }
    public void delete(int position){
        sqLiteDatabase.delete(DB_TABLE,ID + " = " + position,null);
    }


    private static boolean isUnique(SQLiteDatabase db, String title) {
        Cursor cursor = db.query(DB.DB_TABLE, new String[]{COLUMN_TITLE}, "TITLE = ?", new String[]{title}, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }


    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            updateDB(db,0,DB_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        private void updateDB(SQLiteDatabase db,int oldVersion,int newVersion){
            if (oldVersion < 1){
                db.execSQL(CREATE_TABLE);
            }
        }
    }
}
