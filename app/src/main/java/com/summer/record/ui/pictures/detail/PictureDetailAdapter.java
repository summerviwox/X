package com.summer.record.ui.pictures.detail;

import android.content.Context;
import android.provider.MediaStore;

import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.picture.FragPicture;
import com.summer.record.ui.play.PlayCT;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PictureDetailAdapter extends FragmentStatePagerAdapter {

    private Context context;

    private  ArrayList<PictureB> datas;

    private HashMap<Integer, XFragment> fragmentHashMap = new HashMap<>();

    public PictureDetailAdapter(Context context, FragmentManager fm, ArrayList<PictureB> datas) {
        super(fm);
        this.context = context;
        this.datas = datas;
    }


    @Override
    public Fragment getItem(int position) {
        if(datas.get(position).getAtype().equals(PictureB.ATYPE_IMAGE)||datas.get(position).getAtype().equals(""+MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)){
            FragPicture fragPicture = FragPicture.getInstance(datas.get(position));
            fragmentHashMap.put(position,fragPicture);
            return fragPicture;
        }else{
            PlayCT playCT = PlayCT.getInstance(datas.get(position));
            fragmentHashMap.put(position,playCT);
            return playCT;
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    public HashMap<Integer, XFragment> getFragmentHashMap() {
        return fragmentHashMap;
    }

    public void setFragmentHashMap(HashMap<Integer, XFragment> fragmentHashMap) {
        this.fragmentHashMap = fragmentHashMap;
    }
}
