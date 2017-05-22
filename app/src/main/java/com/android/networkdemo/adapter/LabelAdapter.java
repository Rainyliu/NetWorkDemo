package com.android.networkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.networkdemo.R;
import com.android.networkdemo.widgets.XRecyclerView;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/18 下午5:59
 */

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.MyHolder> {
    private Context context;
    private Map<String, List<String>> map;
    private List<String> listKey;

    public LabelAdapter(Context context, Map<String, List<String>> map, List<String> listKey) {
        this.context = context;
        this.map = map;
        this.listKey = listKey;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_label_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        TextView title = holder.title;
        final XRecyclerView xitem = holder.xitem;
        title.setText(listKey.get(position));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
//        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(context);

        xitem.setLayoutManager(gridLayoutManager);
        final LabelItemAdapter adapter = new LabelItemAdapter(context, map.get(listKey.get(position)));
        xitem.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

//        title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("liuyuting", "onClick: 点击了");
////                TextView view = (TextView) v;
////                if(view.getText().equals(listKey.get(position))){
//                xitem.setVisibility(View.VISIBLE);
//                xitem.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//////                }else {
////                    xitem.setVisibility(View.GONE);
////                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private XRecyclerView xitem;

        public MyHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleTv);
            xitem = (XRecyclerView) itemView.findViewById(R.id.xitem);
        }
    }

}
