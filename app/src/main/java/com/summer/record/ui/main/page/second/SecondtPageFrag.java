package com.summer.record.ui.main.page.second;

import android.os.Bundle;

import com.summer.record.ui.albums.albums.FragAlbumHome;
import com.summer.record.ui.main.page.base.BasePageFrag;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;

import androidx.annotation.Nullable;

public class SecondtPageFrag extends BasePageFrag<SecondPageUI, DE, VA> {

    public static SecondtPageFrag getInstance(){
        SecondtPageFrag secondtPageFrag = new SecondtPageFrag();
        secondtPageFrag.setArguments(new Bundle());
        return  secondtPageFrag;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if(findChildFragment(FragAlbumHome.class)==null){
            loadRootFragment(getUI().getRootId(),FragAlbumHome.getInstance());
        }
    }
}
