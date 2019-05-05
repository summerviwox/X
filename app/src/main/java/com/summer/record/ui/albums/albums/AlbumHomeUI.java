package com.summer.record.ui.albums.albums;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.FragAlbumHomeBinding;
import com.summer.record.ui.albums.bean.Album;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class AlbumHomeUI extends UI<FragAlbumHomeBinding> {

    private AlbumHomeAdapter recordAdapter;

    public void initRecord(Context context, BaseQuickAdapter.OnItemClickListener onItemClickListener, BaseQuickAdapter.OnItemLongClickListener onItemLongClickListener, SwipeRefreshLayout.OnRefreshListener onRefreshListener){
        recordAdapter= new AlbumHomeAdapter(context);
        getUI().recycle.setLayoutManager(new GridLayoutManager(context,5));
        recordAdapter.bindToRecyclerView(getUI().recycle);
        getUI().recycle.addItemDecoration(new AlbumDecoration(context,5));
        recordAdapter.setOnItemClickListener(onItemClickListener);
        recordAdapter.setOnItemLongClickListener(onItemLongClickListener);
        getUI().refresh.setOnRefreshListener(onRefreshListener);
    }

    public void refreshRecord(ArrayList<Album> str){
        recordAdapter.setNewData(str);
    }


    public void endRefresh(){
        getUI().refresh.setRefreshing(false);
    }
}
