package com.summer.record.ui.pictures.detail;

import android.os.Bundle;

import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.main.main.MainAct;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class PictureDetailCT extends XFragment<PictureDetailUI,PictureDetailDE,PictureDetailVA> {


    public static PictureDetailCT getInstance(ArrayList<PictureB> datas,int id){
        PictureDetailCT pictureDetailCT = new PictureDetailCT();
        pictureDetailCT.getVA().setDatas(pictureDetailCT.getDE().init(datas));
        pictureDetailCT.getVA().setPos(pictureDetailCT.getDE().getPos(pictureDetailCT.getVA().getDatas(),id));
        return pictureDetailCT;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        ((MainAct)getAct()).getUI().setBottomVisible(false);
        getUI().init(getActivity(),getFragmentManager(),getVA().getDatas(),getVA().getPos());
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator(R.anim.anim_picture_enter,R.anim.anim_picture_exit);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainAct)getAct()).getUI().setBottomVisible(true);
    }
}
