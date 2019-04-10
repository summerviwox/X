package com.summer.record.ui.pictures.pictures;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.FragPictureHomeBinding;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;

public class PictureHomeUI extends UI<FragPictureHomeBinding> {

    private PictureHomeAdapter pictureHomeAdapter;

    public static final int SPANCOUNT = 6;

    public void initRecord(Context context, ArrayList<PictureB> pictures, BaseQuickAdapter.OnItemClickListener onItemClickListener, BaseQuickAdapter.OnItemLongClickListener onItemLongClickListener){
        pictureHomeAdapter= new PictureHomeAdapter(context);
        getUI().recycle.setLayoutManager(new GridLayoutManager(context,SPANCOUNT));
        pictureHomeAdapter.setOnItemClickListener(onItemClickListener);
        pictureHomeAdapter.setOnItemLongClickListener(onItemLongClickListener);
        pictureHomeAdapter.bindToRecyclerView(getUI().recycle);
        getUI().recycle.addItemDecoration(new TimeDecoration(context,pictures,SPANCOUNT));
    }

    public void refreshRecord(ArrayList<PictureB> pictures){
        pictureHomeAdapter.setNewData(pictures);
    }

}
