package com.android.networkdemo.ui.base;

import android.app.Application;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/31 13:12
 */

public abstract class BaseApplication extends Application {
    public abstract void initConfigs();

    @Override
    public void onCreate() {
        super.onCreate();
        initConfigs();
    }
}
