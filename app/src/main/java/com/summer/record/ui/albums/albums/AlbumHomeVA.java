package com.summer.record.ui.albums.albums;

import com.summer.x.base.ui.VA;

import java.util.ArrayList;

public class AlbumHomeVA extends VA {

    public ArrayList<String> getRecords(){
        ArrayList<String> datas = new ArrayList<>();
        for(int i=0;i<100;i++){
            datas.add(i+"");
        }
        return datas;
    }

}
