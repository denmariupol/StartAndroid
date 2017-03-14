package com.example.lesson144_drawing_matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View{
        Paint paint;
        Path path;
        Matrix matrix;
        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            paint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);

            path = new Path();
            matrix = new Matrix();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80,102,204,255);

            //создаем крест в path
            path.reset();
            path.addRect(300,150,450,200,Path.Direction.CW);
            path.addRect(350,100,400,250, Path.Direction.CW);

            //drawing path in green color
            paint.setColor(Color.GREEN);
            canvas.drawPath(path,paint);

            // настраиваем матрицу на перемещение на 300 вправо и 200 вниз
            matrix.reset();
//            matrix.setTranslate(300,200);
            // настраиваем матрицу на изменение размера:
            // в 2 раза по горизонтали
            // в 2,5 по вертикали
            // относительно точки (375, 100)
            matrix.setScale(2f,2.5f,375,100);
            matrix.setRotate(50);
            // применяем матрицу к path
            path.transform(matrix);

            paint.setColor(Color.BLUE);
            canvas.drawPath(path,paint);
        }
    }
}
