package com.android.networkdemo.ui.base;

import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/31 11:13
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;

    public View convertView;
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isVisible = true;
            lazyLoad();//懒加载
        }else {
            isVisible = false;//设置已经不是可见的
        }
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        if(!isFirstLoad || !isVisible || !isInitView){
           //如果不是第一次加载、不是可见的、不是初始化view，则不加载数据
            return;
        }
        //加载数据
        initListener();
        initData();
        //设置已经不是第一次加载
        isFirstLoad = false;
    }

    public <E extends View> E findView(int viewId){
        if(convertView != null){
            E view = (E) mViews.get(viewId);
            if(view == null){
                view = (E) convertView.findViewById(viewId);
                mViews.put(viewId,view);
            }
            return view;
        }
        return null;
    }

    public <E extends View> void setOnclick(E view){
        view.setOnClickListener(this);
    }
}
