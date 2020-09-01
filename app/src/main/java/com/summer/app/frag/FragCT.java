package com.summer.app.frag;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.ui.XFragment;
import com.summer.x.util.HandleUtil;

public class FragCT extends XFragment<FragUI,FragDE,FragVA> {

    public static FragCT getInstance(){
        FragCT fragCT = new FragCT();
        return  fragCT;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HandleUtil handleUtil = new HandleUtil();
//        handleUtil.refresh(this, 3000, new OnFinishI() {
//            @Override
//            public void onFinished(Object o) {
//                LogUtils.e("fdfdfd");
//            }
//        });
        getUI().getUI().text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(FragCT.this).commit();
            }
        });
    }

}