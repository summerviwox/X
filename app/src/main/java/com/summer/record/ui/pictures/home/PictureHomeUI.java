package com.summer.record.ui.pictures.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.R;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.FragPictureHomeBinding;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;

public class PictureHomeUI extends UI<FragPictureHomeBinding> {

    private PictureHomeAdapter pictureHomeAdapter;

    public void initRecord(Context context, ArrayList<PictureB> pictures, BaseQuickAdapter.OnItemClickListener onItemClickListener, BaseQuickAdapter.OnItemLongClickListener onItemLongClickListener){
        pictureHomeAdapter= new PictureHomeAdapter(context);
        getUI().recycle.setLayoutManager(new GridLayoutManager(context, NetConstant.SPANCOUNT));
        pictureHomeAdapter.setOnItemClickListener(onItemClickListener);
        pictureHomeAdapter.setOnItemLongClickListener(onItemLongClickListener);
        pictureHomeAdapter.bindToRecyclerView(getUI().recycle);
        getUI().recycle.addItemDecoration(new TimeDecoration(context,pictures,NetConstant.SPANCOUNT));
    }

    public void refreshRecord(Context context,ArrayList<PictureB> pictures){
        if(getUI().recycle.getItemDecorationCount()!=0){
            for(int i=0;i<getUI().recycle.getItemDecorationCount();i++){
                getUI().recycle.removeItemDecorationAt(i);
            }
        }
        getUI().recycle.addItemDecoration(new TimeDecoration(context,pictures,NetConstant.SPANCOUNT));
        getUI().recycle.setLayoutManager(new GridLayoutManager(context, NetConstant.SPANCOUNT));
        pictureHomeAdapter.setNewData(pictures);
    }

    public void setSureVisible(int model){
        getUI().sure.setVisibility(model==PictureHomeCT.MODEL_SELECT?View.VISIBLE: View.GONE);
    }

    public void notifyItemChanged(int pos){
        pictureHomeAdapter.notifyItemChanged(pos);
    }

}
