package com.summer.record.ui.pictures.mothpicture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.ui.albums.albums.AlbumHomeCT;
import com.summer.record.ui.pictures.home.PictureHomeCT;
import com.summer.record.ui.pictures.test.TestFrag;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;
import com.summer.x.util.StringUtil;

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
        getVA().getPicturesDE().synchLocalAndNetToDB(getAct(), this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //startActivity(new Intent(getAct(),TestFrag.class));
        //extraTransaction().startDontHideSelf(PictureHomeCT.getInstance(PictureHomeCT.MODEL_SCAN,getDE().getStartEndTimeStamp(getVA().getStrs().get(position))));
        start(PictureHomeCT.getInstance(PictureHomeCT.MODEL_SCAN,getDE().getStartEndTimeStamp(getVA().getStrs().get(position))));
    }

    @Override
    public void onProgress(String tag, int status, Object data) {
        switch (tag){
            case "synchLocalAndNetToDB":
                switch (status){
                    case PREPARE:
                        getVA().getLoadingFrag().setOnFinishI(new OnFinishI() {
                            @Override
                            public void onFinished(Object o) {

                            }
                        });
                        extraTransaction().startDontHideSelf(getVA().getLoadingFrag(),STANDARD);
                        break;
                    case DOING:
                        ToastUtils.showLong(StringUtil.getStr(data));
                        break;
                    case END:
                        getDE().getMaxMinDateStamp(this);
                        getVA().getLoadingFrag().pop();
                        break;
                }
                break;
            case "getMaxMinDateStamp":
                getVA().setStrs(getDE().getMonthList((Long[]) data));
               getUI().setNewData(getVA().getStrs());
                break;
        }
    }
}
