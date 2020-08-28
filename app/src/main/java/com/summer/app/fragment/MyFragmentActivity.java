package com.summer.app.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.summer.app.R;

public class MyFragmentActivity extends FragmentActivity {

    public static int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfragmentactivity);
        getSupportFragmentManager().beginTransaction().add(R.id.roota,new FragementA(),"A").commit();
    }

    @Override
    public void onBackPressed() {
        getTopFragment(getSupportFragmentManager().findFragmentByTag("A")).getParentFragmentManager().popBackStack();
    }

    public Fragment getTopFragment(Fragment fragment){
        if(fragment.getChildFragmentManager().getFragments().size()!=0){
            Fragment fragment1 = fragment.getChildFragmentManager().getFragments().get(0);
            return getTopFragment(fragment1);
        }else{
            return fragment;
        }
    }

}
