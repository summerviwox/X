package com.summer.record.ui.pictures.test;

import android.os.Bundle;

import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.de.PicturesDE;
import com.summer.record.ui.pictures.home.PictureHomeUI;
import com.summer.record.ui.pictures.test.testa.TestaFrag;
import com.summer.record.ui.pictures.test.testa.XActivity2;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;
import com.summer.x.util.HandleUtil;

import java.util.ArrayList;
import java.util.Collection;

import androidx.annotation.Nullable;

public class TestFrag extends XActivity2<PictureHomeUI, PicturesDE,TestVA> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportFragmentManager().beginTransaction().add(R.id.actcontainer,new TestaFrag()).commit();
        getUI().initRecord(getActivity(),getVA().getPictureBS(),null,null);
        getDE().getDataFromDBByTime(getActivity(), new Long[]{0l, System.currentTimeMillis()}, new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case END:
                        HandleUtil.getInstance().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getVA().getPictureBS().addAll((Collection<? extends PictureB>) data);
                                getUI().refreshRecord(getActivity(), getVA().getPictureBS());
                            }
                        }, 500);
                        break;
                }
            }
        });
    }
}
