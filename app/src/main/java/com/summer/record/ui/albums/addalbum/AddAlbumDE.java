package com.summer.record.ui.albums.addalbum;

import com.blankj.utilcode.util.GsonUtils;
import com.summer.record.data.net.Net;
import com.summer.record.ui.albums.bean.AddAlbumReq;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;

public class AddAlbumDE extends DE {

    /**
     * 创建相册
     * @param addAlbumReq
     * @param onProgressI
     */
    public void addAlbum(AddAlbumReq addAlbumReq, OnProgressI onProgressI){
        Net.getInstance().addNewAlbum(GsonUtils.toJson(addAlbumReq)).enqueue(new BaseCallBack<Boolean>(){
            @Override
            public void onSuccess(Boolean aBoolean) {
                super.onSuccess(aBoolean);
                if(aBoolean){
                    onProgressI.onProgress("addNewAlbum",OnProgressI.SUCCESS,addAlbumReq);
                }else{
                    onProgressI.onProgress("addNewAlbum",OnProgressI.ERROR,addAlbumReq);
                }
                onProgressI.onProgress("addNewAlbum",OnProgressI.END,addAlbumReq);
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
                onProgressI.onProgress("addNewAlbum",OnProgressI.ERROR,addAlbumReq);
                onProgressI.onProgress("addNewAlbum",OnProgressI.END,addAlbumReq);
            }
        });
    }

}
