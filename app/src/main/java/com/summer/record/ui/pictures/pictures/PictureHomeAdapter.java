package com.summer.record.ui.pictures.pictures;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.record.R;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.ItemImageImageBinding;

import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

public class PictureHomeAdapter extends BaseQuickAdapter<PictureB, BaseViewHolder> {

    private Context context;

    private RequestOptions requestOptions;

    public PictureHomeAdapter(Context context) {
        super(R.layout.item_image_image);
        this.context = context;
        requestOptions = new RequestOptions();
        requestOptions.encodeQuality(5).centerCrop().placeholder(R.color.zxing_transparent).skipMemoryCache(false);
    }

    @Override
    protected void convert(BaseViewHolder helper, PictureB item) {
        ItemImageImageBinding itemImageImageBinding = DataBindingUtil.bind(helper.itemView);
        //Glide.with(context).asBitmap().apply(requestOptions).load(NetConstant.URL +item.getNetpath()).into(itemImageImageBinding.ivVideo);
        Glide.with(context).asBitmap().apply(requestOptions).load(item.getLocpath()).into(itemImageImageBinding.ivVideo);
        ViewCompat.setTransitionName(itemImageImageBinding.ivVideo,String.valueOf(helper.getLayoutPosition()+"_image"));
    }
}
