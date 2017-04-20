package com.android.networkdemo.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.networkdemo.R;
import com.android.networkdemo.widgets.image.ImageUtils;

import org.evilbinary.tv.widget.BorderView;

public class ReflectionImageActivity extends AppCompatActivity {
    private ImageView iv1,iv2;
    private LinearLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection_image);
        BorderView border = new BorderView(this);
        border.setBackgroundResource(R.drawable.home_top_right);
        iv1 = (ImageView) findViewById(R.id.picture_qian);
        iv2 = (ImageView) findViewById(R.id.picture_qian1);
        main = (LinearLayout) findViewById(R.id.main);
        Bitmap bmp1 = ((BitmapDrawable) getResources().getDrawable(
                R.mipmap.qian)).getBitmap();

        Bitmap bmp2 = ((BitmapDrawable) getResources().getDrawable(
                R.mipmap.b)).getBitmap();

        iv1.setImageBitmap(createReflectedImage(bmp1));
//        iv1.setImageBitmap(ImageUtils.drawImageDropShadow(bmp1,this));
        iv2.setImageBitmap(createReflectedImage(bmp2));
        border.attachTo(main);
    }


    /**
     * 创建倒影图片
     * @param originalImage
     * @return
     */
    private Bitmap createReflectedImage(Bitmap originalImage) {
        final int reflectionGap = 4;

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
                height / 2, width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);

        canvas.drawBitmap(originalImage, 0, 0, null);

        Paint defaultPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0,
                originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
                + reflectionGap, 0x70ffffff, 0x00ffffff,
                Shader.TileMode.MIRROR);

        paint.setShader(shader);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }
}
