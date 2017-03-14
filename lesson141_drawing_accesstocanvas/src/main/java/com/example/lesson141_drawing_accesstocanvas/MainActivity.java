package com.example.lesson141_drawing_accesstocanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }


    private class DrawView extends View{


        public DrawView(Context context) {
            super(context);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GREEN);
        }
    }
}
