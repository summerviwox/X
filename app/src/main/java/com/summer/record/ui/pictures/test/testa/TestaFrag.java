package com.summer.record.ui.pictures.test.testa;

import android.os.Bundle;
import android.view.View;

import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.de.PicturesDE;
import com.summer.record.ui.pictures.home.PictureHomeUI;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XFragment;
import com.summer.x.util.HandleUtil;

import java.util.ArrayList;
import java.util.Collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.OnClick;

public class TestaFrag extends XFragment2<PictureHomeUI, PicturesDE, VA> {


    ArrayList<PictureB> pictureBS = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUI().initRecord(getActivity(),pictureBS,null,null);
        getDE().getDataFromDBByTime(getActivity(), new Long[]{0l, System.currentTimeMillis()}, new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case END:
                        HandleUtil.getInstance().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pictureBS.addAll((Collection<? extends PictureB>) data);
                                getUI().refreshRecord(getActivity(), pictureBS);
                            }
                        }, 500);
                        break;
                }
            }
        });
    }
}
