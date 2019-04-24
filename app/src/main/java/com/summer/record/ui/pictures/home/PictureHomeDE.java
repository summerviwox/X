package com.summer.record.ui.pictures.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.data.net.Net;
import com.summer.record.tool.DBTool;
import com.summer.record.ui.pictures.home.model.ItemEntity;
import com.summer.record.ui.pictures.home.model.TitleEntity;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ListData;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

import androidx.loader.content.CursorLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class PictureHomeDE extends DE {


    public void asyncGetPicturesFromDB(PictureGetDE pictureGetDE, PictureHomeDE pictureHomeDE,Context context, Long[] time, OnProgressI onProgressI){
        pictureGetDE.asyncGetPicturesFromDB(context, time, new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case SUCCESS:
                        pictureHomeDE.aysncDeal((ArrayList<PictureB>) data, NetConstant.SPANCOUNT,onProgressI);
                        break;
                }
            }
        });
    }


    /**
     * 按照日期排序
     * @param videos
     * @param num 每行的数量
     * @return
     */
    public ArrayList<PictureB> dealRecord(ArrayList<PictureB> videos, int num){
        //获取只精确到日的时间字符串
        for(int i=0;videos!=null&&i<videos.size();i++){
            videos.get(i).initDateStr();
        }
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
        videos.clear();
        videos.addAll(v);
        return videos;
    }


    public void aysncDeal(ArrayList<PictureB> videos, int num,OnProgressI progressI){
        new AsyncTask<String, String, ArrayList<PictureB>>() {
            @Override
            protected ArrayList<PictureB> doInBackground(String... strings) {
                return dealRecord(videos,num);
            }

            @Override
            protected void onPostExecute(ArrayList<PictureB> pictureBS) {
                super.onPostExecute(pictureBS);
                progressI.onProgress("aysncDeal",OnProgressI.SUCCESS,pictureBS);
            }
        }.execute();
    }

    /**
     * 按月排序
     * @param videos
     * @param num
     * @return
     */
    public ArrayList<MultiItemEntity> getMothSortModel(ArrayList<PictureB> videos, int num){
        ArrayList<MultiItemEntity> datas = new ArrayList<>();
        //获取只精确到日的时间字符串
        for(int i=0;videos!=null&&i<videos.size();i++){
            videos.get(i).initDateStr();
        }
        //按 月分类
        HashMap<String, ArrayList<PictureB>> map = new HashMap<>();
        for(int i=0;i<videos.size();i++){
            if(map.get(videos.get(i).getYyyyMM())==null){
                map.put(videos.get(i).getYyyyMM(),new ArrayList<>());
            }
            map.get(videos.get(i).getYyyyMM()).add(videos.get(i));
        }
        ArrayList<String> months = new ArrayList<>();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            months.add(iterator.next());
        }
        HashMap<Integer,String> monthmap = new HashMap<>();
        ArrayList<Integer> sizes = new ArrayList<>();
        //对月排序
        for(int i=0;i<months.size();i++){
            String[] m = months.get(i).split("-");
            int size = Integer.parseInt(m[0])*10+Integer.parseInt(m[1]);
            sizes.add(size);
            monthmap.put(size,months.get(i));
        }
        Collections.sort(sizes);
        ArrayList<String> newmonth = new ArrayList<>();
        for(int i=0;i<sizes.size();i++){
            newmonth.add(monthmap.get(sizes.get(i)));
        }
        for(int i=0;i<newmonth.size();i++){
            TitleEntity titleEntity = new TitleEntity();
            titleEntity.setTitle(newmonth.get(i));
            for(int j=0;j<map.get(newmonth.get(i)).size();j++){
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setItem(map.get(newmonth.get(i)).get(j));
                titleEntity.addSubItem(itemEntity);
            }
            datas.add(titleEntity);
        }
        return datas;
    }

    /**
     *取网络数据和本地数据的并集
     * @param netdata
     * @param localdata
     */
    public ArrayList<PictureB>  getNetAndLocalData(ArrayList<PictureB> netdata,ArrayList<PictureB> localdata){
        HashMap<String,PictureB> map = new HashMap<>();
        for(int i=0;i<netdata.size();i++){
            map.put(netdata.get(i).getLocpath(),netdata.get(i));
        }
        for(int i=0;i<localdata.size();i++){
            if(map.get(localdata.get(i).getLocpath())!=null){
                localdata.remove(i);
                i--;
            }
        }
        ArrayList<PictureB> data = new ArrayList<>();
        data.addAll(netdata);
        data.addAll(localdata);
        return  data;
    }

    /**
     * 获取网络数据
     * @param progressI
     */
    public void getNetData(OnProgressI progressI){
        Net.getInstance().getAllPictures("0",System.currentTimeMillis()+"").enqueue(new BaseCallBack<ListData<PictureB>>(){
            @Override
            public void onSuccess(ListData<PictureB> pictureBListData) {
                super.onSuccess(pictureBListData);
                if(pictureBListData!=null&&pictureBListData.getData()!=null){
                    progressI.onProgress("getNetData",OnProgressI.SUCCESS,pictureBListData.getData());
                }
            }
        });
    }

    public void init(Context context,OnProgressI onProgressI){
        onProgressI.onProgress("getPictures",OnProgressI.PREPARE,"");
        //初始化过
        if(DBTool.isInit()){
            addLastPicturesToDB(context,onProgressI);
        }else{
            fristInit(context,onProgressI);
        }
    }

    /**
     * 第一次安装app同步网络数据和本地数据到本地数据库
     * @param context
     * @param onProgressI
     */
    public void fristInit(Context context,OnProgressI onProgressI){
        ArrayList<PictureB> netdata=new ArrayList<>();
        ArrayList<PictureB> localdata=new ArrayList<>();
        if(onProgressI!=null){
            onProgressI.onProgress("getPictures",OnProgressI.DOING,"获取网络数据");
        }
        getNetData(new OnProgressI() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case SUCCESS:
                        if(onProgressI!=null){
                            onProgressI.onProgress("getPictures",OnProgressI.DOING,"获取本地数据");
                        }
                        netdata.addAll((Collection<? extends PictureB>) data);
                        ArrayList<PictureB> pictureBS = new ArrayList<>();
                        String[] projection = new String[]{MediaStore.Files.FileColumns._ID,
                                MediaStore.Files.FileColumns.MEDIA_TYPE,
                                MediaStore.Files.FileColumns.DATA,
                                MediaStore.Files.FileColumns.DATE_ADDED,
                                MediaStore.Files.FileColumns.DATE_MODIFIED,
                                MediaStore.Files.FileColumns.DISPLAY_NAME};
                        String selection = "("+MediaStore.Files.FileColumns.MEDIA_TYPE + "="+ MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                                + " OR "
                                + MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO+")"
                                +" AND "
                                +MediaStore.Files.FileColumns.DATE_ADDED+">=? AND "+MediaStore.Images.Media.DATE_ADDED+"< ? ";
                        Uri queryUri = MediaStore.Files.getContentUri("external");
                        CursorLoader cursorLoader = new CursorLoader(
                                context,
                                queryUri,
                                projection,
                                selection,
                                new String[]{"0",""+System.currentTimeMillis()},
                                MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
                        );
                        new AsyncTask<String, String, ArrayList<PictureB>>() {
                            @Override
                            protected ArrayList<PictureB> doInBackground(String... strings) {
                                Cursor cursor = cursorLoader.loadInBackground();
                                while (cursor.moveToNext()){
                                    PictureB pictureB = new PictureB(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE)),
                                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)),
                                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED))*1000,
                                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED))*1000,
                                            0l,
                                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME))
                                    );
                                    //record.init();
                                    pictureBS.add(pictureB);
                                    publishProgress(pictureB.getName());
                                }
                                localdata.addAll(pictureBS);
                                ArrayList<PictureB> alldata = getNetAndLocalData(netdata,localdata);
                                //dealRecord(alldata,NetConstant.SPANCOUNT);
                                //getMothSortModel(pictureBS,NetConstant.SPANCOUNT);
                                DBTool.savePictures(alldata, new OnProgressI() {
                                    @Override
                                    public void onProgress(String tag, int status, Object data) {
                                        switch (status){
                                            case DOING:
                                                publishProgress(data+"");
                                                break;
                                        }
                                    }
                                });
                                return alldata;
                            }

                            @Override
                            protected void onPostExecute(ArrayList<PictureB> pictureBS) {
                                super.onPostExecute(pictureBS);
                                if(onProgressI!=null){
                                    onProgressI.onProgress("getPictures",OnProgressI.END,pictureBS);
                                }
                            }

                            @Override
                            protected void onProgressUpdate(String... values) {
                                super.onProgressUpdate(values);
                                if(onProgressI!=null){
                                    onProgressI.onProgress("getPictures",OnProgressI.DOING,values[0]);
                                }
                            }
                        }.execute();
                        break;
                }
            }
        });
    }

    public ArrayList<PictureB> getUploadPictures(ArrayList<PictureB> ori){
        ArrayList<PictureB> pictureBS = new ArrayList<>();
        for(int i=0;i<ori.size();i++){
            if(TextUtils.isEmpty(ori.get(i).getAtype())){
                continue;
            }
            //未上传
            if(TextUtils.isEmpty(ori.get(i).getNetpath())){
                if(ori.get(i).getLocpath()!=null){
                    File file = new File(ori.get(i).getLocpath());
                    //本地有该文件
                    if(file.exists()){
                         pictureBS.add(ori.get(i));
                    }
                }
            }
        }
        return pictureBS;
    }



    /**
     * 将本地最新数据添加到数据库
     * @param context
     * @param onProgressI
     * @return
     */
    @SuppressLint("StaticFieldLeak")
    public ArrayList<PictureB> addLastPicturesToDB(Context context,OnProgressI onProgressI){
        ArrayList<PictureB> pictureBS = new ArrayList<>();
        String[] timeduraion = new String[]{""+DBTool.getLasPictureTime()/1000,""+System.currentTimeMillis()/1000};
        String[] projection = new String[]{MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.DISPLAY_NAME};
        String selection = "("+MediaStore.Files.FileColumns.MEDIA_TYPE + "="+ MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO+")"
                +" AND "
                +MediaStore.Files.FileColumns.DATE_ADDED+"> ? AND "+MediaStore.Images.Media.DATE_ADDED+"<= ? ";
        Uri queryUri = MediaStore.Files.getContentUri("external");
        CursorLoader cursorLoader = new CursorLoader(
                context,
                queryUri,
                projection,
                selection,
                timeduraion,
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
        );
        new AsyncTask<String,String,ArrayList<PictureB>>(){

            @Override
            protected ArrayList<PictureB> doInBackground(String... strings) {
                //先从本地数据取出所有数据
                publishProgress("从数据库获取历史记录");
                ArrayList<PictureB> localPictures = DBTool.getAllRecord(new long[]{0,System.currentTimeMillis()});
                if(localPictures!=null){
                    pictureBS.addAll(localPictures);
                }
                //然后从手机取出本地数据库最后一条保存时间到现在的所有数据
                Cursor cursor = cursorLoader.loadInBackground();
                ArrayList<PictureB> phonePictures = new ArrayList<>();
                publishProgress("获取完成");
                publishProgress("获取手机记录");
                while (cursor.moveToNext()){
                    PictureB pictureB = new PictureB(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE))==(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)?PictureB.ATYPE_IMAGE:PictureB.ATYPE_VIDEO,
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED))*1000,
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED))*1000,
                            0l,
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME))
                    );
                    //record.init();
                    phonePictures.add(pictureB);
                    publishProgress("获取完成");
                }
                pictureBS.addAll(phonePictures);
                //处理图片
                publishProgress("处理图片");
                //dealRecord(pictureBS, NetConstant.SPANCOUNT);
                //getMothSortModel(pictureBS,NetConstant.SPANCOUNT);
                //保存没有保存过的记录
                DBTool.savePictures(phonePictures ,new OnProgressI() {
                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        switch (status){
                            case DOING:
                                publishProgress(data+"");
                                break;
                        }
                    }
                });
                publishProgress("保存完成");
                return pictureBS;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                onProgressI.onProgress("getPictures",OnProgressI.DOING,values[0]);
            }

            @Override
            protected void onPostExecute(ArrayList<PictureB> pictureBS) {
                super.onPostExecute(pictureBS);
                onProgressI.onProgress("getPictures",OnProgressI.END,pictureBS);
            }
        }.execute();
        return pictureBS;
    }

    /**
     * 去掉填充的数据
     * @param ori
     * @return
     */
    public ArrayList<PictureB> removeNullPictures(ArrayList<PictureB> ori){
        ArrayList<PictureB> datas = new ArrayList<>();
        for(int i=0;i<ori.size();i++){
            if(!TextUtils.isEmpty(ori.get(i).getAtype())){
                datas.add(ori.get(i));
            }
        }
        for(int i=0;i<datas.size();i++){
            datas.get(i).setPos(i);
        }
        return datas;
    }

}
