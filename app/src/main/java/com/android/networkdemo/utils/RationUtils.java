package com.android.networkdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 计算比率的工具类
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/10 16:31
 */

public class RationUtils {
    private static DisplayMetrics metrics = null;
    private static WindowManager wm = null;
    /**
     * 初始化全局变量
     */
    public static void initGlobalValues(Context mContext) {
        /**
         * 设计图的宽高
         */
        GlobalConsts.screenWidth = DeviceUtils.getMetaIntValue(mContext, "designWidth");
        GlobalConsts.screenHeight = DeviceUtils.getMetaIntValue(mContext, "designHeight");
        metrics = DeviceUtils.getScreenPix(mContext);
//        wm.getDefaultDisplay().getMetrics(metrics);


        /**
         * 屏幕缩放比例
         */
        GlobalConsts.Rx = 1.0f*metrics.widthPixels/GlobalConsts.screenWidth;
        GlobalConsts.Ry = 1.0f*metrics.heightPixels/GlobalConsts.screenHeight;
        Log.d("liuyuting", "initGlobalValues:  screenWidth=="+GlobalConsts.screenWidth);
        Log.d("liuyuting", "initGlobalValues:  screenHeigh=="+GlobalConsts.screenHeight);
        Log.d("liuyuting", "initGlobalValues:  Rx=="+GlobalConsts.Rx);
        Log.d("liuyuting", "initGlobalValues:  Ry=="+GlobalConsts.Ry);
    }

    /**
     * 获取X比率
     * @return
     */
    public static float getRationX(){
        return GlobalConsts.Rx;
    }

    /**
     * 获取Y比率
     * @return
     */
    public static float getRationY(){
        return GlobalConsts.Ry;
    }

    /**
     * 设置view的宽
     * @param v
     * @param size
     */
    public static void setViewSizeX(View v, int size) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = (int)(size * GlobalConsts.Rx);
        v.setLayoutParams(lp);
    }

    /**
     * 设置view的高
     * @param v
     * @param size
     */
    public static void setViewSizeY(View v, int size) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = (int)(size * GlobalConsts.Ry);
        v.setLayoutParams(lp);
    }

    /**
     * 设置view的宽高
     * @param v
     * @param width
     * @param height
     */
    public static void setViewSizeXY(View v, int width,int height) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = (int)(width * GlobalConsts.Rx);
        lp.height = (int)(height * GlobalConsts.Ry);
        v.setLayoutParams(lp);
    }

    /**
     * 设置view的margin，相对于其他控件
     * @param v
     * @param marginLeft
     * @param marginTop
     * @param marginRight
     * @param marginBottom
     * @throws Exception
     */
    public static void setViewMarginSize(View v, int marginLeft, int marginTop,
                                         int marginRight, int marginBottom) throws Exception {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.leftMargin = (int) (marginLeft * GlobalConsts.Rx);
            mlp.topMargin = (int) (marginTop * GlobalConsts.Ry);
            mlp.rightMargin = (int) (marginRight * GlobalConsts.Rx);
            mlp.bottomMargin = (int) (marginBottom * GlobalConsts.Ry);
            v.setLayoutParams(mlp);
        }
    }

    /**
     * 设置view的padding值
     * @param v
     * @param paddingLeft
     * @param paddingTop
     * @param paddingRight
     * @param paddingBottom
     */
    public static void setViewPaddingSize(View v, float paddingLeft,
                                             float paddingTop, float paddingRight, float paddingBottom) {
        int pLeft = (int) (paddingLeft * GlobalConsts.Rx);
        int pTop = (int) (paddingTop * GlobalConsts.Ry);
        int pRight = (int) (paddingRight * GlobalConsts.Rx);
        int pBottom = (int) (paddingBottom * GlobalConsts.Ry);
        v.setPadding(pLeft, pTop, pRight, pBottom);
    }

    /**
     * 使listview内容全部呈现
     * @param lv
     * @param itemHeight
     */
    public static void makeListViewFullSize(ListView lv, int itemHeight) {
        int itemCount = lv.getAdapter().getCount();
        int divider = lv.getDividerHeight();
        int height = (itemHeight + divider) * itemCount;
        ViewGroup.LayoutParams lp = lv.getLayoutParams();
        lp.height = (int)(height * GlobalConsts.Ry);
        lv.setLayoutParams(lp);
    }

    /**
     * 使listview内容全部呈现
     * @param gv
     * @param itemHeight
     * @param rowNum
     */
    public static void makeGridViewFullSize(GridView gv, int itemHeight,
                                            int rowNum) {
        int itemCount = gv.getAdapter().getCount();
        int lines = (int) (itemCount / rowNum);
        if (itemCount % rowNum != 0) {
            lines++;
        }
        ViewGroup.LayoutParams lp = gv.getLayoutParams();
        lp.height = (int)((lines * itemHeight)*GlobalConsts.Ry);
        gv.setLayoutParams(lp);
    }

    /**
     * 图片实现有倒影的效果
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

    /**
     * setTextSize()
     * 该方法一个参数的时候单位是scaled pixel，就是sp，不是px(像素)
     * 两个参数的重载方法，一个是单位，一个是数值。
     * 例子：TextView mName = (TextView)findViewById(R.id.name);
     * mName.setTextSize(
     *                  TypedValue.COMPLEX_UNIT_PX,
     *                  getResources().getDimensionPixelSize(R.dimen.my_text_size));
     * 设置字体的大小
     * @param view
     * @param size
     */
    public static void setTextSize(TextView view, float size){
        if(GlobalConsts.Rx != 0 && GlobalConsts.Ry != 0 ){
            if(GlobalConsts.Rx >= GlobalConsts.Ry){
                view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * GlobalConsts.Rx);
            }else {
                view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * GlobalConsts.Ry);
            }
        }
    }


    /**
     * 设置Activity的位置
     * @param activity
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void setActivitySizePos(Activity activity, int x, int y,
                                          int width, int height) {
        WindowManager.LayoutParams p = activity.getWindow().getAttributes();
        p.x = (int)(x * GlobalConsts.Rx);
        p.y = (int)(y * GlobalConsts.Ry);
        p.width = (int)(width * GlobalConsts.Rx);
        p.height = (int)(height * GlobalConsts.Ry);
        activity.getWindow().setAttributes(p);
    }


    public static boolean isScreenCenter(MotionEvent e) {
        boolean ret = true;
        if (e.getX() < (metrics.widthPixels / 2 - 25)) {
            ret = false;
        }
        if (e.getX() > (metrics.widthPixels / 2 + 25)) {
            ret = false;
        }
        if (e.getY() < (metrics.heightPixels / 2 - 25)) {
            ret = false;
        }
        if (e.getY() > (metrics.heightPixels / 2 + 25)) {
            ret = false;
        }
        return ret;
    }


    /**
     * 获取状态栏的高度
     * @param activity
     * @return
     */
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


    /**
     * 获取导航条的高度
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        try {
            int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
            int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
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
