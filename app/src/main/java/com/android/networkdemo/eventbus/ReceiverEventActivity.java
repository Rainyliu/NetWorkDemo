package com.android.networkdemo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.networkdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ReceiverEventActivity extends AppCompatActivity {

    private static final String TAG = "liuyuting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_event);

    }

    public void click(View v){
        startActivity(new Intent(this,SendEventActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);//反注册EventBus
        super.onStop();
    }

    //    @Override
//    protected void registEventBus() {
//        super.registEventBus();
//    }
//
//    @Override
//    protected void unRegistEventBus() {
//        super.unRegistEventBus();
//    }

    // This method will be called when a MessageEvent is posted
    @Subscribe
    public void onEventMainThread(MessageEvent event){
        Log.d(TAG, "onMessageEvent: ===="+event.getMessage());
        Toast.makeText(this, "收到=="+event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BaseEvents.CommonEvent event) {
        Log.d(TAG, "onMessageEvent: ====" + event.getObject().toString());
        Toast.makeText(this, "收到==" + event.getObject().toString(), Toast.LENGTH_SHORT).show();
        MessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.class);
// Better check that an event was actually posted before
        if (stickyEvent != null) {
// "Consume" the sticky event
            EventBus.getDefault().removeStickyEvent(stickyEvent);
            Log.d(TAG, "onMessageEvent: ====已经移除" );
        }
    }
    // This method will be called when a SomeOtherEvent is posted
//    @Subscribe
//    public void handleSomethingElse(SomeOtherEvent event){
//        doSomethingWith(event);
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
