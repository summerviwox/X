package com.summer.record.ui.pictures.hdpicture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.picture.PictureDE;
import com.summer.record.ui.pictures.picture.PictureVA;
import com.summer.x.base.ui.XActivity;

import androidx.annotation.Nullable;

public class HDPicutreCT extends XActivity<HDPicutreUI, PictureDE, PictureVA> {

    public static void goTo(Context context, PictureB pictureB){
        Intent intent = new Intent(context,HDPicutreCT.class);
        intent.putExtra("data",pictureB);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getVA().setPicture((PictureB) getIntent().getSerializableExtra("data"));
        getUI().initPicture(getVA().getPicture());
    }
}
