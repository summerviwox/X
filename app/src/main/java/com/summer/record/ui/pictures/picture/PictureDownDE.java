package com.summer.record.ui.pictures.picture;

import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.tool.DownLoadTool;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;

import java.util.ArrayList;

import retrofit2.http.POST;

public class PictureDownDE extends DE {

    /**
     * 单个下载
     * @param pictureB
     * @param progressI
     */
    public void downloadOne(PictureB pictureB, OnProgressI progressI){
        if(pictureB==null||pictureB.getNetpath()==null){
            progressI.onProgress("downloadOne",OnProgressI.END,pictureB);
            return;
        }
        new DownLoadTool().downloadFile(NetConstant.getNetPath(pictureB), pictureB.getLocpath(), progressI);
    }


    /**
     * 批量下载
     * @param pictureBS
     * @param index
     * @param onProgressI
     */
    public void downloadList(ArrayList<PictureB> pictureBS, int index, OnProgressI onProgressI){
        if(index >=pictureBS.size()){
            onProgressI.onProgress("downloadList",OnProgressI.END,pictureBS);
            return;
        }
        downloadOne(pictureBS.get(index), new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case END:
                        onProgressI.onProgress("downloadList",DOING,pictureBS.get(index));
                        downloadList(pictureBS,index+1,onProgressI);
                        break;
                }
            }
        });
    }
}
