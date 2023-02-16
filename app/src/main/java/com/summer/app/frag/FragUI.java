package com.summer.app.frag;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.summer.x.base.ui.DA;
import com.summer.x.base.ui.UI;

import com.summer.app.databinding.CtFragBinding;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;

public class FragUI extends UI<CtFragBinding, DA> {


    public FragUI(@NonNull XActivity xActivity, @Nullable XFragment xFragment, @Nullable ViewGroup viewGroup) {
        super(xActivity, xFragment, viewGroup);
    }
}