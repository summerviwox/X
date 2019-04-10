package com.summer.record.ui.loading;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.blankj.utilcode.util.LogUtils;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.ui.XFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据加载动画
 */
public class LoadingFrag extends XFragment<LoadingUI,LoadingDE,LoadingVA>  {

    @Getter
    @Setter
    OnFinishI onFinishI;

    public static LoadingFrag getInstance(){
        LoadingFrag loadingFrag = new LoadingFrag();
        return loadingFrag;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        onFinishI.onFinished(this);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return null;
    }
}
