package com.android.networkdemo.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.networkdemo.R;
import com.android.networkdemo.ui.base.BaseFragment;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/31 11:45
 * 这里采用 viewPager+fragment，如果需要对fragment进行缓存，必须对viewpager进行缓存
 *
 * //设置缓存界面
 * viewpager.setOffscreenPageLimit(5)
 */

public class HomeFragment extends BaseFragment {
//    private ShopController shopController;
    private TextView tv;
    private ImageView iv;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        tv = findView(R.id.tvF);
        iv = findView(R.id.ivF);
    }

    @Override
    public void initListener() {
        setOnclick(tv);
        setOnclick(iv);
    }

    @Override
    public void initData() {
        initShop();
    }

    private void initShop() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.tvF:
                break;
            case R.id.ivF:
                break;
        }
    }
}
