package com.summer.x.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.summer.x.R;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;


public class SimpleTitleView extends RelativeLayout {


    Context context;

    ImageView leftImg;

    TextView midText;

    TextView rightText;

    ImageView rightImg;

    XFragment xFragment;


    public SimpleTitleView(Context context) {
        super(context);
        init(context,null);
    }

    public XFragment getxFragment() {
        return xFragment;
    }

    public void setxFragment(XFragment xFragment) {
        this.xFragment = xFragment;
    }

    public SimpleTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context,@Nullable AttributeSet attrs){
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.item_title,this);
        leftImg = findViewById(R.id.leftimg);
        midText = findViewById(R.id.midtext);
        rightImg = findViewById(R.id.rightimg);
        rightText = findViewById(R.id.righttext);
        if(attrs!=null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.title);
            String midtextstr = typedArray.getString(R.styleable.title_midtext);
            midText.setText(midtextstr);
            String righttextstr = typedArray.getString(R.styleable.title_righttext);
            rightText.setText(righttextstr);
            int leftImgId = typedArray.getResourceId(R.styleable.title_backimg,-1);
            if(leftImgId!=-1){
                leftImg.setVisibility(View.VISIBLE);
                leftImg.setImageResource(leftImgId);
                leftImg.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!v.isEnabled()){
                            return;
                        }
                        if(getxFragment()!=null){
                            xFragment.onBackPressedSupport();
                            return;
                        }
                        if(getContext() instanceof XActivity){
                            XActivity xActivity = (XActivity) getContext();
                            xActivity.onBackPressedSupport();
                        }
                        v.setEnabled(false);
                    }
                });
            }else{
                leftImg.setVisibility(View.GONE);
            }
            int rightImgId = typedArray.getResourceId(R.styleable.title_rightimg,-1);
            if(rightImgId!=-1){
                rightImg.setVisibility(View.VISIBLE);
                rightImg.setImageResource(rightImgId);
            }else{
                rightImg.setVisibility(View.GONE);
            }
            typedArray.recycle();
        }
    }

    public ImageView getLeftImg() {
        return leftImg;
    }

    public TextView getMidText() {
        return midText;
    }

    public TextView getRightText() {
        return rightText;
    }

    public ImageView getRightImg() {
        return rightImg;
    }
}
