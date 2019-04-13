package com.summer.record.ui.main.main;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.summer.record.BuildConfig;
import com.summer.record.R;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;
import com.summer.x.util.PermissionUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import butterknife.OnClick;
import butterknife.Optional;
import me.yokeyword.fragmentation.Fragmentation;

public class MainAct extends XActivity<MainUI,MainDE,MainVA>  {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUI().ImmersionBar(getActivity());
        if(!new PermissionUtil().requestPermissions(getActivity(),getVA().getPermissions())){
            return;
        }
        init();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getUI().initGlobalMenu(getActivity(),this);
    }

    public void init(){
        loadMultipleRootFragment(getUI().getRootId(),0,getVA().getPageFragments());
        getUI().initBottomMenu(new FinishI() {
            @Override
            public void onFinished(Object o) {
                showHideFragment(getVA().getPageFragments()[(Integer)o],getVA().getPageFragments()[getVA().getLastindex()]);
                getVA().setLastindex((Integer)o);
            }
        });
    }


    public void onClick(View v) {
        super.onClick(v);
        if(getVA().getPageFragments()[getVA().getLastindex()].getTopChildFragment() instanceof View.OnClickListener){
            View.OnClickListener onClickListener = (View.OnClickListener) getVA().getPageFragments()[getVA().getLastindex()].getTopChildFragment();
            onClickListener.onClick(v);
        }
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
