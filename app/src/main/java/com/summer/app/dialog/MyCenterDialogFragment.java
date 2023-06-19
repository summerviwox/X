package com.summer.app.dialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.summer.app.databinding.CenterdialogLayoutBinding;


/**
 *
 **/
public class MyCenterDialogFragment extends MyDialogFragment{

    CenterdialogLayoutBinding mBinding;


    @Override
    public Animation onShowAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0,
                1,
                0,
                1,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(300);
        return scaleAnimation;
    }

    @Override
    public Animation onDismissAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1,
                0,
                1,
                0,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(300);
        return scaleAnimation;
    }

    @Override
    public void addDialogView(LayoutInflater inflater,ViewGroup viewGroup) {
        mBinding = CenterdialogLayoutBinding.inflate(inflater,viewGroup,true);
    }

    @Override
    public ViewGroup getDialogContentView() {
        return mBinding.content;
    }
}
