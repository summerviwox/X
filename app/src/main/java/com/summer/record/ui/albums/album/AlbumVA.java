package com.summer.record.ui.albums.album;

import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.picture.PictureDownDE;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlbumVA extends VA {

    private String albumid;

    private ArrayList<PictureB> datas = new ArrayList<>();

    private ArrayList<String> itemMenus = new ArrayList<>();

    private ArrayList<HashMap<String,String>> menus = new ArrayList<>();

    private PictureDownDE pictureDownDE = new PictureDownDE();

    private PictureB currentPictures;

    @Override
    public void initVA() {
        super.initVA();
        itemMenus.add("新增");
        itemMenus.add("删除");
        itemMenus.add("下载全部");
        itemMenus.add("下载全部图片");
        itemMenus.add("下载全部视频");
        itemMenus.add("设置为封面");


        String[] text = new String[]{"新增","下载全部","下载全部图片","下载全部视频"};
        for(int i=0;i<text.length;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("text",text[i]);
            menus.add(hashMap);
        }
    }

    public void setDatas(ArrayList<PictureB> datas){
        this.datas .clear();
        this.datas.addAll(datas);
    }
}
