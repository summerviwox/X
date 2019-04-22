package com.summer.record.ui.albums.album;

import android.provider.MediaStore;

import com.summer.record.data.model.PictureB;
import com.summer.record.ui.albums.bean.Album;
import com.summer.record.ui.pictures.picture.PictureDownDE;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlbumVA extends VA {

    private String albumid;

    private ArrayList<PictureB> datas = new ArrayList<>();

    private ArrayList<String> menus = new ArrayList<>();

    private PictureDownDE pictureDownDE = new PictureDownDE();

    private PictureB currentPictures;

    @Override
    public void initVA() {
        super.initVA();
        menus.add("新增");
        menus.add("删除");
        menus.add("下载全部");
        menus.add("下载全部图片");
        menus.add("下载全部视频");
        menus.add("设置为封面");
    }

    public void setDatas(ArrayList<PictureB> datas){
        this.datas .clear();
        this.datas.addAll(datas);
    }
}
