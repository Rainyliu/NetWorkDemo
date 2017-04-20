package com.android.networkdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.networkdemo.R;
import com.android.networkdemo.widgets.image.ImageReflect;
import com.android.networkdemo.widgets.image.ScaleAnimEffect;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/13 11:50
 */

public class View1 extends LinearLayout implements View.OnClickListener,View.OnFocusChangeListener{
    private Context mContext;
    private ImageView[] refImg = new ImageView[2];
    private FrameLayout[] fls = new FrameLayout[5];
    private ImageView[] backgrounds = new ImageView[5];//背景图片层
    private ImageView[] typeLogs = new ImageView[5];//类型标志

    public View1(Context context) {
        super(context);
        mContext = context;
    }

    //初始化UI
    public void initView(){
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER_HORIZONTAL);
        View root = LayoutInflater.from(mContext).inflate(R.layout.layout1,null);
        addView(root);

        refImg[0] = (ImageView) findViewById(R.id.settings_layout_refimg_0);
        refImg[1] = (ImageView) findViewById(R.id.settings_layout_refimg_1);

        fls[0] = (FrameLayout) findViewById(R.id.setting_layout_fl_0);
        fls[1] = (FrameLayout) findViewById(R.id.setting_layout_fl_1);
        fls[2] = (FrameLayout) findViewById(R.id.setting_layout_fl_2);
        fls[3] = (FrameLayout) findViewById(R.id.setting_layout_fl_3);
        fls[4] = (FrameLayout) findViewById(R.id.setting_layout_fl_4);

        typeLogs[0] = (ImageView) findViewById(R.id.setting_layout_log_0);
        typeLogs[1] = (ImageView) findViewById(R.id.setting_layout_log_1);
        typeLogs[2] = (ImageView) findViewById(R.id.setting_layout_log_2);
        typeLogs[3] = (ImageView) findViewById(R.id.setting_layout_log_3);
        typeLogs[4] = (ImageView) findViewById(R.id.setting_layout_log_4);

        backgrounds[0] = (ImageView) findViewById(R.id.setting_layout_bg_0);
        backgrounds[1] = (ImageView) findViewById(R.id.setting_layout_bg_1);
        backgrounds[2] = (ImageView) findViewById(R.id.setting_layout_bg_2);
        backgrounds[3] = (ImageView) findViewById(R.id.setting_layout_bg_3);
        backgrounds[4] = (ImageView) findViewById(R.id.setting_layout_bg_4);

        //设置事件
        for (int i = 0; i < 5; i++) {
            typeLogs[i].setOnClickListener(this);
            typeLogs[i].setOnFocusChangeListener(this);
            backgrounds[i].setVisibility(View.GONE);
        }

        initRef();
    }

    /**
     * 倒影的实现
     */
    private void initRef() {
        int refIndex = 0;
        for (int i = 0; i < 2; i++) {
            switch (i){
                case 0:
                    Bitmap rebm = ImageReflect.createCutReflectedImage(ImageReflect.convertViewToBitmap(fls[1]),0);
                    refImg[refIndex].setImageBitmap(rebm);
                    refIndex++;
                    break;
                case 1:
                    Bitmap rebm1 = ImageReflect.createCutReflectedImage(
                            ImageReflect.convertViewToBitmap(fls[2]), 0);
                    refImg[refIndex].setImageBitmap(rebm1);
                    refIndex++;
                    break;
            }
        }
    }

    //动画效果实现
    ScaleAnimEffect animEffect = new ScaleAnimEffect();

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.setting_layout_log_0:
                Toast.makeText(mContext, "点击了无线设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_layout_log_1:
                Toast.makeText(mContext, "点击了检查更新", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_layout_log_2:
                Toast.makeText(mContext, "点击了本地媒体", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_layout_log_3:
                Toast.makeText(mContext, "点击了系统设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_layout_log_4:
                Toast.makeText(mContext, "点击了壁纸设置", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.setting_layout_log_0:
                if (hasFocus) {
                    showOnFocusAnimation(0);
                } else {
                    showLooseFocusAinimation(0);
                }
                break;
            case R.id.setting_layout_log_1:
                if (hasFocus) {
                    showOnFocusAnimation(1);
                } else {
                    showLooseFocusAinimation(1);
                }
                break;
            case R.id.setting_layout_log_2:
                if (hasFocus) {
                    showOnFocusAnimation(2);
                } else {
                    showLooseFocusAinimation(2);
                }
                break;
            case R.id.setting_layout_log_3:
                if (hasFocus) {
                    showOnFocusAnimation(3);
                } else {
                    showLooseFocusAinimation(3);
                }
                break;
            case R.id.setting_layout_log_4:
                if (hasFocus) {
                    showOnFocusAnimation(4);
                } else {
                    showLooseFocusAinimation(4);
                }
                break;
        }
    }

    //失去焦点后的动画
    private void showLooseFocusAinimation(int position) {
        animEffect.setAttributs(1.10f, 1.0f, 1.10f, 1.0f, 100);
        if (position == 0) {

        } else if (position == 1) {

        } else if (position == 4) {

        }
        typeLogs[position].startAnimation(animEffect.createAnimation());
        backgrounds[position].setVisibility(View.GONE);
    }
    //获得焦点后的显示动画
    private void showOnFocusAnimation(final int position) {
        fls[position].bringToFront();
        animEffect.setAttributs(1.0f, 1.10f, 1.0f, 1.10f, 100);
        Animation anim = animEffect.createAnimation();
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                backgrounds[position].startAnimation(animEffect.alphaAnimation(
                        0, 1, 150, 0));
                backgrounds[position].setVisibility(View.VISIBLE);

            }
        });
        typeLogs[position].startAnimation(anim);

    }
}
