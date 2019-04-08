package com.summer.record.ui.setting;

import android.os.Bundle;

import com.summer.record.R;
import com.summer.record.ui.pictures.picture.FragPicture;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XFragment;

import androidx.annotation.Nullable;

/**
 * 设置首页
 */
public class FragSettingHome extends XFragment<SettingHomeUI, DE, VA> {

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
}
