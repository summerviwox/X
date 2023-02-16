package com.summer.app.multiview;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.summer.app.databinding.ActivityMultiviewBinding;
import com.summer.x.base.ui.DA;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;


public class MulitViewUI extends UI<ActivityMultiviewBinding, DA> {


    public MulitViewUI(@NonNull XActivity xActivity, @Nullable XFragment xFragment, @Nullable ViewGroup viewGroup) {
        super(xActivity, xFragment, viewGroup);
    }
}