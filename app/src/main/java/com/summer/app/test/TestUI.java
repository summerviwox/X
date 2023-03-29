package com.summer.app.test;

import android.util.DisplayMetrics;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.summer.app.databinding.ActivityTest1Binding;
import com.summer.app.databinding.ActivityTestBinding;
import com.summer.app.image.MyBitmapTrans;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;

/**
 * tangjie 2023/2/15 10:36
 **/
public class TestUI extends UI<ActivityTestBinding,TestDA> {


    public TestUI(@NonNull XActivity xActivity, @Nullable XFragment xFragment, @Nullable ViewGroup viewGroup) {
        super(xActivity, xFragment, viewGroup);
        //Glide.with(getXActivity()).load(getDA().imgUrl).apply(new RequestOptions().transform(new MyBitmapTrans())).into(getUI().image);
        //Glide.with(getXActivity()).load(getDA().imgUrl).apply(new RequestOptions().transform(new RoundedCorners(50))).into(getUI().image);
    }

    public void showDialog(){

    }

    public void initMidUI(){

    }

    public void setUI1(){

    }

    public void setUI2(){

    }

    public void setUI3(){

    }

    public void setUI4(){

    }

    public void setUI5(){

    }
}
