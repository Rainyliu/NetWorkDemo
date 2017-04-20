package com.android.networkdemo.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.networkdemo.R;
import com.android.networkdemo.adapter.AllPagerAdapter;
import com.android.networkdemo.views.View1;
import com.android.networkdemo.views.View2;
import com.android.networkdemo.views.View3;
import com.android.networkdemo.views.View4;

import java.util.ArrayList;
import java.util.List;

public class TvHomeActivity extends AppCompatActivity {
    private View1 v1;
    private View2 v2;
    private View3 v3;
    private View4 v4;

    private List<View> pages = new ArrayList<>();
    private ViewPager centerPager;
    private RadioGroup titleGroup;//标签组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_home);
        v1 = new View1(this);
        v2 = new View2(this);
        v3 = new View3(this);
        v4 = new View4(this);

        pages.add(v1);
        pages.add(v2);
        pages.add(v3);
        pages.add(v4);//添加页面

        initView();
        initListener();

    }

    private void initView() {
        titleGroup = (RadioGroup) findViewById(R.id.title_group);
        titleGroup.check(R.id.rb1);
        centerPager = (ViewPager) findViewById(R.id.main_layout_pager);
        centerPager.setAdapter(new AllPagerAdapter(pages));
        //当前显示第一页
        centerPager.setCurrentItem(0);
        v1.initView();
        v2.initView();
        v3.initView();
        v4.initView();//加载页面
    }

    /**
     * //对radiogroup的焦点监听，进行页面的切换
     */
    private void initListener() {
        View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    int position = (int) v.getTag();
                    centerPager.setCurrentItem(position,true);
                }
            }
        };

        for (int i = 0; i < titleGroup.getChildCount(); i++) {
            View childAt = titleGroup.getChildAt(i);
            childAt.setTag(i);
            childAt.setOnFocusChangeListener(focusListener);
        }

        centerPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position < titleGroup.getChildCount()){
                    ((RadioButton)titleGroup.getChildAt(position)).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
