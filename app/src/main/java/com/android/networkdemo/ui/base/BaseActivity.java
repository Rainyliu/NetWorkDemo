package com.android.networkdemo.ui.base;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.networkdemo.MainApplication;
import com.android.networkdemo.R;
import com.android.networkdemo.entity.Bg;
import com.android.networkdemo.receiver.BgChangeBroadcastReceiver;
import com.android.networkdemo.utils.GlobalConsts;
import com.android.networkdemo.views.CustomBitmapDrawable;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/31 11:14
 */

public abstract class BaseActivity extends AppCompatActivity{
    private View view = null;
    private BgChangeBroadcastReceiver receiver = null;
    private IntentFilter fliter = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置背景
        view = this.getWindow().getDecorView();   //getDecorView 获得window最顶层的View
//        view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg_global));
//        setBackgroundBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bg_global));
        setContentView(setLayoutID());
        receiver = new BgChangeBroadcastReceiver();
        fliter = new IntentFilter(GlobalConsts.BG_CHANGE_ACTION);
//        registerReceiver(receiver,fliter);
    }

    protected abstract int setLayoutID();

    protected BaseActivity getActivity(){
        return this;
    }

    public void setBackgroundBitmap(Bitmap bm){
        view.setBackground(new CustomBitmapDrawable(bm));
    }

    public Drawable getBackgroundBitmap(){
        return view.getBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        registerReceiver(receiver,fliter);
        setBackgroundBitmap(MainApplication.getBg().getBm());
        Log.d("liuyuting", "onResume: baseactivity的换背景");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
    }
}
