package com.summer.record.ui.albums.addalbum;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.ItemAlbumBinding;
import com.summer.record.databinding.ItemImageImageBinding;

import androidx.databinding.DataBindingUtil;

public class AddAlbumAdapter extends BaseQuickAdapter<PictureB, BaseViewHolder> {

    private Context context;

    public AddAlbumAdapter(Context context) {
        super(R.layout.item_image_image);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PictureB item) {
        ItemImageImageBinding itemImageImageBinding = DataBindingUtil.bind(helper.itemView);
        Glide.with(context).asBitmap().load(item.getLocpath()).into(itemImageImageBinding.ivVideo);
    }
}
