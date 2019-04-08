package com.summer.record.ui.main.main;

import com.summer.record.ui.albums.albums.FragAlbumHome;
import com.summer.record.ui.main.page.first.FristPageFrag;
import com.summer.record.ui.main.page.second.SecondtPageFrag;
import com.summer.record.ui.main.page.three.ThreePageFrag;
import com.summer.record.ui.pictures.pictures.FragPictureHome;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XFragment;

import lombok.Getter;
import lombok.Setter;

public class MainVA extends VA {

    @Getter
    private XFragment[] pageFragments;

    @Setter
    @Getter
    private int lastindex = 0;

    @Override
    public void initVA() {
        super.initVA();
        pageFragments = new XFragment[]{FristPageFrag.getInstance(), SecondtPageFrag.getInstance(), ThreePageFrag.getInstance()};
    }
}
