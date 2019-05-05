package com.summer.record.ui.albums.albums;

import com.summer.record.data.net.Net;
import com.summer.record.ui.albums.bean.Album;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ListData;
import com.summer.x.data.net.ObjectData;

public class AlbumHomeDE extends DE {

    public void getAllAlbums(OnProgressI progressI){
        Net.getInstance().getAllAlbums().enqueue(new BaseCallBack<ListData<Album>>(){
            @Override
            public void onSuccess(ListData<Album> albumListData) {
                super.onSuccess(albumListData);
                if(albumListData!=null&&albumListData.getData()!=null){
                    progressI.onProgress("getAllAlbums",OnProgressI.SUCCESS,albumListData.getData());
                }
                progressI.onProgress("getAllAlbums",OnProgressI.END,null);
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
                progressI.onProgress("getAllAlbums",OnProgressI.END,null);
            }
        });
    }

    public void deleteAlbum(String id,OnProgressI progressI){
        Net.getInstance().deleteAlbum(id).enqueue(new BaseCallBack<ObjectData<Boolean>>(){
            @Override
            public void onSuccess(ObjectData<Boolean> aBoolean) {
                super.onSuccess(aBoolean);
                if(aBoolean!=null&&aBoolean.getData()){
                    progressI.onProgress("deleteAlbum",OnProgressI.SUCCESS,aBoolean.getData());
                }
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
            }
        });
    }
}
