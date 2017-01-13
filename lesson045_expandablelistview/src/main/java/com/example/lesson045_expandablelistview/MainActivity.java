package com.example.lesson045_expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // названия компаний (групп)
    String[] groups = new String[]{"HTC", "Samsung", "LG"};

    // названия телефонов (элементов)
    String[] phonesHTC = new String[]{"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSams = new String[]{"Galaxy S II", "Galaxy Nexus", "Wave"};
    String[] phonesLG = new String[]{"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

    // коллекция для групп
    ArrayList<Map<String, String>> groupData;

    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    // в итоге получится childData = ArrayList<childDataItem>

    // список атрибутов группы или элемента
    Map<String, String> m;

    ExpandableListView elvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // заполняем коллекцию групп из массива с названиями групп,
        // заглавия открывающегося меню "HTC", "Samsung", "LG"
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            // заполняем список атрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put("groupName", group);//имя компании  пример."groupName" ,"HTC"
            groupData.add(m);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[]{"groupName"};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[]{android.R.id.text1};


        // создаем коллекцию для коллекций элементов "HTC"->"Sensation", "Desire", "Wildfire", "Hero"
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем список атрибутов для каждого элемента
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone); // название телефона  пример."phoneName" "Sensation"
            childDataItem.add(m);
        }
        // добавляем в коллекцию коллекций
        childData.add(childDataItem);

        // создаем коллекцию элементов для второй группы
//        childDataItem = new ArrayList<Map<String, String>>();
//        for (String phone : phonesSams) {
//            m = new HashMap<String, String>();
//            m.put("phoneName", phone);
//            childDataItem.add(m);
//        }
//        childData.add(childDataItem);

        // создаем коллекцию элементов для третьей группы
//        childDataItem = new ArrayList<Map<String, String>>();
//        for (String phone : phonesLG) {
//            m = new HashMap<String, String>();
//            m.put("phoneName", phone);
//            childDataItem.add(m);
//        }
//        childData.add(childDataItem);

        // список атрибутов элементов для чтения
        String childFrom[] = new String[]{"phoneName"};
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        int childTo[] = new int[]{android.R.id.text1};

        /*
            this – контекст
            groupData – коллекция групп
            android.R.layout.simple_expandable_list_item_1 – layout-ресурс, который будет использован для отображения группы в списке.
                                                             Соответственно, запросто можно использовать свой layout-файл.
            groupFrom – массив имен атрибутов групп
            groupTo – массив ID TextView из layout для групп
            childData – коллекция коллекций элементов по группам
            android.R.layout.simple_list_item_1 - layout-ресурс, который будет использован для отображения элемента в списке.
                                                  Можно использовать свой layout-файл
            childFrom – массив имен атрибутов элементов
            childTo - массив ID TextView из layout для элементов.
         */
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                R.layout.support_simple_spinner_dropdown_item,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);
    }
}


