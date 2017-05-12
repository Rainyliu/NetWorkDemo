package com.android.networkdemo.utils;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/19 19:33
 */

public class GlobalConsts {
    //静态变量在内存紧张的时候会被回收
    public static float Rx = 1f;
    public static float Ry = 1f;

    public static int screenWidth = 1920;//设计图的高
    public static int screenHeight = 1080;//设计图的宽

    public static boolean isVideoPrepareComplete = false;
    public static String BG_CHANGE_ACTION = "com.rainy.networkdemo.BG_CHANGE_ACTION";
}
