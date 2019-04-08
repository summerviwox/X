package com.summer.record.ui.albums.albums;

import android.content.Context;

import com.summer.record.databinding.FragAlbumHomeBinding;
import com.summer.x.base.ui.UI;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;

public class AlbumHomeUI extends UI<FragAlbumHomeBinding> {

    private AlbumHomeAdapter recordAdapter;

    public void initRecord(Context context){
        recordAdapter= new AlbumHomeAdapter(context);
        getUI().recycle.setLayoutManager(new GridLayoutManager(context,6));
        recordAdapter.bindToRecyclerView(getUI().recycle);
    }

    public void refreshRecord(ArrayList<String> str){
        recordAdapter.setNewData(str);
    }
}
