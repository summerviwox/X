package com.summer.x.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.summer.x.R;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;
import com.summer.x.util.HandleUtil;


public class SimpleTitleView extends RelativeLayout {


    Context context;

    ImageView leftImg;

    TextView midText;

    TextView rightText;

    ImageView rightImg;

    XFragment xFragment;

    Handler handler = new Handler();


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
                        LogUtils.e("left on click");
                        v.setEnabled(false);
                        if(getContext() instanceof XActivity){
                            XActivity xActivity = (XActivity) getContext();
                            xActivity.getTopFragment().getSupportDelegate().pop();
                        }else{
                            Activity activity = (Activity) getContext();
                            activity.finish();
                        }
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v.setEnabled(true);
                            }
                        }, 1000);
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
