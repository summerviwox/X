package com.summer.record.data.model;

import android.net.Uri;

import com.blankj.utilcode.util.TimeUtils;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.summer.record.data.db.AppDataBase;
import com.summer.x.base.ui.VA;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(database = AppDataBase.class)
public class PictureB extends BaseModel implements Serializable {


    /**
     * id : 9994
     * locpath : /storage/emulated/0/tencent/MicroMsg/WeChat/1553763040226.mp4
     * netpath : E:\record\20190328\1553763040226.mp4
     * ctime : 1553763041000
     * utime : 1553763040000
     * atype : video
     * duration : 19264
     * name : 1553763040226.mp4
     * isUploaded : 0
     */

    @PrimaryKey(autoincrement = true)
    public Integer id;
    @Column
    public String locpath;
    @Column
    public String netpath;
    @Column
    public Long ctime;
    @Column
    public Long utime;
    @Column
    public String atype;
    @Column
    public Long duration;
    @Column
    public String name;
    @Column
    public Integer isupload;

    public String dateStr;

    public String yyyyMM;

    public boolean isFrist;

    public static final String ATYPE_VIDEO = "video";

    public static final String ATYPE_IMAGE = "image";

    private boolean selected;

    private int pos;


    public PictureB() {
    }

    public PictureB(String type, int id, String locpath, long ctime, long utime, Long duration, String name) {
        this.atype = type;
        this.id = id;
        this.locpath = locpath;
        this.ctime = ctime;
        this.utime = utime;
        this.duration = duration;
        this.name = name;
        initDateStr();
    }

    public void initDateStr(){
        Date d=new Date((ctime));
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateStr = df.format(d);
        df=new SimpleDateFormat("yyyy-MM");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        yyyyMM = df.format(d);
    }
}
