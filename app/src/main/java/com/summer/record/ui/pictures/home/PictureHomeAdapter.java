package com.summer.record.ui.pictures.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.record.R;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.ItemImageImageBinding;
import com.summer.x.GlideApp;

import java.io.File;

import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

public class PictureHomeAdapter extends BaseQuickAdapter<PictureB, BaseViewHolder> {

    private Context context;

    private RequestOptions requestOptions;

    public PictureHomeAdapter(Context context) {
        super(R.layout.item_image_image);
        this.context = context;
        requestOptions = new RequestOptions();
        requestOptions.encodeQuality(10).placeholder(R.color.zxing_transparent).skipMemoryCache(false);
        //requestOptions.encodeQuality(10).override(ScreenUtils.getScreenWidth()/PictureHomeUI.SPANCOUNT,ScreenUtils.getScreenWidth()/PictureHomeUI.SPANCOUNT).centerCrop().placeholder(R.color.zxing_transparent).skipMemoryCache(false);
    }

    @Override
    protected void convert(BaseViewHolder helper, PictureB item) {
        if(TextUtils.isEmpty(item.getAtype())){
            helper.itemView.setVisibility(View.INVISIBLE);
            return;
        }
        helper.itemView.setVisibility(View.VISIBLE);
        ItemImageImageBinding itemImageImageBinding = DataBindingUtil.bind(helper.itemView);
        //Glide.with(context).asBitmap().apply(requestOptions).load(NetConstant.URL +item.getNetpath()).into(itemImageImageBinding.ivVideo);
        ViewCompat.setTransitionName(itemImageImageBinding.ivVideo,String.valueOf(helper.getLayoutPosition()+"_image"));
        if(item.isSelected()){
            itemImageImageBinding.ivVideo.setAlpha(0.3f);
        }else{
            itemImageImageBinding.ivVideo.setAlpha(1f);
        }
        File file = new File(item.getLocpath());
        if(!file.exists()){
            GlideApp.with(context).asBitmap().load((NetConstant.URL+item.getNetpath().replace("E:\\record","records").replace("\\","/"))).apply(requestOptions).into(itemImageImageBinding.ivVideo);
        }else{
            GlideApp.with(context).asBitmap().load(item.getLocpath()).apply(requestOptions).into(itemImageImageBinding.ivVideo);
        }
    }
}
