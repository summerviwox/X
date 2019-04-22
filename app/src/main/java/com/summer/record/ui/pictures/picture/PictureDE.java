package com.summer.record.ui.pictures.picture;

import com.blankj.utilcode.util.GsonUtils;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.data.net.Net;
import com.summer.record.tool.DownLoadTool;
import com.summer.record.ui.albums.bean.Album;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ListData;
import com.summer.x.data.net.ObjectData;

import java.util.ArrayList;

public class PictureDE extends DE {

    public void getAllAlbums(OnProgressI onProgressI){
        Net.getInstance().getAllAlbums().enqueue(new BaseCallBack<ListData<Album>>(){
            @Override
            public void onSuccess(ListData<Album> albumListData) {
                super.onSuccess(albumListData);
                if(albumListData!=null&&albumListData.getData()!=null){
                    onProgressI.onProgress("", OnProgressI.SUCCESS,albumListData.getData());
                }
                onProgressI.onProgress("", OnProgressI.END,null);
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
                onProgressI.onProgress("", OnProgressI.END,null);
            }
        });
    }

    public void addAlbums(String id,String locpath,OnProgressI progressI){
        ArrayList<String> strs = new ArrayList<>();
        strs.add(locpath);
        Net.getInstance().addAlbums(id, GsonUtils.toJson(strs)).enqueue(new BaseCallBack<ObjectData<Boolean>>(){
            @Override
            public void onSuccess(ObjectData<Boolean> booleanObjectData) {
                super.onSuccess(booleanObjectData);
                if(booleanObjectData!=null&&booleanObjectData.getData()){
                    progressI.onProgress("addAlbums",OnProgressI.SUCCESS,true);
                }
                progressI.onProgress("addAlbums",OnProgressI.END,true);
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
                progressI.onProgress("addAlbums",OnProgressI.END,false);
            }
        });
    }


}
