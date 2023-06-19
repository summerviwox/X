package com.summer.app.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gyf.barlibrary.ImmersionBar;
import com.summer.app.R;
import com.summer.app.databinding.BillDialogFragmentLayoutBinding;


/**
 *
 **/
public abstract class MyDialogFragment extends DialogFragment implements MyDialogConfigInf{

    public static final String TAG = "MyDialogFragment";
    protected BillDialogFragmentLayoutBinding mBinding;
    /**
     * 记录弹框动画状态 防止在动画中点击背景移除对话框对话框重新加载的bug
     */
    protected Boolean isDialogAnimating = false;
    protected int duration = 300;
    protected View mContentView;
    protected Fragment mFragment;

    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag, View contentView) {
        mContentView = contentView;
        return super.show(transaction, tag);
    }
    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag, Fragment fragment) {
        mFragment = fragment;
        return super.show(transaction, tag);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.style_base_dialogfragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = BillDialogFragmentLayoutBinding.inflate(inflater,container,false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(true);
        mBinding.content.setVisibility(View.INVISIBLE);
        addDialogView(LayoutInflater.from(getContext()),mBinding.content);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.mask.setOnClickListener(v -> cancelDialog());
        //ImmersionBar.with(this).statusBarDarkFont(false).init();
        //mBinding.mask.setPadding(0, DeviceInfoUtils.getStatusBarHeight(getContext()), 0, 0);
        if(null!=getDialogContentView()){
            if(null!=mContentView){
                getDialogContentView().removeAllViews();
                getDialogContentView().addView(mContentView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }else if(null!=mFragment){
                getDialogContentView().removeAllViews();
                getChildFragmentManager().beginTransaction().replace(getDialogContentView().getId(),mFragment,mFragment.getClass().getName()).commitNow();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.dimAmount = 0.0f;
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                window.setLayout(width, height);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.content.post(() -> {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 0.6f);
            alphaAnimation.setDuration(duration);
            alphaAnimation.setFillAfter(true);
            mBinding.mask.startAnimation(alphaAnimation);
            mBinding.mask.setVisibility(View.VISIBLE);

            Animation animation = onShowAnim();
            if(null!=animation){
                mBinding.content.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        isDialogAnimating = true;
                        mBinding.content.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isDialogAnimating = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        isDialogAnimating = true;
                    }
                });
            }
        });
    }


    protected void cancelDialog() {
        if (isDialogAnimating) {
            return;
        }
        Animation animation = onDismissAnim();
        if(null!=animation){
            mBinding.content.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isDialogAnimating = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isDialogAnimating = false;
                    dismiss();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    isDialogAnimating = true;
                }
            });
        }


        AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        mBinding.mask.startAnimation(alphaAnimation);
        mBinding.mask.setVisibility(View.INVISIBLE);
    }

}
