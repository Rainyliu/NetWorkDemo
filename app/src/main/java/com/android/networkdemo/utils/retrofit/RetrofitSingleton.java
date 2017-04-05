package com.android.networkdemo.utils.retrofit;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.networkdemo.utils.retrofit.api.ApiConst;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/29 11:30
 */

public class RetrofitSingleton {
    public static final int DEFAULT_TIMEOUT = 30;
    private static HashMap<Class, Object> apis = new HashMap<>();

    private static Retrofit retrofit = null;

    private static OkHttpClient okHttpClient = null;

    private static final String TAG = RetrofitSingleton.class.getSimpleName();

    private volatile static RetrofitSingleton singleton;

    private RetrofitSingleton() {
    }

    public static RetrofitSingleton getInstance(Context context) {
        if (singleton == null) {
            synchronized (RetrofitSingleton.class) {
                if (singleton == null) {
                    singleton = new RetrofitSingleton();
                }
            }
        }
        init(context);
        return singleton;
    }

    /**
     * 初始化
     */

    private static void init(Context context) {
//        initOkHttpClient(context);
        initRetrofit();
    }

//    private static void initOkHttpClient(Context context) {
//        if (okHttpClient == null) {
//            // new MyInterceptor(context)
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC.BODY : HttpLoggingInterceptor.Level.NONE);
//            okHttpClient = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                    .addNetworkInterceptor(new HeaderInterceptor()).addInterceptor(logging).retryOnConnectionFailure(true).build();
//        }
//    }

    private static void initRetrofit() {
        if (retrofit == null) {
            Executor executor = Executors.newCachedThreadPool();
            Gson gson = new GsonBuilder().serializeNulls().create();
            retrofit = new Retrofit.Builder()
//                    .addConverterFactory(ProtoConverterFactory.create())
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(ApiConst.MAIN)
//                    .callbackExecutor(executor)
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .client(okHttpClient)
                    .build();
        }
    }

    public <T> T getApi(Class<T> service) {
        if (!apis.containsKey(service)) {
            T instance = retrofit.create(service);
            apis.put(service, instance);
        }
        return (T) apis.get(service);
    }

    public <T> T getApiService(Class<T> service) {
        return getApi(service);
    }

    public Retrofit getRetrofit() {
        if (retrofit != null) return retrofit;
        initRetrofit();
        return retrofit;
    }

    public OkHttpClient getOkHttpClient(Context context) {
        if (okHttpClient != null) return okHttpClient;
//        initOkHttpClient(context);
        return okHttpClient;
    }

    public static void disposeFailureInfo(Throwable t, Context context, View view) {
        if (t.toString().contains("GaiException") || t.toString().contains("SocketTimeoutException") ||
                t.toString().contains("UnknownHostException")) {
//            Snackbar.make(view, "网络不好,~( ´•︵•` )~", Snackbar.LENGTH_LONG).show();
            Toast.makeText(context, "网络不好,~( ´•︵•` )~", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
        }
        Logger.w(TAG, t.toString());
    }
}
