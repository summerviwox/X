package com.summer.app.test.test1;

import com.bumptech.glide.Glide;
import com.summer.app.databinding.ActivityTest1Binding;
import com.summer.x.GlideApp;
import com.summer.x.base.ui.DA;
import com.summer.x.base.ui.UI;

/**
 * tangjie 2023/2/14 16:04
 **/
public class Test1UI extends UI<ActivityTest1Binding> {

    @Override
    public void initUI(DA va) {
        super.initUI(va);
        Glide.with(getXActivity()).load("").into(getUI().image);
    }
}
