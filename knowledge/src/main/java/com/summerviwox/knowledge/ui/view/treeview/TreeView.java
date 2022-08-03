package com.summerviwox.knowledge.ui.view.treeview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * summer 2022/8/2 17:43
 **/
public class TreeView extends ViewGroup {

    int gap = 10;

    public TreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(List<String> texts){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //setMeasuredDimension(MeasureSpec.makeMeasureSpec(widthMode,widthSize),MeasureSpec.makeMeasureSpec(heightMode,widthSize));
        //setMeasuredDimension(100,100);
        //setMeasuredDimension(getMeasuredWidth(),getMeasuredWidth());
        int width=0,height = 0;
        for(int i=0;i<getChildCount();i++){
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
            Log.e(""+getChildAt(i).getMeasuredWidth(),""+getChildAt(i).getMeasuredHeight());
            width+=getChildAt(i).getMeasuredWidth();
            height+=getChildAt(i).getMeasuredHeight()+gap;
        }
        setMeasuredDimension(getMeasuredWidth(),height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        for(int i=0;i<getChildCount();i++){
            getChildAt(i).layout(0,top,getChildAt(i).getMeasuredWidth(),top+getChildAt(i).getMeasuredHeight());
            top+=getChildAt(i).getMeasuredHeight()+gap;
        }
    }
}
