package com.android.networkdemo.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.networkdemo.utils.GlobalConsts;

/**
 * 自定义Drawable
 * 然后把图片画到画布上
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/12 11:13
 */

public class CustomBitmapDrawable extends Drawable{

    private Paint mPaint;
    private Bitmap mBitmap;
    private Rect srcRect;

    public CustomBitmapDrawable(Bitmap mBitmap){
        this.mBitmap = mBitmap;
        mPaint = new Paint();
        srcRect = new Rect(0,0, (int)(GlobalConsts.Rx*GlobalConsts.screenWidth),(int)(GlobalConsts.Ry*GlobalConsts.screenHeight));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
//        canvas.drawBitmap(mBitmap,0,0,mPaint);
        /**
         * 四个参数的意思：
         * mBitmap 要绘制的图片
         * srcRect 图片的坐标
         * srcRect 需要绘制的图片所在位置的坐标
         * mPaint 画笔（处理时一般为null）
         */
        canvas.drawBitmap(mBitmap,srcRect,srcRect,null);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    /**
     * 返回值
     * UNKNOWN,     0    these constants need to match those in hardware/hardware.h
     * TRANSLUCENT, -3   System chooses a format that supports translucency (many alpha bits)
     * TRANSPARENT, -2   System chooses a format that supports transparency   (at least 1 alpha bit)
     * OPAQUE       -1
     * @return
     */
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
