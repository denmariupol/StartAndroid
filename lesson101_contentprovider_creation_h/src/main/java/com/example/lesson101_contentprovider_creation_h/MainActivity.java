package com.example.lesson101_contentprovider_creation_h;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lesson101_contentprovider_creation_h.databinding.ActivityMainBinding;
import com.example.lesson101_contentprovider_creation_h.interfaces.IUpdateCursor;
import com.example.lesson101_contentprovider_creation_h.fragments.Insert;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements IUpdateCursor {
    public final static Uri PROVIDER_URI = Uri.parse("com.example.lesson101_contentprovider_creation_h");
    private Cursor cursor;
    private MyAdapter scAdapter;
    private ActivityMainBinding mainActivity;
    private OnClickListener clickListener;
    private int colId;
    private int colText;
    private ArrayList<Integer> chekedItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stethoInit();


        mainActivity = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            cursor = getContentResolver().query(MyProvider.CONTENT_URI, null, null, null, null, null);
        }

        colId = cursor.getColumnIndex(MyProvider.ID);
        colText = cursor.getColumnIndex(MyProvider.TEXT);

        String[] from = new String[]{MyProvider.ID, MyProvider.TEXT};
        int[] to = new int[]{R.id.itemId, R.id.itemText};

        scAdapter = new MyAdapter(this, R.layout.item, cursor, from, to, 0);

        mainActivity.listItem.setAdapter(scAdapter);
        scAdapter.notifyDataSetChanged();

        mainActivity.listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final View v = view;
                final LinearLayout itemUpdateLayout = (LinearLayout)view.findViewById(R.id.itemUpdateLayout);
                final LinearLayout itemViewLayout = (LinearLayout)view.findViewById(R.id.itemViewLayout);

                TextView idView = (TextView)view.findViewById(R.id.itemId);
                TextView textView = (TextView)view.findViewById(R.id.itemText);
                final EditText editText = (EditText)view.findViewById(R.id.updateTextField);
                Button updateButton = (Button)view.findViewById(R.id.updateRecButton);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Log.d("!!!","onTextChanged -> "+s.toString());
//                        ed
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                final String updId  = idView.getText().toString();
                updateButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues cv = new ContentValues();

                        String text = editText.getText().toString();

                        Log.d("!!!","clicked text -> "+editText.getText().toString());
                        if(text.length() > 0) {
                            cv.put(MyProvider.TEXT, text);
                            Uri newUri = ContentUris.withAppendedId(MyProvider.CONTENT_URI,Long.valueOf(updId));
                            getContentResolver().update(newUri, cv,null,null);

//                            Cursor cursor = null;
//                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                                cursor = getContentResolver().query(MyProvider.CONTENT_URI,null,null,null,null,null);
//                            }
//                            updateCursor(cursor);
                        }


                        itemUpdateLayout.setVisibility(View.GONE);
                        itemViewLayout.setVisibility(View.VISIBLE);
                    }
                });
                editText.setText(textView.getText());

                itemUpdateLayout.setVisibility(View.VISIBLE);
                itemViewLayout.setVisibility(View.INVISIBLE);


            }
        });

        mainActivity.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment insertFragment = new Insert();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, insertFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        mainActivity.delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chekedItems.size() > 0){
                    for (int i = 0; i < chekedItems.size(); i++) {
                        Uri newUri = ContentUris.withAppendedId(MyProvider.CONTENT_URI,chekedItems.get(i));
                        getContentResolver().delete(newUri,null,null);
//                        Cursor cursor = null;
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                            cursor = getContentResolver().query(MyProvider.CONTENT_URI,null,null,null,null,null);
//                        }
//                        updateCursor(cursor);
                    }
                    chekedItems.clear();
                }else{
                    Toast.makeText(MainActivity.this,"Nothing selected",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void stethoInit() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }


    @Override
    public void updateCursor(Cursor newCusor) {
        scAdapter.changeCursor(newCusor);
        scAdapter.notifyDataSetChanged();
        //hide keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private class MyAdapter extends SimpleCursorAdapter{
        private int layout;
        public MyAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            final String id = cursor.getString(colId);
            String title = cursor.getString(colText);
            TextView idView = (TextView)view.findViewById(R.id.itemId);
            idView.setText(id);
            TextView textView = (TextView)view.findViewById(R.id.itemText);
            textView.setText(title);
            CheckBox checkBox = (CheckBox)view.findViewById(R.id.itemCheckBox);
            checkBox.setChecked(false);

            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!chekedItems.contains(Integer.valueOf(id))){
                        chekedItems.add(Integer.valueOf(id));
                    }else{
                        chekedItems.remove(chekedItems.indexOf(Integer.valueOf(id)));
                    }
                }
            });
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = getLayoutInflater().inflate(layout,parent,false);
            return view;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return getCount();
        }


    }
}
