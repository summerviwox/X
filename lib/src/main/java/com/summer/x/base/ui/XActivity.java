package com.summer.x.base.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.summer.x.base.i.OnProgressI;

import butterknife.ButterKnife;

public class XActivity<A extends UI,B extends DE,C extends DA> extends AppCompatActivity implements CT<A,B,C>,View.OnClickListener, OnProgressI {

    private ReflectUtil reflectUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reflectUtil = new ReflectUtil<A,B,C>(this);
        reflectUtil.init(this,null,null);
        setContentView(getUI().getUI().getRoot());
        ButterKnife.bind(this,getUI().getUI().getRoot());
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public void onProgress(String tag, int status, Object data) {

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
}
