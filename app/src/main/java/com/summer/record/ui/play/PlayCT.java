package com.summer.record.ui.play;

import android.os.Bundle;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.XFragment;

import androidx.annotation.Nullable;

public class PlayCT extends XFragment<PlayUI,PlayDE,PlayVA> {

    public static PlayCT getInstance(PictureB pictureB){
        PlayCT playCT = new PlayCT();
        playCT.setArguments(new Bundle());
        playCT.getVA().setPictureB(pictureB);
        playCT.getArguments().putSerializable("data",playCT.getVA());
        return playCT;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUI().initPlayer(getAct(),getVA().getPictureB());
    }
}
