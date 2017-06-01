package com.android.networkdemo.ui.animal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.networkdemo.R;
import com.android.networkdemo.utils.AnimUtils;

public class AnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
    }

    public void click(View v){
        AnimUtils.floatAnim(v,1000);
    }
}
