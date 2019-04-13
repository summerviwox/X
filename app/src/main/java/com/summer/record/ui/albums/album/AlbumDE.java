package com.summer.record.ui.albums.album;

import com.blankj.utilcode.util.GsonUtils;
import com.summer.record.data.model.PictureB;
import com.summer.record.data.net.Net;
import com.summer.record.ui.albums.bean.Album;
import com.summer.record.ui.albums.bean.AlbumItem;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ListData;
import com.summer.x.data.net.ObjectData;

import java.util.ArrayList;

public class AlbumDE extends DE {

    public void getAllAlbumItemsById(String id,OnProgressI onProgressI){
        Net.getInstance().getAllAlbumItemsById(id).enqueue(new BaseCallBack<ListData<PictureB>>(){
            @Override
            public void onSuccess(ListData<PictureB> albumItemListData) {
                super.onSuccess(albumItemListData);
                if(albumItemListData!=null&&albumItemListData.getData()!=null){
                    onProgressI.onProgress("getAllAlbumItemsById",OnProgressI.SUCCESS,albumItemListData.getData());
                }
            }
        });
    }

    public void addAlbums(String id, ArrayList<String> strs,OnProgressI onProgressI){

        Net.getInstance().addAlbums(id, GsonUtils.toJson(strs)).enqueue(new BaseCallBack<ObjectData<Boolean>>(){
            @Override
            public void onSuccess(ObjectData<Boolean> booleanObjectData) {
                super.onSuccess(booleanObjectData);
                if(booleanObjectData!=null&&booleanObjectData.getData()){
                    onProgressI.onProgress("addAlbums",OnProgressI.SUCCESS,booleanObjectData.getData());
                }
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
            }
        });
    }


    public void deleteAlbumItem(String albumid,String recordid,OnProgressI onProgressI){
        Net.getInstance().deleteAlbumItem(albumid,recordid).enqueue(new BaseCallBack<ObjectData<Boolean>>(){
            @Override
            public void onSuccess(ObjectData<Boolean> booleanObjectData) {
                super.onSuccess(booleanObjectData);
                if(booleanObjectData!=null&&booleanObjectData.getData()){
                    onProgressI.onProgress("deleteAlbumItem",OnProgressI.SUCCESS,booleanObjectData.getData());
                }
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
            }
        });
    }

    public ArrayList<String> getLocPaths(ArrayList<PictureB> pictureBS){
        ArrayList<String> strs = new ArrayList<>();
        for(int i=0;i<pictureBS.size();i++){
            strs.add(pictureBS.get(i).getLocpath());
        }
        return strs;
    }

}
