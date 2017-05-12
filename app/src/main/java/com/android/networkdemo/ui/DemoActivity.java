package com.android.networkdemo.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.networkdemo.R;
import com.android.networkdemo.utils.DeviceUtils;

public class DemoActivity extends AppCompatActivity {
    private static String TAG = "liuyuting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.btn1:
                gotoDetails();
                break;
            case R.id.btn2:
                printDeviceInfo();
                startActivity(new Intent(DemoActivity.this,TestActivity.class));
                break;
        }

    }


    private void gotoDetails(){
        /**
         * 从别的app跳转到观影神器的电影详情页面
         * 需要用bundle传参
         * 需要两个String类型的参数
         * source  thirdApp
         * filmId  当前点击的filmId
         */
        ComponentName cp = new ComponentName(
                "com.android.launcher1905",
                "com.android.launcherxc1905.filmnew.NewFilmDetailActivity");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        Bundle bundle = new Bundle();
        bundle.putString("source","thirdApp");
        bundle.putString("filmId","13533823" );
        intent.putExtra("activityBundle", bundle);
        intent.setComponent(cp);
        startActivity(intent);


//        ComponentName cp = new ComponentName("com.android.jinshanyun1905",
//                "com.android.jinshanyun1905.VideoActivity1");
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.putExtra("videoPath","http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4");
//        intent.putExtra("mVideoTitle","title");
//        intent.setComponent(cp);
//        startActivity(intent);


//        ComponentName cp = new ComponentName(
//                "com.android.launcher1905",
//                "com.android.launcherxc1905.Demo1Activity");
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        Bundle bundle = new Bundle();
//        bundle.putString("filmId","13533823" );
//        intent.putExtra("activityBundle", bundle);
//        intent.putExtra("filmId","13533823");
//        intent.setComponent(cp);
//        startActivity(intent);

    }

    /**
     * 打印设备信息
     */
    private void printDeviceInfo(){
        Log.d(TAG, "printDeviceInfo: 设备信息===="+ DeviceUtils.getScreenPix(getApplicationContext()));
        Toast.makeText(DemoActivity.this,"设备信息="+DeviceUtils.getScreenPix(getApplicationContext()),Toast.LENGTH_LONG).show();
    }
}
