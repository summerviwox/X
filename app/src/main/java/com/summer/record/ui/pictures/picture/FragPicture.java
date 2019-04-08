package com.summer.record.ui.pictures.picture;

import android.os.Bundle;
import android.view.View;

import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.XFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 图片详情
 */
public class FragPicture extends XFragment<PictureUI,PictureDE,PictureVA> {

    public static FragPicture getInstance(PictureB data){
        FragPicture fragPicture = new FragPicture();
        fragPicture.setArguments(new Bundle());
        fragPicture.getArguments().putSerializable("data",data);
        return fragPicture;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getVA().setPicture((PictureB)(getArguments().getSerializable("data")));
        getUI().initPicture(getVA().getPicture());
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator(R.anim.anim_picture_enter,R.anim.anim_picture_exit);
    }
}
