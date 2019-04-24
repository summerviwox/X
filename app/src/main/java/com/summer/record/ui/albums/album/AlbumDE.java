package com.summer.record.ui.albums.album;

import android.provider.MediaStore;
import android.text.TextUtils;

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

import java.io.File;
import java.util.ArrayList;

public class AlbumDE extends DE {

    public void getAllAlbumItemsById(String id,OnProgressI onProgressI){
        Net.getInstance().getAllAlbumItemsById(id).enqueue(new BaseCallBack<ListData<PictureB>>(){
            @Override
            public void onSuccess(ListData<PictureB> albumItemListData) {
                super.onSuccess(albumItemListData);
                if(albumItemListData!=null&&albumItemListData.getData()!=null){
                    for(int i=0;i<albumItemListData.getData().size();i++){
                        albumItemListData.getData().get(i).setPos(i);
                    }
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

    public void setAlbumHead(String id,String head,OnProgressI onProgressI){
        Net.getInstance().setAlbumHead(id,head).enqueue(new BaseCallBack<ObjectData<Boolean>>(){
            @Override
            public void onSuccess(ObjectData<Boolean> booleanObjectData) {
                super.onSuccess(booleanObjectData);
                onProgressI.onProgress("setAlbumHead",OnProgressI.SUCCESS,booleanObjectData.getData());
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
                onProgressI.onProgress("setAlbumHead",OnProgressI.ERROR,false);
            }
        });
    }



    /**
     * 获取图片类别的数据
     * @param datas
     * @return
     */
    public  ArrayList<PictureB> getImagePicture(ArrayList<PictureB> datas){
        ArrayList<PictureB> list = new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            if(PictureB.ATYPE_IMAGE.equals(datas.get(i).getAtype())|| ((""+ MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE).equals(datas.get(i).getAtype()))){
                list.add(datas.get(i));
            }
        }
        return list;
    }

    /**
     * 获取视频类别的数据
     * @param datas
     * @return
     */
    public  ArrayList<PictureB> getVideoPicture(ArrayList<PictureB> datas){
        ArrayList<PictureB> list = new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            if(PictureB.ATYPE_VIDEO.equals(datas.get(i).getAtype())|| ((""+MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO).equals(datas.get(i).getAtype()))){
                list.add(datas.get(i));
            }
        }
        return list;
    }

    public ArrayList<PictureB> getUndownLoadPictures(ArrayList<PictureB> ori){
        ArrayList<PictureB> pictureBS = new ArrayList<>();
        for(int i=0;i<ori.size();i++){
            if(TextUtils.isEmpty(ori.get(i).getAtype())){
                continue;
            }
            if(ori.get(i).getNetpath()==null){
                continue;
            }
            File file = new File(ori.get(i).getLocpath());
            //本地有该文件
            if(!file.exists()){
                pictureBS.add(ori.get(i));
            }
        }
        return pictureBS;
    }

}
