package com.summer.app.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.summer.app.R;
import com.summer.app.model.Position;

/**
 * tangjie 2023/1/16 16:37
 * 自定义下拉控件
 **/
public class PullView extends ViewGroup {

    public static final String TAG = "PullView";

    /**
     * MotionEvent.ACTION_DOWN,MotionEvent.ACTION_MOVE,MotionEvent.ACTION_UP 对应的View位置
     */
    private Position[] mPositions = new Position[]{
            new Position(),
            new Position(),
            new Position(),
    };
    /**
     * 上次滑动结束Y偏移量
     */
    private int lastDeltaY = 0;

    public static int MAX_DELTA_Y = 0;

    public PullView(Context context) {
        super(context);
        init(context,null,0,0);
    }

    public PullView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0,0);
    }

    public PullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr,0);
    }



    public void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PullView, defStyleAttr,0);
        typedArray.recycle();
        MAX_DELTA_Y = SizeUtils.dp2px(50);
        //LogUtils.e(MAX_DELTA_Y);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(getChildCount()!=1){
            return;
        }
        //ScrollView
        //measureChild(widthMeasureSpec,MeasureSpec.makeMeasureSpec(MeasureSpec.,MeasureSpec.EXACTLY));
        int heightSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec),MeasureSpec.UNSPECIFIED);
        measureChildren(widthMeasureSpec, heightSpec);
        //getChildAt(0).measure(widthMeasureSpec,heightMeasureSpec);
        //int height = getChildAt(0).getMeasuredHeight();
        //setMeasuredDimension(getMeasuredWidth(),height);
        LogUtils.e(getChildAt(0).getMeasuredHeight());

    }

/*    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec,int parentHeightMeasureSpec) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, lp.leftMargin + lp.rightMargin, lp.width);
        int size = Math.max(0, MeasureSpec.getSize(parentHeightMeasureSpec));
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(size,MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed,int parentHeightMeasureSpec, int heightUsed) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,lp.leftMargin + lp.rightMargin+ widthUsed, lp.width);
        int size = Math.max(0, MeasureSpec.getSize(parentHeightMeasureSpec) - (lp.topMargin + lp.bottomMargin +heightUsed));
        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(size,MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }*/

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(getChildCount()!=1){
            return;
        }
        getChildAt(0).layout(0,0,getChildAt(0).getMeasuredWidth(),getChildAt(0).getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPositions[0].set((int) event.getX(), (int) event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                mPositions[1].set((int) event.getX(), (int) event.getY());
                move();
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mPositions[2].set((int) event.getX(), (int) event.getY());
                down();
                stop();
                return true;
        }
        return false;
    }

    private void move(){
        if(getChildCount()!=1){
            return;
        }
        int deltaY = lastDeltaY+Position.getDeltaY(mPositions[0],mPositions[1]);
        if(deltaY>=MAX_DELTA_Y){
            return;
        }
        int bottom = getChildAt(0).getMeasuredHeight()+deltaY;
        LogUtils.e(getChildAt(0).getMeasuredHeight(),getMeasuredHeight(),deltaY);
        if(bottom<=getMeasuredHeight()){
            return;
        }
        getChildAt(0).layout(0,deltaY,getChildAt(0).getMeasuredWidth(),getChildAt(0).getMeasuredHeight()+deltaY);
    }

    private void stop(){
        if(getChildCount()!=1){
            return;
        }
        lastDeltaY = getChildAt(0).getTop();
    }


    private void reset(){
        int deltaY = lastDeltaY+Position.getDeltaY(mPositions[0],mPositions[1]);
        if(deltaY>=MAX_DELTA_Y){
            getChildAt(0).layout(0,0,getChildAt(0).getMeasuredWidth(),getChildAt(0).getMeasuredHeight());
            return;
        }

    }

    private void down(){
        int deltaY = lastDeltaY+Position.getDeltaY(mPositions[0],mPositions[1]);
        if(deltaY>=MAX_DELTA_Y){
            animDown(getChildAt(0),deltaY,getMeasuredHeight());
            //getChildAt(0).layout(0,getMeasuredHeight(),getChildAt(0).getMeasuredWidth(),getMeasuredHeight()+getChildAt(0).getMeasuredHeight());
            return;
        }

    }

    public void animDown(View view,float start,float end){
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(start,end);
        valueAnimator.setDuration(400);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int)((float) animation.getAnimatedValue());
                view.layout(0,value,view.getMeasuredWidth(),view.getMeasuredHeight()+value);
            }
        });
        valueAnimator.start();
    }
}
