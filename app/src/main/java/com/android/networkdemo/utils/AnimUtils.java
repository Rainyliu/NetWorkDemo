package com.android.networkdemo.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 动画加载工具类
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/6/1 11:08
 */

public class AnimUtils {
    /**
     * 实现上下左右浮动效果
     * @param v
     * @param delay
     */
    public static void floatAnim(View v,int delay){
        List<Animator> animatorList = new ArrayList<>();

        ObjectAnimator translationXAnim = ObjectAnimator.ofFloat(v,"translationX",-6.0f,6.0f,-6.0f);
        translationXAnim.setDuration(1500);
        translationXAnim.setRepeatCount(ValueAnimator.INFINITE);
        translationXAnim.setRepeatMode(ValueAnimator.REVERSE);
        translationXAnim.start();
        animatorList.add(translationXAnim);

        ObjectAnimator translationYAnim = ObjectAnimator.ofFloat(v,"translationY",-3.0f,3.0f,-3.0f);
        translationYAnim.setDuration(1000);
        translationXAnim.setRepeatCount(ValueAnimator.INFINITE);
        translationXAnim.setRepeatMode(ValueAnimator.REVERSE);
        translationXAnim.start();
        animatorList.add(translationYAnim);

        AnimatorSet btnSexAnimatorSet = new AnimatorSet();
        btnSexAnimatorSet.playTogether(animatorList);
        btnSexAnimatorSet.setStartDelay(delay);
        btnSexAnimatorSet.start();

    }

}
