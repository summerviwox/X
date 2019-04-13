package com.summer.record.ui.albums.albums;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.FragAlbumHomeBinding;
import com.summer.record.ui.albums.bean.Album;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;

public class AlbumHomeUI extends UI<FragAlbumHomeBinding> {

    private AlbumHomeAdapter recordAdapter;

    public void initRecord(Context context, BaseQuickAdapter.OnItemClickListener onItemClickListener, BaseQuickAdapter.OnItemLongClickListener onItemLongClickListener){
        recordAdapter= new AlbumHomeAdapter(context);
        getUI().recycle.setLayoutManager(new GridLayoutManager(context,4));
        recordAdapter.bindToRecyclerView(getUI().recycle);
        getUI().recycle.addItemDecoration(new AlbumDecoration(context,4));
        recordAdapter.setOnItemClickListener(onItemClickListener);
        recordAdapter.setOnItemLongClickListener(onItemLongClickListener);
    }

    public void refreshRecord(ArrayList<Album> str){
        recordAdapter.setNewData(str);
    }
}
