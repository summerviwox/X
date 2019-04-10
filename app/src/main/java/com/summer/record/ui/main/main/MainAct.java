package com.summer.record.ui.main.main;

import android.os.Bundle;

import com.blankj.utilcode.util.PermissionUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.summer.record.BuildConfig;
import com.summer.x.base.ui.XActivity;
import com.summer.x.util.PermissionUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import me.yokeyword.fragmentation.Fragmentation;

public class MainAct extends XActivity<MainUI,MainDE,MainVA> implements FinishI {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUI().ImmersionBar(getActivity());
        if(!new PermissionUtil().requestPermissions(getActivity(),getVA().getPermissions())){
            return;
        }
        init();
    }

    public void init(){
        loadMultipleRootFragment(getUI().getRootId(),0,getVA().getPageFragments());
        getUI().initBottomMenu(this);
    }

    @Override
    public void onFinished(Object o) {
        showHideFragment(getVA().getPageFragments()[(Integer)o],getVA().getPageFragments()[getVA().getLastindex()]);
        getVA().setLastindex((Integer)o);
    }


    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(new PermissionUtil().onRequestPermissionsResult(getActivity(),requestCode,grantResults)){
            init();
        }
    }
}
