package com.android.networkdemo.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.networkdemo.R;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void click(View v){
        ComponentName cp = new ComponentName(
                "com.android.launcher1905",
                "com.android.launcherxc1905.filmnew.NewFilmDetailActivity");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        Bundle bundle = new Bundle();
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
}
