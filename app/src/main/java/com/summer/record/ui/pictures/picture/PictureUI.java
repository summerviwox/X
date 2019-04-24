package com.summer.record.ui.pictures.picture;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.ItemPictureBinding;
import com.summer.x.GlideApp;
import com.summer.x.base.ui.UI;

import java.io.File;

public class PictureUI extends UI<ItemPictureBinding> {

    @Override
    public void initUI() {
        super.initUI();
    }

    public void initPicture(PictureB pictureB){

//        File file = new File(pictureB.getLocpath());
//        if(!file.exists()){
//            if(pictureB.getLocpath().toLowerCase().endsWith("gif")){
//                GlideApp.with(getContext()).asGif().load((NetConstant.URL+pictureB.getNetpath().replace("E:\\record","records").replace("\\","/"))).into(getUI().picture);
//            }else{
//                GlideApp.with(getContext()).asBitmap().load((NetConstant.URL+pictureB.getNetpath().replace("E:\\record","records").replace("\\","/"))).into(getUI().picture);
//            }
//
//        }else{
//
//
//        }
        if(pictureB.getLocpath()==null){
            return;
        }
        if(pictureB.getLocpath().toLowerCase().endsWith("gif")){
            GlideApp.with(getContext()).asGif().encodeQuality(100).diskCacheStrategy(DiskCacheStrategy.RESOURCE) // NONE if you load from sdcard
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).load(pictureB.getLocpath()).into(getUI().picture);
        }else{
            GlideApp.with(getContext()).asBitmap().encodeQuality(100).diskCacheStrategy(DiskCacheStrategy.RESOURCE) // NONE if you load from sdcard
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).load(pictureB.getLocpath()).into(getUI().picture);
        }
    }
}
