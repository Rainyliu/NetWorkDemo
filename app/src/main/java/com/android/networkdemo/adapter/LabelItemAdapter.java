package com.android.networkdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.networkdemo.R;

import java.util.List;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/18 下午6:44
 */

public class LabelItemAdapter extends RecyclerView.Adapter<LabelItemAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;

    public LabelItemAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.label_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("liuyuting", "onBindViewHolder: =====list==="+list);
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
