package com.summerviwox.knowledge.ui.main.view.treeview;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.summerviwox.knowledge.R;

/**
 * summer 2022/8/16 11:18
 **/
public class TreeItemView extends ConstraintLayout {

    public TreeItemView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public TreeItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void init(@NonNull Context context, @Nullable AttributeSet attrs){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(6);
        gradientDrawable.setColor(getResources().getColor(R.color.gray));
        setBackground(gradientDrawable);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getMode(widthMeasureSpec)),
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec)/3,MeasureSpec.getMode(widthMeasureSpec)));
    }
}
