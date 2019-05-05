package com.summer.record.ui.pictures.detail;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.main.main.MainAct;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class PictureDetailCT extends XFragment<PictureDetailUI,PictureDetailDE,PictureDetailVA> implements ViewPager.OnPageChangeListener {


    public static PictureDetailCT getInstance(ArrayList<PictureB> datas,String localpath){
        PictureDetailCT pictureDetailCT = new PictureDetailCT();
        pictureDetailCT.getVA().setDatas(pictureDetailCT.getDE().init(datas));
        pictureDetailCT.getVA().setPos(pictureDetailCT.getDE().getPos(pictureDetailCT.getVA().getDatas(),localpath));
        return pictureDetailCT;
    }

    public static PictureDetailCT getInstance(ArrayList<PictureB> datas,int pos){
        PictureDetailCT pictureDetailCT = new PictureDetailCT();
        pictureDetailCT.getVA().setDatas(datas);
        pictureDetailCT.getVA().setPos(pos);
        return pictureDetailCT;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        ((MainAct)getAct()).getUI().setBottomVisible(false);
        getUI().init(getActivity(),getFragmentManager(),getVA().getDatas(),getVA().getPos(),this);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator(R.anim.anim_picture_enter,R.anim.anim_picture_exit);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainAct)getAct()).getUI().setBottomVisible(true);
        GSYVideoManager.releaseAllVideos();
        if(getUI().getPlayCT()!=null&&getUI().getPlayCT().getUI()!=null&&getUI().getPlayCT().getUI().getPlayer()!=null){
            if (getUI().getPlayCT().getUI().getOrientationUtils() != null)
                getUI().getPlayCT().getUI().getOrientationUtils().releaseListener();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getUI().getPlayCT()!=null&&getUI().getPlayCT().getUI()!=null&&getUI().getPlayCT().getUI().getPlayer()!=null){
            getUI().getPlayCT().getUI().getPlayer().onVideoPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getUI().getPlayCT()!=null&&getUI().getPlayCT().getUI()!=null&&getUI().getPlayCT().getUI().getPlayer()!=null){
            getUI().getPlayCT().getUI().getPlayer().onVideoResume();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(!getVA().getDatas().get(position).getAtype().equals(PictureB.ATYPE_VIDEO)){
            GSYVideoManager.releaseAllVideos();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(data==null||data.get("data")==null){
                    return;
                }
                getUI().getCurrentFrag().onFragmentResult(requestCode,resultCode,data);
                break;
        }
    }
}
