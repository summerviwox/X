package com.summer.record.ui.menu;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.databinding.FragMenuBinding;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;

public class MenuUI extends UI<FragMenuBinding> {

    private MenuAdapter menuAdapter;

    public void initList(Context context, ArrayList<String> items, BaseQuickAdapter.OnItemClickListener onItemClickListener){
        menuAdapter = new MenuAdapter();
        getUI().recycle.setLayoutManager(new LinearLayoutManager(context));
        getUI().recycle.getLayoutManager().setAutoMeasureEnabled(true);
        menuAdapter.bindToRecyclerView(getUI().recycle);
        menuAdapter.setOnItemClickListener(onItemClickListener);
        menuAdapter.setNewData(items);
    }
}
