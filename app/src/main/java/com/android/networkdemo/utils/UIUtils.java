package com.android.networkdemo.utils;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/19 19:31
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


public class UIUtils {

    private static Context context = null;
    private static DisplayMetrics dm = null;
    private static boolean followSystemBackground = true;
    private static float ratioX;
    private static float ratioY;

    public static DisplayMetrics getDM() {
        return dm;
    }

    public static float getDensity() {
        return dm.density;
    }

    public static void initDisplayMetrics(Context ctx, WindowManager wm,
                                          boolean isFollowSystemBackground) {
        if(dm == null){
            if (context == null) {
                context = ctx;
            }

            dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);

            followSystemBackground = isFollowSystemBackground;

            int height = 0;
            if (getHeight() > 650 && getHeight() < 721) {
                height = 720;
            } else {
                if (getHeight() > 1000 && getHeight() < 1080) {
                    height = 1080;
                } else {
                    height = getHeight();
                }
            }
            ratioX = getWidth() / 1920f;
            ratioY = height / 1080f;
        }
    }

    public static int getTextSize() {
        return dm.heightPixels * 12 / 720;
    }

    public static int getTimeTextSize() {
        return dm.heightPixels * 24 / 720;
    }

    public static int getTitleTextSize() {
        return dm.heightPixels * 36 / 720;
    }

    public static int getTextSize(int size) {
        return dm.heightPixels * size / 720;
    }

    public static boolean touchInDialog(Activity activity, MotionEvent e) {
        int leftW, rightW, topH, bottomH;
        leftW = 8;
        rightW = dm.widthPixels - leftW;
        topH = 0;
        bottomH = 450;
        return ((e.getX() > leftW) && (e.getX() < rightW) && (e.getY() > topH) && (e.getY() < bottomH));
    }

    public static boolean isScreenCenter(MotionEvent e) {
        boolean ret = true;
        if (e.getX() < (dm.widthPixels / 2 - 25)) {
            ret = false;
        }
        if (e.getX() > (dm.widthPixels / 2 + 25)) {
            ret = false;
        }
        if (e.getY() < (dm.heightPixels / 2 - 25)) {
            ret = false;
        }
        if (e.getY() > (dm.heightPixels / 2 + 25)) {
            ret = false;
        }
        return ret;
    }

    public static PointF getLeftBottomPoint() {
        return new PointF((dm.widthPixels / 4) + 0.09f,
                (dm.heightPixels / 4 * 3) + 0.09f);
    }

    public static PointF getRightBottomPoint() {
        return new PointF((dm.widthPixels / 4 * 3) + 0.09f,
                (dm.heightPixels / 4 * 3) + 0.09f);
    }

    public static PointF getLeftPoint() {
        return new PointF(20, dm.heightPixels / 2);
    }

    public static PointF getRightPoint() {
        return new PointF(dm.widthPixels - 20, dm.heightPixels / 2);
    }

    public static boolean isTouchLeft(MotionEvent e) {
        return (e.getX() < (dm.widthPixels / 2));
    }

    public static int getStatusbarHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (statusHeight == 0) {
            try {
                Class<?> localClass = Class
                        .forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass
                        .getField("status_bar_height").get(localObject)
                        .toString());
                statusHeight = activity.getResources()
                        .getDimensionPixelSize(i5);
            } catch (Exception e) {

            }
        }
        return statusHeight;
    }

    public static void setActivitySizePos(Activity activity, int x, int y,
                                          int width, int height) {
        WindowManager.LayoutParams p = activity.getWindow().getAttributes();
        p.x = x;
        p.y = y;
        p.width = width;
        p.height = height;
        activity.getWindow().setAttributes(p);
    }

    public static int dipToPx(int dip) {
        if (dm == null) {
            return -1;
        }
        return (int) (dip * dm.density + 0.5f);
    }

    public static int dipToPx(float f) {
        if (dm == null) {
            return -1;
        }
        return (int) (f * dm.density + 0.5f);
    }

    public static float pxToScaledPx(int px) {
        if (dm == null) {
            return -1;
        }
        return px / dm.density;
    }

    public static int scaledPxToPx(float scaledPx) {
        if (dm == null) {
            return -1;
        }
        return (int) (scaledPx * dm.density);
    }

    public static int countViewAdvWidth(int count, int innerMargin,
                                        int outerMargin) {
        int width = dm.widthPixels - (outerMargin * 2);
        width = width - (innerMargin * (count - 1));
        width = width / count;
        return width;
    }

    public static int countViewAdvWidthByFrame(int count, int innerMargin,
                                               int outerMargin, int frameWidth) {
        int width = frameWidth - (outerMargin * 2);
        width = width - (innerMargin * (count - 1));
        width = width / count;
        return width;
    }

    public static int countViewAdvHeight(int count, int innerMargin,
                                         int outerMargin) {
        int height = dm.heightPixels - (outerMargin * 2);
        height = height - (innerMargin * (count - 1));
        height = height / count;
        return height;
    }

    public static int countViewAdvHeightByFrame(int count, int innerMargin,
                                                int outerMargin, int frameHeight) {
        int height = frameHeight - (outerMargin * 2);
        height = height - (innerMargin * (count - 1));
        height = height / count;
        return height;
    }

    public static int getWidth() {
        if (dm != null) {
            return dm.widthPixels;
        } else {
            return 0;
        }
    }

    public static int getHeight() {
        if (dm != null) {
            if (dm.heightPixels >= 672 && dm.heightPixels <= 696) {
                return dm.heightPixels;
            } else if (dm.heightPixels >= 696 && dm.heightPixels <= 720) {
                dm.heightPixels = dm.heightPixels * 672 / 720;
                return dm.heightPixels;
            } else if (dm.heightPixels >= 1044 && dm.heightPixels <= 1080) {
                // return dm.heightPixels * 672 / 720;
                return 1080;
            } else if (dm.heightPixels >= 1000 && dm.heightPixels <= 1080) {
                return 1080;
            }
            return dm.heightPixels;
        } else {
            return 0;
        }
    }

    public static float getRatioX() {
        return ratioX;
    }

    public static float getRatioY() {
        return ratioY;
    }

    public static void setActivitySizeX(Activity a, int size) {
        WindowManager.LayoutParams lp = a.getWindow().getAttributes();
        lp.width = size;
        a.getWindow().setAttributes(lp);
    }

    public static void setActivitySizeY(Activity a, int size) {
        WindowManager.LayoutParams lp = a.getWindow().getAttributes();
        lp.height = size;
        a.getWindow().setAttributes(lp);
    }

    public static void setActivityPercentX(Activity a, float percent) {
        WindowManager.LayoutParams lp = a.getWindow().getAttributes();
        lp.width = (int) (getWidth() * percent / 100);
        a.getWindow().setAttributes(lp);
    }

    public static void setActivityPercentY(Activity a, float percent) {
        WindowManager.LayoutParams lp = a.getWindow().getAttributes();
        lp.height = (int) (getHeight() * percent / 100);
        a.getWindow().setAttributes(lp);
    }

    public static void setViewSizeX(View v, int size) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = size;
        v.setLayoutParams(lp);
    }

    public static void setViewSizeY(View v, int size) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = size;
        v.setLayoutParams(lp);
    }

    public static void setViewSizeXY(View v, int width,int height) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = width;
        lp.height = height;
        v.setLayoutParams(lp);
    }

    public static void setViewPercentX(View v, float percent) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = (int) (getWidth() * percent / 100);
        v.setLayoutParams(lp);
    }

    public static void setViewPercentY(View v, float percent) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = (int) (getHeight() * percent / 100);
        v.setLayoutParams(lp);
    }

    public static void setViewPercentXByFrame(View v, float percent,
                                              float frameXPercent) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = (int) ((getWidth() * frameXPercent / 100) * percent / 100);
        v.setLayoutParams(lp);
    }

    public static void setViewPercentYByFrame(View v, float percent,
                                              float frameYPercent) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = (int) ((getHeight() * frameYPercent / 100) * percent / 100);
        v.setLayoutParams(lp);
    }

    public static void setViewMarginPercent(View v, float marginLeft,
                                            float marginTop, float marginRight, float marginBottom)
            throws Exception {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.leftMargin = (int) (getWidth() * marginLeft / 100);
            mlp.topMargin = (int) (getHeight() * marginTop / 100);
            mlp.rightMargin = (int) (getWidth() * marginRight / 100);
            mlp.bottomMargin = (int) (getHeight() * marginBottom / 100);
            v.setLayoutParams(mlp);
        } else {
        }

    }

    public static void setViewMarginSize(View v, int marginLeft, int marginTop,
                                         int marginRight, int marginBottom) throws Exception {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.leftMargin = marginLeft;
            mlp.topMargin = marginTop;
            mlp.rightMargin = marginRight;
            mlp.bottomMargin = marginBottom;
            v.setLayoutParams(mlp);
        } else {
        }
    }

    public static void setViewPaddingPercent(View v, float paddingLeft,
                                             float paddingTop, float paddingRight, float paddingBottom) {
        int pLeft = (int) (getWidth() * paddingLeft / 100);
        int pTop = (int) (getHeight() * paddingTop / 100);
        int pRight = (int) (getWidth() * paddingRight / 100);
        int pBottom = (int) (getHeight() * paddingBottom / 100);
        v.setPadding(pLeft, pTop, pRight, pBottom);
    }

    public static void makeListViewFullSize(ListView lv, int itemHeight) {
        int itemCount = lv.getAdapter().getCount();
        int divider = lv.getDividerHeight();
        int height = (itemHeight + divider) * itemCount;
        ViewGroup.LayoutParams lp = lv.getLayoutParams();
        lp.height = height;
        lv.setLayoutParams(lp);
    }

    public static void makeGridViewFullSize(GridView gv, int itemHeight,
                                            int rowNum) {
        int itemCount = gv.getAdapter().getCount();
        int lines = (int) (itemCount / rowNum);
        if (itemCount % rowNum != 0) {
            lines++;
        }
        ViewGroup.LayoutParams lp = gv.getLayoutParams();
        lp.height = lines * itemHeight;
        gv.setLayoutParams(lp);

    }

    public static boolean isFollowSystemBackground() {
        return followSystemBackground;
    }

    public static void setFollowSystemBackground(
            boolean isFollowSystemBackground) {
        followSystemBackground = isFollowSystemBackground;
    }

    /**
     *
     * @param originalBitmap
     *            Bitmap对象
     * @param times
     *            倒影比率
     * @param isSpecial
     *            特殊处理的图片
     * @return
     */
    public static Bitmap getReflectBitmap(Bitmap originalBitmap, double times,
                                          boolean isSpecial) {
        // 图片与倒影间隔距离
        final int reflectionGap = 0;

        // 图片的宽度
        int width = originalBitmap.getWidth();
        // 图片的高度
        int height = originalBitmap.getHeight();

        Matrix matrix = new Matrix();
        // 图片缩放，x轴变为原来的1倍，y轴为-1倍,实现图片的反转
        matrix.preScale(1, -1);
        // 创建反转后的图片Bitmap对象，图片高是原图的一半。
        Bitmap reflectionBitmap = null;
        if (isSpecial) {
            reflectionBitmap = Bitmap.createBitmap(originalBitmap, 0,
                    height / 5 * 1, width, height / 5 * 4, matrix, false);
        } else {
            reflectionBitmap = Bitmap.createBitmap(originalBitmap, 0,
                    height / 2, width, height / 2, matrix, false);
        }

        // 创建标准的Bitmap对象，宽和原图一致，高是原图的1.5倍。
        Bitmap withReflectionBitmap = null;
        withReflectionBitmap = Bitmap.createBitmap(width, (int) (height + height / times + reflectionGap), Bitmap.Config.ARGB_8888);

        // 构造函数传入Bitmap对象，为了在图片上画图
        Canvas canvas = new Canvas(withReflectionBitmap);

        // 画原始图片
        canvas.drawBitmap(originalBitmap, 0, 0, null);

        // 画间隔矩形
        Paint defaultPaint = new Paint();

        // canvas.drawRect(0, height, width, height + reflectionGap,
        // defaultPaint);

        // 画倒影图片

        canvas.drawBitmap(reflectionBitmap, 0, height + reflectionGap, null);

        // 实现倒影效果
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0,
                originalBitmap.getHeight(), 0,
                withReflectionBitmap.getHeight(), 0x70ffffff, 0x00ffffff,
                Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        // 覆盖效果
        canvas.drawRect(0, height, width, withReflectionBitmap.getHeight(),paint);

        return withReflectionBitmap;
    }

    public static void setTextSize(TextView view, float size){
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * GlobalConsts.Rx);
    }

    public static int getNavigationBarHeight(Context context) {
        try {
            int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
            int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
//				LogUtil.e("super_hw","nav_bar is show ? " + context.getResources().getBoolean(rid) +""); //获取导航栏是否显示true or false
//				LogUtil.e("super_hw","nav_bar.h:" + context.getResources().getDimensionPixelSize(resourceId) +""); //获取高度
                if (context.getResources().getBoolean(rid)) {
                    int navbarHeight = context.getResources().getDimensionPixelSize(resourceId);
                    if (navbarHeight > 0)

                    return navbarHeight;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
