package com.summer.record.ui.pictures.hdpicture;

import com.bumptech.glide.request.target.Target;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.ItemHdPictureBinding;
import com.summer.x.GlideApp;
import com.summer.x.base.ui.UI;

public class HDPicutreUI extends UI<ItemHdPictureBinding> {

    public void initPicture(PictureB pictureB){

        if(pictureB.getLocpath()==null){
            return;
        }
        if(pictureB.getLocpath().toLowerCase().endsWith("gif")){
            GlideApp.with(getContext()).asGif().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).load(pictureB.getLocpath()).into(getUI().picture);
        }else{
            GlideApp.with(getContext()).asBitmap().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).load(pictureB.getLocpath()).into(getUI().picture);
        }
    }
}
