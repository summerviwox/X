package com.summer.app.a;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.gyf.barlibrary.ImmersionBar;
import com.summer.app.R;


/**
 * 通用问号提示弹框
 */
public class PromptDialog extends DialogFragment {
    private static final String TITLE = "title";
    private static final String CONTENT = "content";

    private String title;
    private String content;
    private View mViewMask;
    private ConstraintLayout mDialogRootView;
    /**
     * 记录弹框动画状态 防止在动画中点击背景移除对话框对话框重新加载的bug
     */
    private Boolean isDialogAnimating = false;

    private PromptDialog() {
    }

    public static PromptDialog newInstance(@NonNull String title, String content) {
        PromptDialog businessDetailDialog = new PromptDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(CONTENT, content);
        businessDetailDialog.setArguments(bundle);
        return businessDetailDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.plan_dialog_style_anima);
        Bundle arguments = getArguments();
        if (arguments == null) {
            dismiss();
            return;
        }
        title = arguments.getString(TITLE);
        content = arguments.getString(CONTENT);
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(500);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isDialogAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isDialogAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 0.6f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        mDialogRootView.startAnimation(translateAnimation);
        mDialogRootView.setVisibility(View.VISIBLE);
        mViewMask.startAnimation(alphaAnimation);
        mViewMask.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }

    private void cancelDialog() {
        if (isDialogAnimating) {
            return;
        }

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(500);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isDialogAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
                isDialogAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mDialogRootView.startAnimation(translateAnimation);
        mDialogRootView.setVisibility(View.INVISIBLE);
        mViewMask.startAnimation(alphaAnimation);
        mViewMask.setVisibility(View.INVISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getDialog() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.gravity = Gravity.BOTTOM;
            getDialog().getWindow().setAttributes(params);
        }
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(true);
        return inflater.inflate(R.layout.plan_tip_info_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvContent = view.findViewById(R.id.tv_content);
        mViewMask = view.findViewById(R.id.alpha_mask);
        tvTitle.setText(title);
        tvContent.setText(content);
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        view.findViewById(R.id.btn_know).setOnClickListener(v -> cancelDialog());
        view.findViewById(R.id.img__sub_business_dialog_close).setVisibility(View.GONE);
        view.findViewById(R.id.img__sub_business_dialog_close).setOnClickListener(v -> cancelDialog());
        mDialogRootView = view.findViewById(R.id.constr_dialog_root);
        mViewMask.setOnClickListener(v -> cancelDialog());
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
}
