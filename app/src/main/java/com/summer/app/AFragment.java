package com.summer.app;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.fragment.BActivity;

public class AFragment extends Fragment implements View.OnClickListener {

    private BlankViewModel mViewModel;

    View v;

    public static AFragment newInstance() {
        return new AFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("BlankFragment onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LogUtils.e("BlankFragment onCreateView" +( v==null));
        if(v!=null){
            return v;
        }
        v = inflater.inflate(R.layout.afragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setRetainInstance(true);
        mViewModel = new ViewModelProvider(this).get(BlankViewModel.class);
        LogUtils.e("BlankFragment onActivityCreated");
        getView().findViewById(R.id.text).setOnClickListener(this);
        getView().findViewById(R.id.activitytext).setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("BlankFragment onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("BlankFragment onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.e("BlankFragment onDestroyView");
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text:
                Navigation.findNavController(v).navigate(R.id.action_blankFragment_to_blankFragment2);
                break;
            case R.id.activitytext:
                getActivity().startActivity(new Intent(getContext(), BActivity.class));
                getActivity().overridePendingTransition(R.anim.h_fragment_enter,R.anim.h_fragment_exit);
                break;
        }
    }
}