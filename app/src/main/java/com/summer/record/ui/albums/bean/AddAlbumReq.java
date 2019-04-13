package com.summer.record.ui.albums.bean;

import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAlbumReq extends VA {

    private String name;

    private ArrayList<String> albumItems;
}
