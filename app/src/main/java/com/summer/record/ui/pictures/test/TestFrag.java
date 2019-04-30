package com.summer.record.ui.pictures.test;

import android.os.Bundle;

import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.de.PicturesDE;
import com.summer.record.ui.pictures.test.testa.TestaFrag;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class TestFrag extends XActivity<TestUI, PicturesDE,TestVA> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.actcontainer,new TestaFrag());
    }
}
