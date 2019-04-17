package com.summer.record.ui.main.main;

import android.Manifest;

import com.summer.record.ui.main.page.first.FristPageFrag;
import com.summer.record.ui.main.page.second.SecondtPageFrag;
import com.summer.record.ui.main.page.three.ThreePageFrag;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XFragment;

import lombok.Getter;
import lombok.Setter;

public class MainVA extends VA {

    @Getter
    private XFragment[] pageFragments;

    @Setter
    @Getter
    private int lastindex = 0;

    @Getter
    private String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.DISABLE_KEYGUARD
    };


    @Override
    public void initVA() {
        super.initVA();
        pageFragments = new XFragment[]{FristPageFrag.getInstance(), SecondtPageFrag.getInstance(), ThreePageFrag.getInstance()};
    }

    public XFragment getCurrentFrag(){
        return getPageFragments()[getLastindex()];
    }


}
