package com.android.networkdemo.utils.retrofit.interceptor;

import android.os.Build;

import com.android.networkdemo.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

/**
 * Author: liuyuting
 * Description: NetWorkDemo 封装头部
 * Since: 2017/3/29 11:26
 */

public class HeaderInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        //封装headers
        Request request = chain.request().newBuilder()
                .addHeader("Accept","application/json")
                .addHeader("Content","application/json;charset=UTF-8")
//                .addHeader("User-Agent", Util.getApp().buildUserAgent())
                .addHeader("OS", "Android " + Build.VERSION.RELEASE + " " + Build.MODEL)
//                .addHeader("clientID", BuildConfig.CLIENTID)
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build();

        return chain.proceed(request);
    }
}
