package com.summer.record.ui.pictures.picture;

import com.bumptech.glide.Glide;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.FragPictureBinding;
import com.summer.x.base.ui.UI;

public class PictureUI extends UI<FragPictureBinding> {

    @Override
    public void initUI() {
        super.initUI();
    }

    public void initPicture(PictureB pictureB){
       // Glide.with(getContext()).asBitmap().load(NetConstant.URL+pictureB.getNetpath()).into(getUI().picture);
    }
}
