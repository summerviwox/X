package com.summerviwox.knowledge.ui.view.treeview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.summerviwox.knowledge.R;
import com.summerviwox.knowledge.databinding.ItemTreeviewBinding;

/**
 * summer 2022/8/4 16:09
 **/
public class TreeViewAdapter extends RecyclerView.Adapter<TreeViewAdapter.TreeViewHolder>{


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
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class TreeViewHolder extends RecyclerView.ViewHolder{

        public TreeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
