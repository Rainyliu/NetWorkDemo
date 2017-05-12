package com.android.networkdemo.ui.settings;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.networkdemo.R;
import com.android.networkdemo.ui.base.BaseActivity;
import com.android.networkdemo.views.RoundImageDrawable;

public class SettingsActivity extends BaseActivity{
    private static String TAG = "liuyuting";
    private ImageView iv;
    private Bitmap bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
        Log.d(TAG, "onCreate:SettingsActivity ");
        iv = (ImageView) findViewById(R.id.iv);
    }

    @Override
    protected int setLayoutID() {
        return R.layout.activity_settings;
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(getActivity(),FileSettingsActivity.class));
                break;
            case R.id.btn2:
                bm = BitmapFactory.decodeResource(getResources(),R.mipmap.xunlongjue);
                iv.setImageDrawable(new RoundImageDrawable(bm));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: SettingsActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:SettingsActivity ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause:SettingsActivity ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop:SettingsActivity ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: SettingsActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: SettingsActivity");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: SettingsActivity");
    }
}
