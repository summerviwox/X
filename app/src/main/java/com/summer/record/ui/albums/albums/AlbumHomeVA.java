package com.summer.record.ui.albums.albums;

import com.summer.record.data.model.PictureB;
import com.summer.record.ui.albums.bean.Album;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumHomeVA extends VA {

    private ArrayList<PictureB> datas = new ArrayList<>();

    public void addDatas(PictureB data){
        this.datas.add(data);
    }

    private ArrayList<String> menus = new ArrayList<>();

    private ArrayList<Album> albums = new ArrayList<>();

    private Album selectedAlbum;

    public void setAlbums( ArrayList<Album> datas){
        albums.clear();
        albums.addAll(datas);
    }

    @Override
    public void initVA() {
        super.initVA();
        menus.add("新增相册");
        menus.add("删除相册");
    }

    public static ArrayList<String> getRecords(){
        ArrayList<String> datas = new ArrayList<>();
        for(int i=0;i<100;i++){
            datas.add(i+"");
        }
        return datas;
    }

}
