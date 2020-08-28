package com.summer.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.R;

import org.w3c.dom.Text;

import java.util.UUID;

public class FragementA extends Fragment implements View.OnClickListener {


    View view;

    String tag = UUID.randomUUID().toString();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.fragment_a,container,false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.text);
        textView.setText("text is :" + MyFragmentActivity.count++);
        textView.setOnClickListener(this);
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
                getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.h_fragment_enter,R.anim.h_fragment_exit,R.anim.h_fragment_enter,R.anim.h_fragment_exit).add(R.id.fragmenta_root,new FragementB()).addToBackStack(tag).commit();
                break;
        }
    }


}
