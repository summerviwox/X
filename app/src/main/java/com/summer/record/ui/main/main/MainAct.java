package com.summer.record.ui.main.main;

import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.summer.record.BuildConfig;
import com.summer.x.base.ui.XActivity;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import me.yokeyword.fragmentation.Fragmentation;

public class MainAct extends XActivity<MainUI,MainDE,MainVA> implements FinishI {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColor("#1296db").init();

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
}
