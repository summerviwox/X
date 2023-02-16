package com.summer.x.base.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.summer.x.base.i.OnProgressI;

import butterknife.ButterKnife;

public class XView<A extends UI,B extends DE,C extends DA> extends FrameLayout implements CT<A,B,C>, View.OnClickListener, OnProgressI {

    private ReflectUtil reflectUtil;

    public XView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        reflectUtil = new ReflectUtil<A,B,C>((XActivity) context);
        reflectUtil.init(getXActivity(),null,this);
        addView(getUI().getUI().getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this);
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
