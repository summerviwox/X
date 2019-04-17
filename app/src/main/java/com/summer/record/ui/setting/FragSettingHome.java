package com.summer.record.ui.setting;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.summer.record.R;
import com.summer.record.constant.NetConstant;
import com.summer.record.ui.main.main.MainAct;
import com.summer.record.ui.menu.MenuFrag;
import com.summer.record.ui.pictures.home.PictureHomeCT;
import com.summer.record.ui.pictures.picture.FragPicture;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XFragment;

import androidx.annotation.Nullable;
import butterknife.OnClick;

/**
 * 设置首页
 */
public class FragSettingHome extends XFragment<SettingHomeUI, DE, SettingHomeVA> {

    public static FragSettingHome getInstance(){
        FragSettingHome fragSettingHome = new FragSettingHome();
        fragSettingHome.setArguments(new Bundle());
        return fragSettingHome;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //getChildFragmentManager().beginTransaction().add(R.id.root_setting, FragPicture.getInstance()).commit();
        //start(FragPicture.getInstance());
    }

    @OnClick({R.id.imagenum})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.imagenum:
                extraTransaction().startForResultDontHideSelf(MenuFrag.getInstance(getVA().getNums()),0);
                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if(data!=null){
            int index = data.getInt("index");
            SPUtils.getInstance().put("imagenum",index);
            NetConstant.SPANCOUNT = Integer.parseInt(getVA().getNums().get(index));
            PictureHomeCT pictureHomeCT = (PictureHomeCT) ((MainAct)getAct()).getVA().getPageFragments()[0].findChildFragment(PictureHomeCT.class);
            if(pictureHomeCT!=null){
                pictureHomeCT.initData();
            }
        }
    }
}
