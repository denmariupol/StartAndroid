package com.example.lesson033_preferences_storedata;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    Button btnSave,btnLoad;
    SharedPreferences preferences;
    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.etText);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnLoad = (Button)findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        load();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoad:
                load();
                break;
            case R.id.btnSave:
                save();
                break;
        }
    }

    private void save(){
        //константа MODE_PRIVATE используется для настройки доступа и
        // означает, что после сохранения, данные будут видны только этому приложению
        preferences = getPreferences(MODE_PRIVATE);
        Editor editor = preferences.edit();//чтобы редактировать данные, необходим объект Editor
        editor.putString(SAVED_TEXT,editText.getText().toString());
        editor.commit();// Чтобы данные сохранились
        Toast.makeText(this,"Text Saved",Toast.LENGTH_SHORT).show();
    }

    private void load(){
        preferences = getPreferences(MODE_PRIVATE);
        String savedText = preferences.getString(SAVED_TEXT,"");
        editText.setText(savedText);
        Toast.makeText(this,"Text Loaded",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onDestroy();
        save();
    }
}
