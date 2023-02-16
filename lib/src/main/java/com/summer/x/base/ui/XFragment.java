package com.summer.x.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.summer.x.base.i.OnProgressI;

import butterknife.ButterKnife;

public class XFragment<A extends UI,B extends DE,C extends DA> extends Fragment implements CT<A,B,C>, View.OnClickListener, OnProgressI {

    private ReflectUtil reflectUtil;

    public XFragment(){
        if(getArguments()==null){
            setArguments(new Bundle());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        reflectUtil = new ReflectUtil<A,B,C>((AppCompatActivity) context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reflectUtil.init(getXActivity(),this,container);
        View view = getUI().getUI().getRoot();
        ButterKnife.bind(this,getUI().getUI().getRoot());
        return view;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public A getUI() {
        return (A) reflectUtil.ope.getUI();
    }

    @Override
    public B getDE() {
        return (B) reflectUtil.ope.getDE();
    }

    @Override
    public C getVA() {
        return (C) reflectUtil.ope.getVA();
    }

    @Override
    public AppCompatActivity getXActivity() {
        return reflectUtil.activity;
    }

    @Override
    public void onProgress(String tag, int status, Object data) {

    }
}
