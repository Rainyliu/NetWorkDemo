package com.android.networkdemo.ui;

import android.animation.Animator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.android.networkdemo.R;

public class VectorActivity extends AppCompatActivity {
    private ImageView btn_anim;
    private static String TAG = "liuyuting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
        btn_anim = (ImageView) findViewById(R.id.btn_anim);
    }

    public void anim(View view){
        Log.d(TAG, "anim:======= ");
//        switch (view.getId()){
//            case R.id.btn_anim:
                Drawable drawable = btn_anim.getDrawable();
                if(drawable instanceof Animatable){
                    ((Animatable)drawable).start();
                }
//                break;
//        }
    }
}
