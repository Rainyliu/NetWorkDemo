package com.android.networkdemo.widgets.video;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.networkdemo.R;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/6/1 11:34
 */

public class VideoControlPanel extends FrameLayout implements Animation.AnimationListener,IVideoControlPanel, View.OnTouchListener, Runnable {

    private static final int DELAYMILLIS = 3 * 1000;//延迟时间
    private static final int DURATION = 300;//动画持续时间
    private Animation topEnter, topExit, buttomEnter, buttomExit;

    private boolean isShowing = false;//是否显示

    private View mRoot;//根布局

    private TextView tvTitle;
    private LinearLayout llTitle, llChange, llHangup;
    private FrameLayout flOperate;

    private Handler handler = new Handler();

    public VideoControlPanel(@NonNull Context context) {
        super(context);
        initControllerPanel();
    }

    public VideoControlPanel(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControllerPanel();
    }

    public VideoControlPanel(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControllerPanel();
    }

    private void initControllerPanel() {
        initOther();
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRoot = inflater.inflate(R.layout.media_controller,null);

        llTitle = (LinearLayout) mRoot.findViewById(R.id.llTitle);
        flOperate = (FrameLayout) mRoot.findViewById(R.id.flOperate);

        tvTitle = (TextView) mRoot.findViewById(R.id.tvTitle);
        llHangup = (LinearLayout) mRoot.findViewById(R.id.llHangup);
        llChange = (LinearLayout) mRoot.findViewById(R.id.llChange);

        setOnTouchListener(this);

        addView(mRoot);

        show();

    }

    private void initOther() {
        topEnter = new TranslateAnimation(0,0,-100,0);
        topEnter.setDuration(DURATION);

        topExit = new TranslateAnimation(0,0,0,-100);
        topExit.setDuration(DURATION);
        topExit.setAnimationListener(this);

        buttomEnter = new TranslateAnimation(0,0,300,0);
        buttomEnter.setDuration(DURATION);

        buttomExit = new TranslateAnimation(0,0,0,100);
        buttomExit.setDuration(DURATION);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(mRoot != null){
            mRoot.setVisibility(View.GONE);
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void hide() {
        isShowing = false;
        llTitle.startAnimation(topExit);
        flOperate.startAnimation(buttomExit);

    }

    @Override
    public void show() {
        if(!isShowing){
            mRoot.setVisibility(View.VISIBLE);
            isShowing = true;

            llTitle.startAnimation(topEnter);
            flOperate.startAnimation(buttomEnter);
        }

        handler.removeCallbacks(this);
        handler.postDelayed(this,DELAYMILLIS);
    }

    @Override
    public void setTitle(String title) {
        if(tvTitle != null){
            tvTitle.setText(title);
        }
    }

    @Override
    public View getHangupView() {
        return llHangup;
    }

    @Override
    public View getChangeView() {
        return llChange;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        show();
        return false;
    }


    @Override
    public void run() {
        hide();
    }
}
