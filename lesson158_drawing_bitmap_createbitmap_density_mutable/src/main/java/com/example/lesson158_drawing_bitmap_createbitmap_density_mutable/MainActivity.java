package com.example.lesson158_drawing_bitmap_createbitmap_density_mutable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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
        Matrix matrix;
        Bitmap bitmapSource,bitmap;
        public DrawView(Context context) {
            super(context);
            paint = new Paint();

            bitmapSource = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

            matrix = new Matrix();

            matrix.postScale(30,40);
//            matrix.postRotate(45);

            bitmap = Bitmap.createBitmap(bitmapSource,0,0,bitmapSource.getWidth()/2,bitmapSource.getHeight()/2,matrix,true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80,102,204,255);
            canvas.drawBitmap(bitmap,500,500,paint);

        }
    }
}
