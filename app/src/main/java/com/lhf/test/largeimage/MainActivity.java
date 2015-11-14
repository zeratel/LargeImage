package com.lhf.test.largeimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = (ImageView)findViewById(R.id.iv);


        //转换效率太低太慢
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.test,options);
        int w = options.outWidth;
        int h = options.outHeight;

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        InputStream isBm = new ByteArrayInputStream(baos.toByteArray());


        try {

            //效率高很多
            InputStream inputStream = getAssets().open("test.jpg");
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(w / 2-300, h / 2-300, w / 2 + 300, h / 2 + 300), null);
            iv.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }


    }
}
