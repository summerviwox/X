package com.summerviwox.knowledge.ui.main.view.treeview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.summerviwox.knowledge.R;
import com.summerviwox.knowledge.ui.main.MoudleList;
import com.summerviwox.knowledge.ui.moudle.activity.WindowViewActivity;
import com.summerviwox.knowledge.ui.moudle.customerview.CustomerViewActivity;

/**
 * summer 2022/8/4 16:09
 **/
public class TreeViewAdapter extends RecyclerView.Adapter<TreeViewAdapter.TreeViewHolder> implements View.OnClickListener {


    @NonNull
    @Override
    public TreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtils.e(111);
//        ItemTreeviewBinding itemTreeviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
//                R.layout.layout_basepickerview,parent,false);
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_treeview,parent,false);
        return new TreeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TreeViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(R.id.position,position);
        holder.textView.setText(MoudleList.list[position].name);
    }

    @Override
    public int getItemCount() {
        return MoudleList.list.length;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag(R.id.position);
        Intent intent = null;
        intent = new Intent("knowledge."+MoudleList.list[position].name);
        if(intent!=null){
            v.getContext().startActivity(intent);
        }
    }

    public static class TreeViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public TreeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
