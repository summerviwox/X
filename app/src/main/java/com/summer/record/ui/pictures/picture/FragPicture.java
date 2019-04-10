package com.summer.record.ui.pictures.picture;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.loading.LoadingFrag;
import com.summer.record.ui.pictures.pictures.PictureHomeDE;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;
import com.summer.x.util.HandleUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 图片详情
 */
public class FragPicture extends XFragment<PictureUI, PictureHomeDE,PictureVA> {


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

    @Optional
    @OnClick({R.id.upload})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.upload:
                getDE().uploadRecords(getVA().getPicture(), new OnProgressI() {

                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        ToastUtils.showLong(""+data);
                        switch (status){
                            case SUCCESS:
                                getVA().getPicture().setIsupload(1);
                                getVA().getPicture().save();
                                break;
                            case ERROR:

                                break;
                        }
                    }
                });
                break;
        }
    }
}
