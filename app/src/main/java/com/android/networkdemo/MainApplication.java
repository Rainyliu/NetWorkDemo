package com.android.networkdemo;

import android.app.Application;



/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/29 11:05
 */

public class MainApplication extends Application {
//    public static Bus sBus;

    @Override
    public void onCreate() {
        super.onCreate();
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
}
