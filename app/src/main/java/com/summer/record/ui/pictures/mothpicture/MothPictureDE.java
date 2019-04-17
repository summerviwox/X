package com.summer.record.ui.pictures.mothpicture;

import android.content.Context;

import com.summer.record.constant.NetConstant;
import com.summer.record.data.model.PictureB;
import com.summer.record.data.net.Net;
import com.summer.record.ui.pictures.home.PictureGetDE;
import com.summer.record.ui.pictures.home.PictureHomeDE;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.DE;
import com.summer.x.data.net.BaseCallBack;
import com.summer.x.data.net.ObjectData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MothPictureDE extends DE {

    public void getMaxMinDateStamp(OnProgressI progressI){

        Net.getInstance().getMaxMinDateStamp().enqueue(new BaseCallBack<ObjectData<Long[]>>(){
            @Override
            public void onSuccess(ObjectData<Long[]> objectData) {
                super.onSuccess(objectData);
                progressI.onProgress("getMaxMinDateStamp",OnProgressI.SUCCESS,objectData.getData());
            }

            @Override
            public void onError(int code, String error) {
                super.onError(code, error);
            }
        });
    }

    /**
     * 获取两个时间点之间的所有月份
     * @param times
     * @return
     */
    public ArrayList<String> getMonthList(Long[] times){
        ArrayList<String> datas = new ArrayList<>();
        long start = Math.min(times[0],times[1]);
        long end = Math.max(times[0],times[1]);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String starttime = simpleDateFormat.format(new Date(start));
        String endtime = simpleDateFormat.format(new Date(end));
        int startyear = Integer.parseInt(starttime.split("-")[0]);
        int startmonth = Integer.parseInt(starttime.split("-")[1]);
        int endyear = Integer.parseInt(endtime.split("-")[0]);
        int endmonth = Integer.parseInt(endtime.split("-")[1]);

        if((endyear-startyear)==0){
           for(int i=startmonth;i<=endmonth;i++){
               if(i<10){
                   datas.add(startyear+"-"+"0"+i+"");
               }else{
                   datas.add(startyear+"-"+i+"");
               }
           }
        }else if((endyear-startyear)>=1){
            for(int i=startmonth;i<=12;i++){
                if(i<10){
                    datas.add(startyear+"-"+"0"+i+"");
                }else{
                    datas.add(startyear+"-"+i+"");
                }
            }
            if((endyear-startyear)>1){
                for(int i=startyear+1;i<=endyear-1;i++){
                    for(int j=1;j<=12;j++){
                        if(j<10){
                            datas.add(i+"-"+"0"+j+"");
                        }else{
                            datas.add(i+"-"+j+"");
                        }
                    }
                }
            }
            for(int i=1;i<=endmonth;i++){
                if(i<10){
                    datas.add(endyear+"-"+"0"+i+"");
                }else{
                    datas.add(endyear+"-"+i+"");
                }
            }
        }
        return datas;
    }

    /**
     * 获取该月起至时间戳
     * @param time
     * @return
     */
    public Long[] getStartEndTimeStamp(String time){
        Long[] timstam = new Long[2];
        int year = Integer.parseInt(time.split("-")[0]);
        int month = Integer.parseInt(time.split("-")[1])-1;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.YEAR,year);
        startCalendar.set(Calendar.MONTH,month);
        startCalendar.set(Calendar.DAY_OF_MONTH,1);
        startCalendar.set(Calendar.HOUR_OF_DAY,0);
        timstam[0]=startCalendar.getTimeInMillis();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.YEAR,year);
        endCalendar.set(Calendar.MONTH,month);
        endCalendar.add(Calendar.MONTH,1);
        endCalendar.set(Calendar.DAY_OF_MONTH,1);
        endCalendar.set(Calendar.HOUR_OF_DAY,0);
        timstam[1]=endCalendar.getTimeInMillis();
        return timstam;
    }

}
