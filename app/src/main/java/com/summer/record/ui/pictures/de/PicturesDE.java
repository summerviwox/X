package com.summer.record.ui.pictures.de;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.data.net.Net;
import com.summer.record.tool.DBTool;
import com.summer.record.tool.FileTool;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.ListData;
import com.summer.x.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

import androidx.loader.content.CursorLoader;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.POST;

public class PicturesDE extends DE {

    /**
     * 通过
     */
    public void synchLocalAndNetToDB(Context context, OnProgressI progressI){
        progressI.onProgress("synchLocalAndNetToDB",OnProgressI.PREPARE,"开始同步");
        final CursorLoader[] cursorLoader = new CursorLoader[1];
        Disposable subscribe = Observable.just("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        //获取最近的数据库存储时间点
                        return StringUtil.getStr(DBTool.getLasPictureTime());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //初始化文件获取
                        cursorLoader[0] = new FileTool().getCursor(context, new String[]{s, StringUtil.getStr(System.currentTimeMillis())});
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        progressI.onProgress("synchLocalAndNetToDB", OnProgressI.DOING, "开始获取网络数据");
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<ListData<PictureB>>>() {
                    @Override
                    public ObservableSource<ListData<PictureB>> apply(String s) throws Exception {
                        //网络获取数据
                        return Net.getInstance().getAllPictures2(s, StringUtil.getStr(System.currentTimeMillis()));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<ListData<PictureB>>() {
                    @Override
                    public void accept(ListData<PictureB> pictureBListData) throws Exception {
                        progressI.onProgress("synchLocalAndNetToDB", OnProgressI.DOING, "开始获取本地数据");
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<ListData<PictureB>, Object[]>() {
                    @Override
                    public Object[] apply(ListData<PictureB> pictureBListData) throws Exception {
                        //本地获取数据
                        ArrayList<PictureB> arrayList = new FileTool().getData(cursorLoader[0]);
                        return new Object[]{pictureBListData.getData(), arrayList};
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object[]>() {
                    @Override
                    public void accept(Object[] objects) throws Exception {
                        progressI.onProgress("synchLocalAndNetToDB", OnProgressI.DOING, "开始合并数据");
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<Object[], ArrayList<PictureB>>() {
                    @Override
                    public ArrayList<PictureB> apply(Object[] objects) throws Exception {
                        //合并数据
                        ArrayList<PictureB> net = (ArrayList<PictureB>) objects[0];
                        ArrayList<PictureB> loc = (ArrayList<PictureB>) objects[1];
                        ArrayList<PictureB> pictures = new ArrayList<>();
                        pictures.addAll(net);
                        pictures.addAll(loc);
                        return getOnlyPictures(pictures);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<ArrayList<PictureB>>() {
                    @Override
                    public void accept(ArrayList<PictureB> pictureBS) throws Exception {
                        progressI.onProgress("synchLocalAndNetToDB", OnProgressI.DOING, "开始保存数据");
                    }
                })
                .map(new Function<ArrayList<PictureB>, ArrayList<PictureB>>() {
                    @Override
                    public ArrayList<PictureB> apply(ArrayList<PictureB> pictureBS) throws Exception {
                        DBTool.savePictures(pictureBS);
                        return pictureBS;
                    }
                })
                .subscribe(new Consumer<ArrayList<PictureB>>() {
                    @Override
                    public void accept(ArrayList<PictureB> pictureBS) throws Exception {
                        progressI.onProgress("synchLocalAndNetToDB", OnProgressI.END, "操作完成");
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void getDataFromDBByTime(Context context, Long[] time,OnProgressI onProgressI){
        onProgressI.onProgress("getDataFromDBByTime",OnProgressI.PREPARE,"");
        Observable.just("1").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, ArrayList<PictureB>>() {
                    @Override
                    public ArrayList<PictureB> apply(String s) throws Exception {
                        ArrayList<PictureB> data = DBTool.getAllRecord(new long[]{time[0]/1000,time[1]/1000});
                        return dealRecord(data, NetConstant.SPANCOUNT);
                    }
                })
        .subscribe(new Consumer<ArrayList<PictureB>>() {
            @Override
            public void accept(ArrayList<PictureB> pictureBS) throws Exception {
                onProgressI.onProgress("getDataFromDBByTime",OnProgressI.END,pictureBS);
            }
        });
    }

    public ArrayList<PictureB> getOnlyPictures(ArrayList<PictureB> data){
        HashMap<String,PictureB> map = new HashMap<>();
        ArrayList<PictureB> list = new ArrayList<>();
        for(int i=0;i<data.size();i++){
            map.put(data.get(i).getLocpath(),data.get(i));
        }

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            list.add(map.get(iterator.next()));
        }
        return list;
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


}


