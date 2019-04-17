package com.summer.record.ui.pictures.home.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.summer.record.data.model.PictureB;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class ItemEntity implements MultiItemEntity {

    @Getter
    @Setter
    private PictureB item;

    @Override
    public int getItemType() {
        return 1;
    }
}
