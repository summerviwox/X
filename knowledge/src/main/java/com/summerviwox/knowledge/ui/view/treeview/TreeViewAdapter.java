package com.summerviwox.knowledge.ui.view.treeview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.summerviwox.knowledge.R;
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
        LogUtils.e(222);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(R.id.position,position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag(R.id.position);
        Intent intent = null;
        switch (position){
            case 0:
                intent = new Intent(v.getContext(), CustomerViewActivity.class);
                v.getContext().startActivity(intent);
                break;
        }
    }

    public static class TreeViewHolder extends RecyclerView.ViewHolder{

        public TreeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
