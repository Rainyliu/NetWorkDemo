package com.android.networkdemo.views;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.networkdemo.R;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/13 11:50
 */

public class View4 extends LinearLayout {
    private Context mContext;
    public View4(Context context) {
        super(context);
        mContext = context;
    }

    public void initView(){
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER_HORIZONTAL);
        View root = LayoutInflater.from(mContext).inflate(R.layout.layout4,null);
        addView(root);
    }
}
