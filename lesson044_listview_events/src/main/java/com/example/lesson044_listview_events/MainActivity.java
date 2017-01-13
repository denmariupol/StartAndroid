package com.example.lesson044_listview_events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView)findViewById(R.id.lvMain);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /*
            parent – View-родитель для нажатого пункта, в нашем случае - ListView
            view – это нажатый пункт, в нашем случае – TextView из android.R.layout.simple_list_item_1
            position – порядковый номер пункта в списке
            id – идентификатор элемента,
         */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LOG","itemClick: position = " + position + ", id = "+ id);
            }
        });

        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //OnItemSelectedListener – обрабатывает выделение пунктов списка
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LOG","itemSelect: position = " + position + ", id = "+ id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LOG_TAG, "itemSelect: nothing");
            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            /*
                view – это прокручиваемый элемент, т.е. ListView
                scrollState – состояние списка. Может принимать три значения:

                SCROLL_STATE_IDLE = 0, список закончил прокрутку
                SCROLL_STATE_TOUCH_SCROLL = 1, список начал прокрутку
                SCROLL_STATE_FLING = 2, список «катнули», т.е. при прокрутке отпустили палец и прокрутка дальше идет «по инерции»
             */
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(LOG_TAG, "scrollState = " + scrollState);
            }
            /*
                view – прокручиваемый элемент
                firstVisibleItem – первый видимый на экране пункт списка
                visibleItemCount – сколько пунктов видно на экране
                totalItemCount – сколько всего пунктов в списке
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
//                        + ", visibleItemCount" + visibleItemCount
//                        + ", totalItemCount" + totalItemCount);
            }
        });
    }
}
