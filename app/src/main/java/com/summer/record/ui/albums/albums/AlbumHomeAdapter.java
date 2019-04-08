package com.summer.record.ui.albums.albums;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.record.R;
import com.summer.record.databinding.ItemImageImageBinding;

import androidx.databinding.DataBindingUtil;

public class AlbumHomeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    Context context;

    public AlbumHomeAdapter(Context context) {
        super(R.layout.item_image_image);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ItemImageImageBinding itemImageImageBinding = DataBindingUtil.bind(helper.itemView);
       // Glide.with(context).asBitmap().load("http://222.186.36.75:8888/records/20190324/IMG_20190323_173112.jpg").into(itemImageImageBinding.ivVideo);
    }
}
