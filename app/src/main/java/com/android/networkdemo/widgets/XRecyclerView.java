package com.android.networkdemo.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 内容全部展示的recyclerview
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/18 下午5:50
 */

public class XRecyclerView extends RecyclerView {
    private boolean expanded = true;
    public XRecyclerView(Context context) {
        super(context);
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isExpanded(){
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if(expanded){
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
            super.onMeasure(widthSpec,expandSpec);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        }else {
            super.onMeasure(widthSpec, heightSpec);
        }
    }
}
