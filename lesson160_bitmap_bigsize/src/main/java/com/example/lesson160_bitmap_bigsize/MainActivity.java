package com.example.lesson160_bitmap_bigsize;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        logMemory();
        readMemory();
        readImage();
        logMemory();
    }

    private void readMemory(){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"map.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Log.d("LOG",String.format("bitmap size = %sx%s, byteCount = %s",
                bitmap.getWidth(), bitmap.getHeight(),
                bitmap.getByteCount()));
        imageView.setImageBitmap(bitmap);
    }

    private void readImage(){
        int px = getResources().getDimensionPixelOffset(R.dimen.size);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"map.jpg");
        Bitmap bitmap = decodeSampledBitmapFromResource(file.getAbsolutePath(),px,px);
        Log.d("log", String.format("Required size = %s, bitmap size = %sx%s, byteCount = %s",
                px, bitmap.getWidth(), bitmap.getHeight(), bitmap.getByteCount()));
        imageView.setImageBitmap(bitmap);
    }
    private void logMemory(){
        Log.i("log", String.format("Total memory = %s",
                (int) (Runtime.getRuntime().totalMemory() / 1024)));
    }


    public static Bitmap decodeSampledBitmapFromResource(String path,int reqWidth,int reqHeight){
        // Читаем с inJustDecodeBounds=true для определения размеров
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeFile(path,options);
        // Вычисляем inSampleSize
        options.inSampleSize = calculatedSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path,options);

    }
    public static int calculatedSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        // Реальные размеры изображения
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth){
            final int halfHeight = height /2;
            final int halfWidth = width / 2;

            // Вычисляем наибольший inSampleSize, который будет кратным двум
            // и оставит полученные размеры больше, чем требуемые
            while((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
