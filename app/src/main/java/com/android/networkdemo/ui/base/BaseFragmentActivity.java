package com.android.networkdemo.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.View;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/30 19:13
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener{
    private SparseArray<View> mViews;

    public abstract int getLayoutId();
    public abstract void initViews();
    public abstract void initListener();
    public abstract void initData();
    public abstract void processClick(View v);

    @Override
    public void onClick(View v) {
        processClick(v);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        setContentView(getLayoutId());
        initViews();
        initListener();
        initData();
    }

    //通过Id找到view
    public <E extends View> E findView(int viewId){
        E view = (E) mViews.get(viewId);
        if(view == null){
            view = (E) findViewById(viewId);
            mViews.put(viewId,view);
        }
        return view;
    }

    //view设置OnClick事件
    public <E extends View> void setOnClick(E view){
        view.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        registEventBus();
    }

    @Override
    protected void onStop() {
//        unRegistEventBus();
        super.onStop();
    }

//    protected void registEventBus(){
//        //子类如果需要注册eventBus，则重写此方法
//        EventBus.getDefault().register(this);
//    }
//
//    protected void unRegistEventBus(){
//        //子类如果需要注销eventbus，则重写此方法
//        EventBus.getDefault().unregister(this);
//    }

//    @Subscribe
//    public void onMessageEvent(BaseEvents event){
//    }


}
