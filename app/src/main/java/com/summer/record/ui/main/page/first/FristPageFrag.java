package com.summer.record.ui.main.page.first;

import android.os.Bundle;

import com.summer.record.ui.main.page.base.BasePageFrag;
import com.summer.record.ui.pictures.pictures.FragPictureHome;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XFragment;

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
        if(findChildFragment(FragPictureHome.class)==null){
            loadRootFragment(getUI().getRootId(),FragPictureHome.getInstance());
        }
    }
}
