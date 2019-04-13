package com.summer.record.ui.albums.album;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.ItemAlbumBinding;
import com.summer.record.databinding.ItemImageImageBinding;
import com.summer.record.ui.albums.bean.Album;
import com.summer.x.GlideApp;

import androidx.databinding.DataBindingUtil;


public class AlbumAdapter extends BaseQuickAdapter<PictureB, BaseViewHolder> {

    private Context context;

    public AlbumAdapter(Context context) {
        super(R.layout.item_image_image);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PictureB item) {
        ItemImageImageBinding itemImageImageBinding = DataBindingUtil.bind(helper.itemView);
        GlideApp.with(context).load(item.getLocpath()).into(itemImageImageBinding.ivVideo);
    }
}
