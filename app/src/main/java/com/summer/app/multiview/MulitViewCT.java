package com.summer.app.multiview;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;

public class MulitViewCT extends XActivity<MulitViewUI,MulitViewDE,MulitViewVA> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUI().getUI().root.addView(new AView(this,null));
    }

    @Override
    public void onBackPressedSupport() {
        getUI().getUI().root.onBackPress();
    }
}