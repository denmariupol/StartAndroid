package com.example.lesson037_queryfromlinkeddb_innerjoin_rawquery;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by den on 2017-01-11.
 */

public class DBHelper extends SQLiteOpenHelper {
    final String LOG = "LOG";

    int[] position_id = { 1, 2, 3, 4 };
    String[] position_name = { "Директор", "Программер", "Бухгалтер", "Охранник" };
    int[] position_salary = { 15000, 13000, 10000, 8000 };

    // данные для таблицы людей
    String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };


    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG, "--- onCreate database ---");

        ContentValues cv = new ContentValues();

        // создаем таблицу должностей
        db.execSQL("create table position ("
                + "id integer primary key,"
                + "name text," + "salary integer"
                + ");");

        // заполняем ее
        for (int i = 0; i < position_id.length; i++) {
            cv.clear();
            cv.put("id", position_id[i]);
            cv.put("name", position_name[i]);
            cv.put("salary", position_salary[i]);
            db.insert("position", null, cv);
        }

        // создаем таблицу людей
        db.execSQL("create table people ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "posid integer"
                + ");");

        // заполняем ее
        for (int i = 0; i < people_name.length; i++) {
            cv.clear();
            cv.put("name", people_name[i]);
            cv.put("posid", people_posid[i]);
            db.insert("people", null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
