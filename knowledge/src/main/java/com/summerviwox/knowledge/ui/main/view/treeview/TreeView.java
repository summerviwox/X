package com.summerviwox.knowledge.ui.main.view.treeview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * summer 2022/8/4 16:49
 **/
public class TreeView extends RecyclerView {

    public TreeView(@NonNull Context context) {
        super(context);
    }

    public TreeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(){
        TreeViewAdapter treeViewAdapter = new TreeViewAdapter();
        setLayoutManager(new GridLayoutManager(getContext(),3));
        setAdapter(treeViewAdapter);
    }
}
