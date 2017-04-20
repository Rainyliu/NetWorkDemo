package com.android.networkdemo.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/13 13:25
 */

public class AllPagerAdapter extends PagerAdapter {
    private List<View> views;

    public AllPagerAdapter(List<View> views){
        super();
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View child = views.get(position);
        container.addView(child);
        return child;
    }

    public void dataChanged(List<View> views){
        this.views = views;
        notifyDataSetChanged();
    }
}
