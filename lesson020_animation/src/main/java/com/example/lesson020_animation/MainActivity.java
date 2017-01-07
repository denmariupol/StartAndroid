package com.example.lesson020_animation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // константы для ID пунктов меню
    final int MENU_ALPHA_ID = 1;
    final int MENU_SCALE_ID = 2;
    final int MENU_TRANSLATE_ID = 3;
    final int MENU_ROTATE_ID = 4;
    final int MENU_COMBO_ID = 5;

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv =(TextView)findViewById(R.id.tv);
        // регистрируем контекстное меню для компонента tv
        registerForContextMenu(tv);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        switch (v.getId()){
            case R.id.tv:
                menu.add(0,MENU_ALPHA_ID,0,"alpha");
                menu.add(0,MENU_SCALE_ID,0,"scale");
                menu.add(0,MENU_TRANSLATE_ID,0,"translate");
                menu.add(0,MENU_ROTATE_ID,0,"rotate");
                menu.add(0,MENU_COMBO_ID,0,"combo");

        }
        super.onCreateContextMenu(menu,v,menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        Animation animation = null;
        // определяем какой пункт был нажат
        switch (item.getItemId()){
            case MENU_ALPHA_ID:
                animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
                break;
            case  MENU_SCALE_ID:
                animation = AnimationUtils.loadAnimation(this,R.anim.scale);
                break;
            case MENU_TRANSLATE_ID:
                animation = AnimationUtils.loadAnimation(this,R.anim.trans);
                break;
            case MENU_ROTATE_ID:
                animation = AnimationUtils.loadAnimation(this,R.anim.rotate);
                break;
            case MENU_COMBO_ID:
                animation = AnimationUtils.loadAnimation(this,R.anim.rotate_scale);
                break;
        }
        // запускаем анимацию для компонента tv
        tv.startAnimation(animation);
        return super.onContextItemSelected(item);
    }
}
