package com.summer.record.ui.pictures.mothpicture;

import com.summer.record.ui.loading.LoadingFrag;
import com.summer.record.ui.pictures.home.PictureGetDE;
import com.summer.record.ui.pictures.home.PictureHomeDE;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MothPictureVA extends VA {

    private PictureHomeDE pictureHomeDE = new PictureHomeDE();

    private PictureGetDE pictureGetDE = new PictureGetDE();

    private ArrayList<String> strs = new ArrayList<>();

    private LoadingFrag loadingFrag = new LoadingFrag();
}
