package com.summer.x.base.ui;

import androidx.appcompat.app.AppCompatActivity;

/**
 * tangjie 2023/2/14 17:28
 **/
public interface CT<A extends UI,B extends DE,C extends DA>{

    public A getUI();

    public B getDE();

    public C getVA();

    public AppCompatActivity getXActivity();

}
