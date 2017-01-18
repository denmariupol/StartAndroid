package com.example.lesson046_expandablelist_events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.tvInfo);

        // создаем адаптер
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();

        elvMain = (ExpandableListView)findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        // нажатие на элемент
        /*
            Метод
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id), где

            parent – ExpandableListView с которым работаем
            v – View элемента
            groupPosition – позиция группы в списке
            childPosition – позиция элемента в группе
            id – id элемента

            Мы выводим в лог позицию и id. А в TextView сверху от списка выводим текст нажатого элемента и его группы,
            который получаем с помощью методов AdapterHelper.

            Метод должен вернуть boolean. Если мы возвращаем true – это значит, мы сообщаем, что сами полностью обработали
            событие и оно не пойдет в дальнейшие обработчики (если они есть).Если возвращаем false – значит, мы позволяем событию идти дальше
         */
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(LOG_TAG, "onChildClick groupPosition = " + groupPosition +
                        " childPosition = " + childPosition +
                        " id = " + id);
                tvInfo.setText(ah.getGroupChildText(groupPosition,childPosition));
                return  false;
            }
        });

        // нажатие на группу
        /*
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id), где

            parent – ExpandableListView с которым работаем
            v – View элемента
            groupPosition – позиция группы в списке
            id – id группы

            Мы выводим в лог позицию и id группы.

            Этот метод также должен вернуть boolean. Мы будет возвращать true, если позиция группы = 1, иначе - false.
            Т.е. для этой группы мы блокируем дальнейшую обработку события. Далее увидим, что нам это даст.
         */
        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Log.d(LOG_TAG, "onGroupClick groupPosition = " + groupPosition +
                        " id = " + id);
                // блокируем дальнейшую обработку события для группы с позицией 1
                if (groupPosition == 1) return true;

                return false;
            }
        });

        // сворачивание группы onGroupCollapse(int groupPosition), где groupPosition – позиция группы, которую свернули
        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            public void onGroupCollapse(int groupPosition) {
                Log.d(LOG_TAG, "onGroupCollapse groupPosition = " + groupPosition);
                tvInfo.setText("Свернули " + ah.getGroupText(groupPosition));
            }
        });

        // разворачивание группы onGroupExpand(int groupPosition), где groupPosition – позиция группы, которую развернули
        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
                Log.d(LOG_TAG, "onGroupExpand groupPosition = " + groupPosition);
                tvInfo.setText("Развернули " + ah.getGroupText(groupPosition));
            }
        });

        // разворачиваем группу с позицией 2
        elvMain.expandGroup(2);
    }
}
