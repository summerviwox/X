package com.summer.app.main;

import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;

/**
 * tangjie 2023/1/18 16:28
 **/
public class MainDE extends DE<MainDA> implements MainDEChatInf{


    public MainDE(MainDA da) {
        super(da);
    }

    @Override
    public void getTextData() {

    }

    @Override
    public void getVideoData(OnProgressI onProgressI) {
        onProgressI.onProgress("",OnProgressI.END,"");
    }

    @Override
    public void getImageData() {

    }
}
