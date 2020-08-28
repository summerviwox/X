package com.summer.app.fragment;

import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.R;

import java.util.UUID;

public class FragementB extends Fragment implements View.OnClickListener {


    View view;

    String tag = UUID.randomUUID().toString();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fragment_b,container,false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text:
                LogUtils.e(getClass().getSimpleName()+"--"+tag + "backstack:"+getChildFragmentManager().getBackStackEntryCount()+"fragments:"+getChildFragmentManager().getFragments().size());
                 getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.h_fragment_enter,R.anim.h_fragment_exit,R.anim.h_fragment_enter,R.anim.h_fragment_exit).add(R.id.fragmentb_root,new FragementA()).addToBackStack(tag).commit();
                break;
        }
    }
}
