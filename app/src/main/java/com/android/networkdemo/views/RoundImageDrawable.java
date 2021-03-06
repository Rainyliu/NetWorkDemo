package com.android.networkdemo.views;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 实现圆角图片
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/12 15:15
 */

public class RoundImageDrawable extends Drawable {
    private Paint mPaint;
    private Bitmap mBitmap;

    private RectF rectF;

    public RoundImageDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(
                bitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
    }


    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left, top, right, bottom);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(rectF,30,30,mPaint);
    }


    /**
     * 主要是为了在View使用wrap_content的时候，提供一下尺寸，
     * 默认为-1可不是我们希望的。setBounds就是去设置下绘制的范围。
     * @return
     */
    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    /**
     * 主要是为了在View使用wrap_content的时候，提供一下尺寸，
     * 默认为-1可不是我们希望的。setBounds就是去设置下绘制的范围。
     * @return
     */
    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
