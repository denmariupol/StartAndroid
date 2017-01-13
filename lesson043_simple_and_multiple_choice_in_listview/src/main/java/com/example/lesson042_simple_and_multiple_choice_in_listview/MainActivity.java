package com.example.lesson042_simple_and_multiple_choice_in_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  implements OnClickListener {
    final String LOG_TAG = "myLogs";

    ListView lvMain;
    String[] names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView)findViewById(R.id.lvMain);

        // устанавливаем режим выбора пунктов списка
//        lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        // Создаем адаптер, используя массив из файла ресурсов
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.names,
//                android.R.layout.simple_list_item_single_choice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names,
                android.R.layout.simple_list_item_multiple_choice);


        lvMain.setAdapter(adapter);

        Button btnChecked = (Button)findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);

        // получаем массив из файла ресурсов
        names = getResources().getStringArray(R.array.names);
    }

    @Override
    public void onClick(View v) {
//        Log.d(LOG_TAG, "checked: " + names[lvMain.getCheckedItemPosition()]);
        Log.d(LOG_TAG, "checked: ");
        SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();
        for (int i = 0; i < sbArray.size(); i++) {
            int key = sbArray.keyAt(i);
            if (sbArray.get(key))
                Log.d(LOG_TAG, names[key]);
        }
    }
}
