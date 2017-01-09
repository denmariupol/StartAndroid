package com.example.lesson034_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by den on 2017-01-09.
 */

class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    public DBHelper(Context context){
        // конструктор суперкласса
        // сontext - контекст
        // mydb - название базы данных
        // null – объект для работы с курсорами, нам пока не нужен, поэтому null
        // 1 – версия базы данных
        super(context,"myDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG,"---onCreate database");
        //создаем таблицу с полями.
        // Выполнение SQL-запроса, который создает таблицу.
        // Напомню – этот метод вызывается, если БД не существует и ее надо создавать.
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "email text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
