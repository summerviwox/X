package com.summer.app.test1;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.summer.app.R;
import com.summer.app.databinding.LayoutMyscrollviewBinding;


/**
 * tangjie 2023/3/1 15:44
 **/
public class MyScrollView extends ConstraintLayout {

    private LayoutMyscrollviewBinding mBinding;
    private Scroller mScroller;
    private Handler mHandler = new Handler();


    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        mBinding = LayoutMyscrollviewBinding.inflate(LayoutInflater.from(context),this,true);
        mScroller = new Scroller(context);
    }

    public void smoothScrollTo(int startx,int starty,int x,int y){
        if(!mScroller.isFinished()){
            return;
        }
        mScroller.startScroll(startx,starty,x,y,500);
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mBinding.mainview.getLayoutParams();
            layoutParams.topMargin = mScroller.getCurrY();
            mBinding.mainview.setLayoutParams(layoutParams);
            postInvalidate();
        }
    }

    public LayoutMyscrollviewBinding getmBinding() {
        return mBinding;
    }
}
