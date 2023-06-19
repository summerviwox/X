package com.summer.app.dialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 *
 **/
public interface MyDialogConfigInf {

    /**
     * 显示动画
     */
    Animation onShowAnim();
    /**
     * 隐藏动画
     */
    Animation onDismissAnim();

    void addDialogView(LayoutInflater inflater,ViewGroup viewGroup);

    ViewGroup getDialogContentView();


}
