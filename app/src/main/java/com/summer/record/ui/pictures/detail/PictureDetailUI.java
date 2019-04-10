package com.summer.record.ui.pictures.detail;

import android.content.Context;

import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.CtPicturedetailBinding;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.XActivity;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;


public class PictureDetailUI extends UI<CtPicturedetailBinding> {

    private PictureDetailAdapter pictureDetailAdapter;

    public void init(Context context,FragmentManager fm, ArrayList<PictureB> datas, int pos){
        pictureDetailAdapter = new PictureDetailAdapter(context,fm,datas);
        getUI().viewpager.setAdapter(pictureDetailAdapter);
        getUI().viewpager.setCurrentItem(pos);
    }

}
