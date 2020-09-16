package com.summer.app.multiview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.github.florent37.viewanimator.ViewAnimator;
import com.summer.app.R;
import com.summer.x.util.HandleUtil;

public class AView extends RelativeLayout {


    public AView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(LayoutInflater.from(context).inflate(R.layout.fragment_a,null),new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        findViewById(R.id.text).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BView bView = new BView(context,null);
                ((MulitViewCT)context).getUI().getUI().root.addView(bView);
                ViewAnimator.animate(AView.this)
                        .alpha(1,1)
                        .translationX(0, -SizeUtils.dp2px(100))
                        .andAnimate(bView)
                        .alpha(1,1)
                        .translationX(getWidth(),0)
                        .duration(2000)
                        .start();

            }
        });

    }
}
