package com.summer.app.main;

import com.summer.x.base.i.OnProgressI;

/**
 * tangjie 2023/1/18 17:36
 **/
public interface MainDEChatInf {

    void getTextData();
    void getVideoData(OnProgressI onProgressI);
    void getImageData();
}
