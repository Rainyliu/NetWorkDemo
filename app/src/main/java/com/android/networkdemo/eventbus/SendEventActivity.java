package com.android.networkdemo.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.android.networkdemo.R;
import com.android.networkdemo.rxbus.UserEvent;
import com.android.networkdemo.utils.DeviceUtils;
import com.joy.rxbus.RxBus;

import org.greenrobot.eventbus.EventBus;

public class SendEventActivity extends AppCompatActivity {
    private BaseEvents.CommonEvent event;

        private EventBus eventBus;
    private String TAG = "liuyuting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_event);
        eventBus = EventBus.getDefault();
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn1:
//                EventBus.getDefault().postSticky(new MessageEvent("send message"));
                BaseEvents.CommonEvent event = BaseEvents.CommonEvent.LOGIN;
                event.setObject("send event sticky");//传入一个String对象
                eventBus.postSticky(event);
                break;
            case R.id.btn2:
//                Log.d(TAG, "processClick:==ip== "+ DeviceUtils.getLocalIPAddress());
//                Log.d(TAG, "processClick:==screen== "+ DeviceUtils.getScreenPix(getApplicationContext()));
//                Log.d(TAG, "processClick:==dip2px== "+ DeviceUtils.dip2px(this,200));
//                Log.d(TAG, "processClick:==px2dip== "+ DeviceUtils.px2dip(this,1920));
//                Log.d(TAG, "processClick:==sp2px== "+ DeviceUtils.sp2px(this,24));
//                Log.d(TAG, "processClick:==px2sp== "+ DeviceUtils.px2sp(this,40));
//                Log.d(TAG, "processClick:==getStatusBarHeight== "+ DeviceUtils.getStatusBarHeight(getApplicationContext()));
//                Log.d(TAG, "processClick:==getNavigationBarHeight== "+ DeviceUtils.getNavigationBarHeight(getApplicationContext()));
//                Log.d(TAG, "processClick:==getNetType== "+ DeviceUtils.getNetType(getApplicationContext()));
//                Log.d(TAG, "processClick:==isPhone== "+ DeviceUtils.isPhone(getApplicationContext()));
//                Log.d(TAG,"The screenInches "+DeviceUtils.getScreenSizeOfDevice(getApplicationContext()));
//                Log.d(TAG, "Screen inches getScreenSizeOfDevice2: " + DeviceUtils.getScreenSizeOfDevice2(this));
//                startActivity(new Intent(this, ReceiverEventActivity.class));
                RxBus.get().postNext(new UserEvent(1, "yoyo"));
                break;
        }
//        
    }



}
