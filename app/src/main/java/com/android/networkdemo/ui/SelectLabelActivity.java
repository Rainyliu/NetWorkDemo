package com.android.networkdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.android.networkdemo.R;
import com.android.networkdemo.adapter.LabelAdapter;
import com.android.networkdemo.widgets.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectLabelActivity extends AppCompatActivity {
    private XRecyclerView recyclerView;
    private LabelAdapter adapter;
    private Map<String ,List<String>> map;
    private List<String> listKey;
    private List<String> listValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_label);
        recyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setExpanded(false);
        initData();
        GridLayoutManager manager = new GridLayoutManager(this,4);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new LabelAdapter(this,map,listKey);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        map = new HashMap<>();
        listKey = new ArrayList<>();
        listValue = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listValue.add("子标签"+i);
        }
        for (int i = 0; i < 7; i++) {
            listKey.add("我是标签"+i);
        }

        for (int i = 0; i < 7; i++) {
            map.put(listKey.get(i),listValue);
        }

    }
}
