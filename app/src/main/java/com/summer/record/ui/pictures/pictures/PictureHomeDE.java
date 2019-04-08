package com.summer.record.ui.pictures.pictures;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.summer.record.data.model.PictureB;
import com.summer.record.data.net.Net;
import com.summer.record.tool.FileTool;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ListData;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class PictureHomeDE extends DE {

    public void getPictures(OnProgressI onProgressI){
        Net.getInstance().getRecordsWithTypeSize("image","0").enqueue(new BaseCallBack<ListData<PictureB>>(){
            @Override
            public void onSuccess(ListData<PictureB> pictureBListData) {
                super.onSuccess(pictureBListData);
                if(pictureBListData!=null&&pictureBListData.getData()!=null){
                    onProgressI.onProgress("",OnProgressI.SUCCESS,pictureBListData.getData());
                }
            }
        });
    }

    public void uploadRecords(String localpath){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File(localpath));
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile", localpath, requestFile);
       Net.getInstance().uploadRecords(localpath,body).enqueue(new BaseCallBack<String>(){
           @Override
           public void onSuccess(String s) {
               super.onSuccess(s);
           }
       });
    }

    public void getLocalPictures(Context context, OnProgressI onProgressI){
        FileTool.getPictures(context, new String[]{ "0","" + System.currentTimeMillis() / 1000},onProgressI);
    }


    /**
     * 按照日期排序
     * @param videos
     * @param num 每行的数量
     * @return
     */
    public ArrayList<PictureB> dealRecord(ArrayList<PictureB> videos, int num){
        HashMap<String,ArrayList<PictureB>> map = new HashMap<>();
        for(int i=0;i<videos.size();i++){
            String date = videos.get(i).getDateStr();
            if(map.get(date)==null){
                map.put(date,new ArrayList<PictureB>());
            }
            map.get(date).add(videos.get(i));
        }
        //用空记录补完空余
        String[] strs = new String[map.keySet().size()];
        strs = map.keySet().toArray(strs);
        for(int i=0;i<strs.length;i++){
            ArrayList<PictureB> videos1 = map.get(strs[i]);
            if(videos1.size()%num!=0){
                int left = videos1.size()%num;
                for(int j=0;j<num-left;j++){
                    map.get(strs[i]).add(new PictureB("",-1,null,0l,0l,map.get(strs[i]).get(0).getUtime(),null));
                }
            }
        }
        ArrayList<PictureB> v = new ArrayList<>();
        strs = map.keySet().toArray(strs);
        ArrayList<Long> timestr = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        for(int i=0;i<strs.length;i++){
            try {
                timestr.add(format.parse(strs[i]).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //排序日期 冒泡排序法
        for(int i=0;i<timestr.size();i++){
            for(int j=0;j<timestr.size()-1-i;j++){
                if(timestr.get(j)>timestr.get(j+1)){
                    long l = timestr.get(j+1);
                    timestr.set(j+1,timestr.get(j));
                    timestr.set(j,l);
                }
            }
        }
        //从大到小排序 转化日期
        ArrayList<String> ss = new ArrayList<>();
        for(int i=timestr.size()-1;i>=0;i--){
            Date d=new Date(timestr.get(i));
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            ss.add(format.format(d));
        }

        //某一天的记录按日期倒叙
        for(int i=0;i<ss.size();i++){
            ArrayList<PictureB> records = map.get(ss.get(i));
            ArrayList<PictureB> datas = new ArrayList<>();
            for(int j=records.size()-1;j>=0;j--){
                //去掉空的站位记录
                if(!TextUtils.isEmpty(records.get(j).getAtype())){
                    datas.add(records.get(j));
                }
            }
            int size = records.size()-datas.size();
            for(int a=0;a<size;a++){
                datas.add(new PictureB("",-1,null,0l,0l,records.get(0).getUtime(),null));
            }
            map.put(ss.get(i),datas);
        }

        for(int i=0;i<ss.size();i++){
            for(int j=0;j<num;j++){
                map.get(ss.get(i)).get(j).setFrist(true);
            }
            v.addAll(map.get(ss.get(i)));
        }
        int j=0;
        for(int i =0;i<v.size();i++){
            v.get(i).setId(i);
            if(v.get(i).getLocpath()!=null){
               // v.get(i).setPos(j);
                j++;
            }
        }
        map=null;
        return v;
    }

}
