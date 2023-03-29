package com.summer.app.test1;

import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.summer.app.R;
import com.summer.app.databinding.ActivityTest1Binding;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;

import java.util.Random;

/**
 * tangjie 2023/3/1 15:39
 **/
public class Test1UI extends UI<ActivityTest1Binding,Test1DA> {

    public Test1UI(@NonNull XActivity xActivity, @Nullable XFragment xFragment, @Nullable ViewGroup viewGroup) {
        super(xActivity, xFragment, viewGroup);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        xActivity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        getDA().dy =  displayMetrics.heightPixels;
        ImageView imageView = getUI().myscrollview.getmBinding().secondview;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.height = getDA().dy;;
        imageView.setLayoutParams(layoutParams);
        Glide.with(xActivity).load("https://img0.baidu.com/it/u=1568170448,654840322&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889")
                .into(imageView);
    }

    public void smoothScrollTo(){
        getUI().myscrollview.smoothScrollTo(0,0,0,getDA().dy);
    }

    public void smoothScrollBack(){
        getUI().myscrollview.smoothScrollTo(0,getDA().dy,0,-getDA().dy);
    }
}
