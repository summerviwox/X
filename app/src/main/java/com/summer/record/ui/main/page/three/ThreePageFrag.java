package com.summer.record.ui.main.page.three;

import android.os.Bundle;

import com.summer.record.ui.main.page.base.BasePageFrag;
import com.summer.record.ui.setting.FragSettingHome;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;

import androidx.annotation.Nullable;

public class ThreePageFrag extends BasePageFrag<ThreePageUI, DE, VA> {

    public static ThreePageFrag getInstance(){
        ThreePageFrag threePageFrag = new ThreePageFrag();
        threePageFrag.setArguments(new Bundle());
        return  threePageFrag;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if(findChildFragment(FragSettingHome.class)==null){
            loadRootFragment(getUI().getRootId(),FragSettingHome.getInstance());
        }
    }
}
