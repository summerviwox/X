package com.summer.record.ui.pictures.picture;

import com.bumptech.glide.Glide;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.FragPictureBinding;
import com.summer.x.GlideApp;
import com.summer.x.base.ui.UI;

public class PictureUI extends UI<FragPictureBinding> {

    @Override
    public void initUI() {
        super.initUI();
    }

    public void initPicture(PictureB pictureB){
        if(pictureB.getLocpath().toLowerCase().endsWith("gif")){
            GlideApp.with(getContext()).asGif().load(pictureB.getLocpath()).into(getUI().picture);
        }else{
            GlideApp.with(getContext()).asBitmap().load(pictureB.getLocpath()).into(getUI().picture);
        }
    }
}
