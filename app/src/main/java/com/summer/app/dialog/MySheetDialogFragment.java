package com.summer.app.dialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.summer.app.databinding.BottomsheetLayoutBinding;


/**
 *
 **/
public class MySheetDialogFragment extends MyDialogFragment{


    BottomsheetLayoutBinding mBinding;

    @Override
    public Animation onShowAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        return translateAnimation;
    }

    @Override
    public Animation onDismissAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        return translateAnimation;
    }

    @Override
    public void addDialogView(LayoutInflater inflater,ViewGroup viewGroup) {
        //居下显示
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewGroup.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_IN_PARENT,0);
        viewGroup.setLayoutParams(params);
        mBinding = BottomsheetLayoutBinding.inflate(inflater,viewGroup,true);
    }

    @Override
    public ViewGroup getDialogContentView() {
        return mBinding.bottomSheetContent;
    }


}
