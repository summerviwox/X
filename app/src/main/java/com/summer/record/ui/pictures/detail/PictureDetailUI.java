package com.summer.record.ui.pictures.detail;

import android.content.Context;

import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.CtPicturedetailBinding;
import com.summer.record.ui.play.PlayCT;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.XActivity;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;


public class PictureDetailUI extends UI<CtPicturedetailBinding> {

    private PictureDetailAdapter pictureDetailAdapter;

    public void init(Context context, FragmentManager fm, ArrayList<PictureB> datas, int pos, ViewPager.OnPageChangeListener onPageChangeListener){
        pictureDetailAdapter = new PictureDetailAdapter(context,fm,datas);
        getUI().viewpager.setAdapter(pictureDetailAdapter);
        getUI().viewpager.setCurrentItem(pos);
        getUI().viewpager.addOnPageChangeListener(onPageChangeListener);
    }

    public PlayCT getPlayCT(){
        PlayCT playCT = null;
        if(pictureDetailAdapter!=null&&getUI()!=null&&pictureDetailAdapter.getItem(getUI().viewpager.getCurrentItem()) instanceof PlayCT){
            playCT = (PlayCT) pictureDetailAdapter.getItem(getUI().viewpager.getCurrentItem());
        }
        return playCT;
    }

}
