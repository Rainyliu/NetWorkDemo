package com.android.networkdemo;

import android.app.Application;
import android.content.Context;
import android.graphics.BitmapFactory;

import com.android.networkdemo.entity.Bg;
import com.android.networkdemo.utils.RationUtils;


/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/29 11:05
 */

public class MainApplication extends Application {
//    public static Bus sBus;
    public static MainApplication mContext;
    private static Bg bg = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if(bg == null){
            bg = new Bg();
        }
        bg.setId(1);
        bg.setBm(BitmapFactory.decodeResource(getResources(),R.drawable.bg_global));
        RationUtils.initGlobalValues(mContext);
//        RxDownload rxDownload = RxDownload.getInstance(getApplicationContext())
////               .retrofit(myRetrofit)             //若需要自己的retrofit客户端,可在这里指定
////                .defaultSavePath(defaultSavePath) //设置默认的下载路径
//                .maxThread(3)                     //设置最大线程
//                .maxRetryCount(3)                 //设置下载失败重试次数
//                .maxDownloadNumber(5);//Service同时下载数量
    }


//    public static synchronized Bus getBus() {
//        if (sBus == null) {
//            sBus = new Bus();
//        }
//        return sBus;
//    }

    public static MainApplication getMainAplication(){
        return mContext;
    }

    public static MainApplication getmContext() {
        return mContext;
    }

    public static void setmContext(MainApplication mContext) {
        MainApplication.mContext = mContext;
    }

    public static Bg getBg() {
        return bg;
    }

    public static void setBg(Bg bg) {
        MainApplication.bg = bg;
    }
}
