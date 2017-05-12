package com.android.networkdemo.ui.settings;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.networkdemo.MainApplication;
import com.android.networkdemo.R;
import com.android.networkdemo.entity.Bg;
import com.android.networkdemo.ui.base.BaseActivity;
import com.android.networkdemo.utils.GlobalConsts;

public class FileSettingsActivity extends BaseActivity {
    public static String TAG = "liuyuting";
    private Bg bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_file_settings);
//        setBackgroundBitmap();
        Log.d(TAG, "onCreate: ");
        bg = new Bg();
        bg.setId(1);
        bg.setBm(BitmapFactory.decodeResource(getResources(),R.mipmap.b));
    }

    @Override
    protected int setLayoutID() {
        return R.layout.activity_file_settings;
    }

    public void click(View v){
        setBackgroundBitmap(bg.getBm());
        MainApplication.setBg(bg);
//        Intent intent = new Intent();
//        intent.setAction(GlobalConsts.BG_CHANGE_ACTION);
////        intent.putExtra("bg",bg);
//        sendBroadcast(intent);
//        sendStickyBroadcast(intent);
        Log.d("liuyuting", "click: 换背景");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }
}
