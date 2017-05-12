package com.android.networkdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.networkdemo.MainApplication;
import com.android.networkdemo.R;
import com.android.networkdemo.entity.Bg;
import com.android.networkdemo.ui.base.BaseActivity;

/**
 * 全局更换背景的广播接受者
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/12 13:15
 */

public class BgChangeBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
//            Bg bg = (Bg) intent.getSerializableExtra("bg");
//            Log.d("liuyuting", "onReceive: bg.id=="+bg.getId());
            Log.d("liuyuting", "收到了");
            if(context instanceof BaseActivity){
                BaseActivity mBase = (BaseActivity) context;
                Log.d("liuyuting", "huan");
                Bg bg = new Bg();
                bg.setId(1);
                bg.setBm(BitmapFactory.decodeResource(context.getResources(), R.mipmap.b));
                MainApplication.setBg(bg);
//                mBase.setBackgroundBitmap();
            }
        }
    }
}
