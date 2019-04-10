package com.summer.record.ui.pictures.detail;

import android.content.Intent;

import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PictureDetailVA extends VA {

    private ArrayList<PictureB> datas;

    private int pos;
}
