package com.summer.record.ui.albums.albums;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.ItemAlbumBinding;
import com.summer.record.databinding.ItemImageImageBinding;
import com.summer.record.ui.albums.bean.Album;

import androidx.databinding.DataBindingUtil;

public class AlbumHomeAdapter extends BaseQuickAdapter<Album, BaseViewHolder> {

    Context context;

    public AlbumHomeAdapter(Context context) {
        super(R.layout.item_album);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Album item) {
        ItemAlbumBinding itemAlbumBinding = DataBindingUtil.bind(helper.itemView);
        Glide.with(context).asBitmap().load(item.getHead()).into(itemAlbumBinding.ivVideo);
        itemAlbumBinding.text.setText(item.getName());
    }
}
