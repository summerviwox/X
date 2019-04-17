package com.summer.record.ui.pictures.home.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import lombok.Getter;
import lombok.Setter;
import retrofit2.http.GET;

public class TitleEntity extends AbstractExpandableItem<ItemEntity> implements MultiItemEntity {

    @Getter
    @Setter
    private String title;

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
