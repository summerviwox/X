package com.summer.record.ui.albums.albums;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.albums.addalbum.AddAlbumCT;
import com.summer.record.ui.albums.album.AlbumCT;
import com.summer.record.ui.albums.bean.Album;
import com.summer.record.ui.menu.MenuFrag;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
 * 照片首页
 */
public class AlbumHomeCT extends XFragment<AlbumHomeUI, AlbumHomeDE, AlbumHomeVA> implements BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemLongClickListener{

    public static AlbumHomeCT getInstance(){
        AlbumHomeCT albumHomeCT = new AlbumHomeCT();
        albumHomeCT.setArguments(new Bundle());
        return albumHomeCT;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUI().initRecord(getAct(),this,this);
        getAllAlbums();
    }


    public void getAllAlbums(){
        getDE().getAllAlbums(new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case SUCCESS:
                        getVA().setAlbums((ArrayList<Album>) data);
                        getUI().refreshRecord(getVA().getAlbums());
                        break;
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        start(AlbumCT.getInstance(getVA().getAlbums().get(position).getId()+""));
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.globalmenu:
                extraTransaction().startForResultDontHideSelf(MenuFrag.getInstance(getVA().getMenus()),1);
                break;
        }
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(data.getSerializable("datas")!=null){
                    getAllAlbums();
                }
                break;
            case 1:
                if(data!=null&&data.getInt("index",-1)!=-1){
                    int index = data.getInt("index");
                    switch (index){
                        case 0:
                            //新增相册
                            extraTransaction().startForResultDontHideSelf(AddAlbumCT.getInstance(),0);
                            break;
                        case 1:
                            //删除相册
                            getDE().deleteAlbum(getVA().getSelectedAlbum().getId()+"", new OnProgressI() {
                                @Override
                                public void onProgress(String tag, int status, Object data) {
                                    getAllAlbums();
                                }
                            });
                            break;
                    }
                }
                break;
        }
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        getVA().setSelectedAlbum(getVA().getAlbums().get(position));
        extraTransaction().startForResultDontHideSelf(MenuFrag.getInstance(getVA().getMenus()),1);
        return true;
    }
}
