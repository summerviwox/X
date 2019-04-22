package com.summer.record.ui.pictures.home;

import com.blankj.utilcode.util.GsonUtils;
import com.summer.record.data.model.PictureB;
import com.summer.record.data.net.Net;
import com.summer.record.tool.DBTool;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ListData;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PictureUploadDE extends DE {



    public void uploadRecords(PictureB pictureB, OnFinishI onFinishI){
        if(pictureB==null||pictureB.getLocpath()==null||pictureB.getNetpath()!=null){
            onFinishI.onFinished(false);
            return;
        }
        File file = new File(pictureB.getLocpath());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getPath(), requestFile);
        Net.getInstance().uploadRecords(GsonUtils.toJson(pictureB),body).enqueue(new BaseCallBack<ListData<String>>(){
            @Override
            public void onSuccess(ListData<String> s) {
                super.onSuccess(s);
                onFinishI.onFinished(s==null?null:s.getData()==null?null:s.getData().size()==0?null:s.getData().get(0));
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
                onFinishI.onFinished(null);
            }
        });
    }


    public void uploadRecords(PictureB pictureB, OnProgressI onProgressI){
        if(pictureB==null||pictureB.getLocpath()==null||pictureB.getNetpath()!=null){
            onProgressI.onProgress("",OnProgressI.ERROR,"无需上传");
            return;
        }
        File file = new File(pictureB.getLocpath());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getPath(), requestFile);
        Net.getInstance().uploadRecords(GsonUtils.toJson(pictureB),body).enqueue(new BaseCallBack<ListData<String>>(){
            @Override
            public void onSuccess(ListData<String> s) {
                super.onSuccess(s);
                onProgressI.onProgress("",OnProgressI.SUCCESS,s.getData().get(0));
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
                onProgressI.onProgress("",OnProgressI.ERROR,error);
            }
        });
    }

    int index  = 0;

    public void uploadRecords(ArrayList<PictureB> pictureBS, OnProgressI onProgressI){
        if(pictureBS.size()<=index){
            index = 0;
            onProgressI.onProgress("",OnProgressI.END,pictureBS);
            return;
        }
        uploadRecords(pictureBS.get(index), new OnFinishI() {
            @Override
            public void onFinished(Object o) {
                if(o!=null){
                    pictureBS.get(index).setNetpath(o.toString());
                    DBTool.upDataByLocalPath(pictureBS.get(index).locpath,o.toString());
                    //pictureBS.get(index).update();
                }
                onProgressI.onProgress("uploadRecords",o!=null?OnProgressI.SUCCESS:OnProgressI.ERROR,pictureBS.get(index));
                index++;
                uploadRecords(pictureBS,onProgressI);
            }
        });
    }

    public void uploadRecordsAndChangeStatus(ArrayList<PictureB> pictureBS,OnProgressI onProgressI){
        uploadRecords(pictureBS, new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case SUCCESS:
                        PictureB pictureB = (PictureB) data;
                        onProgressI.onProgress("uploadRecordsAndChangeStatus",DOING,pictureB);
                        break;
                    case END:
                        onProgressI.onProgress("uploadRecordsAndChangeStatus",END,pictureBS);
                        break;
                }
            }
        });
    }
}
