package com.android.networkdemo.widgets.image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.view.View;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/13 14:58
 * 图片倒影效果实现
 */

public class ImageReflect {
    private static int reflectImageHeight = 80;

    /**
     * view界面转换成bitmap
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view){
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 将bitmap设置倒影
     * @param bitmap
     * @param reflectImageHeight
     * @return
     */
    public static Bitmap createReflectedImage(Bitmap bitmap,int reflectImageHeight){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if(height <= reflectImageHeight){
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.preScale(1,-1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap,0,
                height - reflectImageHeight,width,reflectImageHeight,matrix,true);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,reflectImageHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(reflectionImage,0,0,null);
        LinearGradient shader = new LinearGradient(0,0,0,bitmapWithReflection.getHeight(),
                0x70ffffff, 0x00ffffff, Shader.TileMode.MIRROR);

        Paint paint = new Paint();
        paint.setShader(shader);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0,0,width,bitmapWithReflection.getHeight(),paint);
        return bitmapWithReflection;

    }

    public static Bitmap createCutReflectedImage(Bitmap bitmap,int cutHeight){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int totalHeight = reflectImageHeight + cutHeight;

        if(height <= totalHeight){
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.preScale(1,-1);

        System.out.println(height - reflectImageHeight - cutHeight);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height
                        - reflectImageHeight - cutHeight, width, reflectImageHeight,
                matrix, true);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                reflectImageHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(reflectionImage, 0, 0, null);
        LinearGradient shader = new LinearGradient(0, 0, 0,
                bitmapWithReflection.getHeight()

                , 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);

        Paint paint = new Paint();
        paint.setShader(shader);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, 0, width, bitmapWithReflection.getHeight(), paint);
        if (!reflectionImage.isRecycled()) {
            reflectionImage.recycle();
        }
        // if (!bitmap.isRecycled()) {
        // bitmap.recycle();
        // }
        System.gc();
        return bitmapWithReflection;

    }




}
