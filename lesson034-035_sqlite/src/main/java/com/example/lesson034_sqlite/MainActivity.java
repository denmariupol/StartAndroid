package com.example.lesson034_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final String LOG_TAG = "LOG";

    Button btnAdd,btnRead,btnClear,btnUpd,btnDel;
    EditText editName,editEmail,editID;
    TextView showDB;
    DBHelper dbHelper;
    StringBuilder sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnRead = (Button)findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnUpd = (Button)findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(this);

        btnDel = (Button)findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        editID = (EditText)findViewById(R.id.etID);
        editEmail = (EditText)findViewById(R.id.etEmail);
        editName = (EditText)findViewById(R.id.etName);

        showDB = (TextView)findViewById(R.id.textView);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        // создаем объект для данных ,используется для указания полей
        // таблицы и значений, которые мы в эти поля будем вставлять
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String id = editID.getText().toString();
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (v.getId()){
            case R.id.btnAdd:
                Log.d(LOG_TAG,"---Insert in mytable:---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение
                cv.put("name",name);
                cv.put("email",email);
                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytable",null,cv);
                Log.d(LOG_TAG,"row inserted, ID = "+rowID);
                break;

            case R.id.btnRead:
                Log.d(LOG_TAG, "--- Rows in mytable: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor ,
                // подается на вход имя таблицы, список запрашиваемых полей,
                // условия выборки, группировка, сортировка. Т.к. нам нужны все данные
                // во всех полях без сортировок и группировок - мы используем везде null.
                Cursor c = db.query("mytable", null, null, null, null, null, null);
                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                //moveToFirst – делает первую запись в Cursor активной и заодно проверяет,
                // есть ли вообще записи в нем (т.е. выбралось ли что-либо в методе query)
                if(c.moveToFirst()){
                    sb = new StringBuilder();
                    // определяем номера столбцов по имени в выборке
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");
                    do{
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                "ID = " + c.getInt(idColIndex) +
                                        ", name = " + c.getString(nameColIndex) +
                                        ", email = " + c.getString(emailColIndex));
                        sb.append("ID = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", email = " + c.getString(emailColIndex)+"\n");
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    }while (c.moveToNext());
                    showDB.setText(sb.toString());
                }else
                    Log.d(LOG_TAG,"---Create mytable: ---");
                //закрываем курсор (освобождаем занимаемые им ресурсы)
                c.close();
                break;

            case R.id.btnClear:
                Log.d(LOG_TAG, "--- Clear mytable: ---");
                // На вход передаем имя таблицы и null в качестве условий для удаления,
                // а значит удалится все. Метод возвращает кол-во удаленных записей
                int clearCount = db.delete("mytable", null, null);
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                showDB.setText("");
                break;

            case R.id.btnUpd:
                if(id.equals("")) break;
                Log.d(LOG_TAG,"--- Update mytable: ---");
                // подготовим значения для обновления
                cv.put("name", name);
                cv.put("email", email);
                // обновляем по id На вход ему подается имя таблицы, заполненный cv
                // с значениями для обновления, строка условия (Where) и массив аргументов для строки условия
                // В строке условия я использовал знак ?. При запросе к БД вместо этого знака
                // будет подставлено значение из массива аргументов, в нашем случае это – значение переменной id.
                // Если знаков ? в строке условия несколько, то им будут сопоставлены значения из массива по порядку.
                int updCount = db.update("mytable", cv, "id = ?",
                        new String[] { id });
                Log.d(LOG_TAG, "updated rows count = " + updCount);
                break;

            case R.id.btnDel:
                if (id.equals("")) break;
                Log.d(LOG_TAG, "--- Delete from mytable: ---");
                // удаляем по id На вход передаем имя таблицы, строку условия и массив
                // аргументов для условия. Метод delete возвращает кол-во удаленных строк,
                // которое мы выводим в лог.
                int delCount = db.delete("mytable", "id = " + id, null);
                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                break;
        }
        // закрываем подключение к БД
        dbHelper.close();
    }
}
