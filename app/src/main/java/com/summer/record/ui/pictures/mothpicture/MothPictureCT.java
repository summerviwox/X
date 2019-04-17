package com.summer.record.ui.pictures.mothpicture;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.ui.pictures.home.PictureHomeCT;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;

import androidx.annotation.Nullable;

public class MothPictureCT extends XFragment<MothPictureUI,MothPictureDE,MothPictureVA> implements BaseQuickAdapter.OnItemClickListener , OnProgressI {

    public static MothPictureCT getInstance(){
        MothPictureCT mothPictureCT = new MothPictureCT();
        return  mothPictureCT;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUI().initMonth(getAct(),this);
        getVA().getPictureHomeDE().init(getAct(),this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        start(PictureHomeCT.getInstance(PictureHomeCT.MODEL_SCAN,getDE().getStartEndTimeStamp(getVA().getStrs().get(position))));
    }

    @Override
    public void onProgress(String tag, int status, Object data) {
        switch (tag){
            case "getMaxMinDateStamp":
                getVA().setStrs(getDE().getMonthList((Long[]) data));
               getUI().setNewData(getVA().getStrs());
                break;
            case "getPictures":
                switch (status){
                    case END:
                        getDE().getMaxMinDateStamp(this);
                        break;
                }
                break;
        }
    }
}
