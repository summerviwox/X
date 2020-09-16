package com.summer.app.multiview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

public class NavigationView extends FrameLayout {


    public NavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void onBackPress(){
        if(getChildCount()!=0){
            if(getChildCount()>1){
                View a = getChildAt(getChildCount()-2);
                View b = getChildAt(getChildCount()-1);

                ViewAnimator.animate(b)
                        .alpha(1,1)
                        .translationX(0, b.getWidth())
                        .andAnimate(a)
                        .alpha(1,1)
                        .translationX(-SizeUtils.dp2px(100),0)
                        .duration(300)
                        .onStop(new AnimationListener.Stop() {
                            @Override
                            public void onStop() {
                                NavigationView.this.removeView(b);
                            }
                        })
                        .start();
            }
        }else{
            ((MulitViewCT)getContext()).finish();
        }
    }
}
