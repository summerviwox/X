package com.summer.app.main;

import android.os.Bundle;

import com.summer.app.R;
import com.summer.app.databinding.ActMainBinding;
import com.summer.app.fragment.FragMain;
import com.summer.x.base.ui.XActivity;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

public class MainAct extends XActivity<MainUI,MainDE,MainVA>{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRootFragment(R.id.mainroot,new FragMain());
        //DataBindingUtil.
    }
}
