package com.summer.record.ui.loading;

import com.summer.record.databinding.FragLoadingBinding;
import com.summer.x.base.ui.UI;

/**
 * 数据加载动画
 */
public class LoadingUI extends UI<FragLoadingBinding> {



    public void setText(String text){
        getUI().info.setText(text);
    }
}
