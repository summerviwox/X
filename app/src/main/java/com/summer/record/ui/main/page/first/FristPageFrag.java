package com.summer.record.ui.main.page.first;

import android.os.Bundle;

import com.summer.record.ui.main.page.base.BasePageFrag;
import com.summer.record.ui.pictures.home.PictureHomeCT;
import com.summer.record.ui.pictures.home.PictureMonthAdapter;
import com.summer.record.ui.pictures.mothpicture.MothPictureCT;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;

import androidx.annotation.Nullable;

public class FristPageFrag extends BasePageFrag<FirstPageUI, DE, VA> {


    public static FristPageFrag getInstance(){
        FristPageFrag fristPageFrag = new FristPageFrag();
        fristPageFrag.setArguments(new Bundle());
        return  fristPageFrag;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if(findChildFragment(MothPictureCT.class)==null){
            loadRootFragment(getUI().getRootId(), MothPictureCT.getInstance());
        }
    }
}
