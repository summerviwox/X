package com.summer.record.ui.pictures.mothpicture;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.databinding.FragMonthpictureBinding;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MothPictureUI extends UI<FragMonthpictureBinding> {

    MothPictureAdapter mothPictureAdapter;

    public void initMonth(Context context, BaseQuickAdapter.OnItemClickListener onItemClickListener){
        mothPictureAdapter = new MothPictureAdapter(context);
        getUI().recycle.setLayoutManager(new GridLayoutManager(context,4));
        mothPictureAdapter.bindToRecyclerView(getUI().recycle);
        getUI().recycle.addItemDecoration(new MonthPictureDecoration(context,4));
        mothPictureAdapter.setOnItemClickListener(onItemClickListener);
    }

    public void setNewData(ArrayList<String> strs){
        mothPictureAdapter.setNewData(strs);
    }

}
