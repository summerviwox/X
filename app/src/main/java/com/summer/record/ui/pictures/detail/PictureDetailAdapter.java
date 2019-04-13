package com.summer.record.ui.pictures.detail;

import android.content.Context;

import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.picture.FragPicture;
import com.summer.record.ui.play.PlayCT;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PictureDetailAdapter extends FragmentStatePagerAdapter {

    private Context context;

    private  ArrayList<PictureB> datas;

    public PictureDetailAdapter(Context context, FragmentManager fm, ArrayList<PictureB> datas) {
        super(fm);
        this.context = context;
        this.datas = datas;
    }


    @Override
    public Fragment getItem(int position) {
        if(datas.get(position).getAtype().equals(PictureB.ATYPE_IMAGE)){
            return FragPicture.getInstance(datas.get(position));
        }else{
            return PlayCT.getInstance(datas.get(position));
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
