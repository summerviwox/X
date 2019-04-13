package com.summer.record.ui.albums.addalbum;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.CtAddalbumBinding;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;

public class AddAlbumUI extends UI<CtAddalbumBinding> {

    private AddAlbumAdapter addAlbumAdapter;

    public void init(Context context, BaseQuickAdapter.OnItemClickListener onItemClickListener){
        addAlbumAdapter = new AddAlbumAdapter(context);
        getUI().recycle.setLayoutManager(new GridLayoutManager(context,4));
        addAlbumAdapter.bindToRecyclerView(getUI().recycle);
        addAlbumAdapter.setOnItemClickListener(onItemClickListener);
    }

    public void setNewData(ArrayList<PictureB> datas){
        ArrayList<PictureB> d = new ArrayList<>();
        d.addAll(datas);
        d.add(new PictureB());
        addAlbumAdapter.setNewData(d);
    }

    public String getName(){
        return getUI().name.getText().toString();
    }
}
