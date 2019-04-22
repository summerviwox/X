package com.summer.record.ui.albums.album;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.CtAlbumBinding;
import com.summer.record.ui.albums.albums.AlbumDecoration;
import com.summer.record.ui.albums.bean.Album;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;

public class AlbumUI extends UI<CtAlbumBinding> {

    AlbumAdapter albumAdapter;

    public void initUI(Context context, BaseQuickAdapter.OnItemClickListener onItemClickListener, BaseQuickAdapter.OnItemLongClickListener onItemLongClickListener){
        albumAdapter = new AlbumAdapter(context);
        getUI().recycle.setLayoutManager(new GridLayoutManager(context,4));
        albumAdapter.bindToRecyclerView(getUI().recycle);
        getUI().recycle.addItemDecoration(new AlbumDecoration(context,4));
        albumAdapter.setOnItemClickListener(onItemClickListener);
        albumAdapter.setOnItemLongClickListener(onItemLongClickListener);
    }

    public void setNewData(ArrayList<PictureB> datas){
        albumAdapter.setNewData(datas);
    }

    public void notifyDataSetChanged(){
        albumAdapter.notifyDataSetChanged();
    }

    public void notifyItemChanged(int pos){
        albumAdapter.notifyItemChanged(pos);
    }


}
