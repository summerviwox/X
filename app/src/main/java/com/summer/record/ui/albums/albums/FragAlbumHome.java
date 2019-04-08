package com.summer.record.ui.albums.albums;

import android.os.Bundle;

import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.XFragment;

import androidx.annotation.Nullable;

/**
 * 照片首页
 */
public class FragAlbumHome extends XFragment<AlbumHomeUI, DE, AlbumHomeVA> {

    public static FragAlbumHome getInstance(){
        FragAlbumHome fragAlbumHome = new FragAlbumHome();
        fragAlbumHome.setArguments(new Bundle());
        return fragAlbumHome;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUI().initRecord(getAct());
        getUI().refreshRecord(getVA().getRecords());
    }
}
